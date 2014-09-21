package com.unhackathon.creature.minigames;

import android.util.Log;

import com.kilobolt.framework.Image;

import java.util.Random;

/**
 * Created by francis on 9/20/14.
 */
public class Anagram
{
    //private Image wordImage;
    private String word;
    private char[] letters;
    private char[] anagram;
    private Random random;

    public Anagram(String word)//, Image wordImage)
    {
        random = new Random();
        this.word = word.toUpperCase();
        //this.wordImage = wordImage;
        letters = this.word.toCharArray();
        shuffle();
    }
    private void shuffle()
    {
        anagram = word.toCharArray();

        for(int i = 0; i < letters.length; i++)
        {
            int firstIndex = random.nextInt(letters.length);
            int secondIndex = firstIndex;
            while(secondIndex == firstIndex)
                secondIndex = random.nextInt(letters.length);

            char temp = anagram[firstIndex];
            anagram[firstIndex] = anagram[secondIndex];
            anagram[secondIndex] = temp;
        }

    }
    public char[] getLetters() { return anagram; }

    //public Image getWordImage() { return wordImage; }

    public String toString()
    {
        return new String(anagram);
    }

    /**
     *
     * @param letters
     * @return
     * Integer representing the amount of letters in the correct place;
     */
    public int differenceBetweenWords(char[] letters)
    {
        int difference = this.letters[0] == letters[0] ? 0 : 1;
        for(int i = 1; i < letters.length; i++)
            if(this.letters[i] != letters[i]) ++difference;
        return difference;

    }


}
