package com.unhackathon.creature;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class CreatureCreationActivity extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int SELECT_PICTURE_REQUEST_CODE = 200;
    private File creatureImage;
    private File tempCreatureImage;
    private ImageView imageViewCreature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_creation);

        final File folder = new File(Environment.getExternalStorageDirectory(), "Creature");
        if(!folder.exists()) {
            if(!folder.mkdirs()) {
                Log.e("Creature", "failed to create directory");
            }
        }
        String imageName = "creatureimg.jpg";
        creatureImage = new File(folder, imageName);
        tempCreatureImage = new File(folder, "temp" + imageName);
        tempCreatureImage.delete();

        imageViewCreature = (ImageView) findViewById(R.id.imageViewCreature);
        //imageViewCreature.setVisibility(View.INVISIBLE);
        if(creatureImage.exists()) {
            imageViewCreature.setVisibility(View.VISIBLE);
            imageViewCreature.setImageURI(Uri.fromFile(creatureImage));
        } else {
            imageViewCreature.setVisibility(View.INVISIBLE);
        }

        Button buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    tempCreatureImage.createNewFile();
                } catch (IOException e) {

                }
                Uri fileUri = Uri.fromFile(tempCreatureImage);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        Button buttonSelect = (Button) findViewById(R.id.buttonSelect);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE_REQUEST_CODE);
            }
        });

        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                creatureImage.delete();
                try {
                    creatureImage.createNewFile();
                    copy(tempCreatureImage, creatureImage);
                    tempCreatureImage.delete();
                } catch(IOException e) {

                }
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                //Image Captured
                imageViewCreature.setVisibility(View.VISIBLE);
                //imageViewCreature.setImageBitmap(bmp);
                imageViewCreature.setImageURI(Uri.fromFile(tempCreatureImage));
            } else if (resultCode == RESULT_CANCELED) {
                //User cancelled the image capture
            } else {
                //Capture failed
                Toast.makeText(this, "Image Capture Failed.", Toast.LENGTH_LONG).show();
            }
        } else if(requestCode == SELECT_PICTURE_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                imageViewCreature.setVisibility(View.VISIBLE);
                imageViewCreature.setImageURI(selectedImageUri);
                tempCreatureImage = new File(selectedImageUri.getPath());
            } else if(resultCode == RESULT_CANCELED) {

            } else {
                //Selection failed
                Toast.makeText(this, "Image Selection Failed.", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.creature_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }
}
