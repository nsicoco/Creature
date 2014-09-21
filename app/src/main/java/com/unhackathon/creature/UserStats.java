package com.unhackathon.creature;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Noel on 9/21/2014.
 */
public class UserStats {
    // A constant to represent the constant associated with the level curve
    private static int expConstant = 50;

    // A factor to represent the constant associated with the level curve
    private static int expFactor = 3;

    private SharedPreferences pref;

    private int experience;
    private int level;
    private int expCap;

    public UserStats(Context c) {
        pref = PreferenceManager.getDefaultSharedPreferences(c);
        experience = pref.getInt(PreferenceKeys.EXPERIENCE, 0);
        level = pref.getInt(PreferenceKeys.LEVEL, 1);
        expCap = getExpCap();
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        level = lvl;
        pref.edit().putInt(PreferenceKeys.LEVEL, level).apply();
        expCap = getExpCap();
        addExperience(0);
    }

    public void setExperience(int exp) {
        experience = exp;
        addExperience(0);
    }

    public void addExperience(int expToAdd) {
        experience += expToAdd;
        while(experience >= expCap) {
            experience -= expCap;
            levelUp();
        }
        pref.edit().putInt(PreferenceKeys.EXPERIENCE, experience).apply();
    }

    private void levelUp() {
        level++;
        pref.edit().putInt(PreferenceKeys.LEVEL, level).apply();
        expCap = getExpCap();
    }

    private int getExpCap() {
        return (int) (expConstant * Math.pow((double) level + 1, (double)expFactor));
    }

    public float getExpPrecent() {
        return experience / (float) expCap;
    }
}
