package com.unhackathon.creature.einnuj_stats_data;

import java.util.HashMap;

/**
 * The mastermind class that will interact with all other parts of 'creature'. Responsible for
 * manipulating all data related to creature stats.
 *
 * Created by einnuj on 9/20/2014.
 */
public class statsManager {
    // A constant to represent the constant associated with the level curve
    static int expConstant = 50;

    // A factor to represent the constant associated with the level curve
    static int expFactor = 3;

    // Hold the actual creature's stats for manipulation
    public creatureStats creatureStats;

    // Map for holding of creatureStats. Meant to be permanent.
    public HashMap<String, creatureStats> statsMap;

    /* Constructor */
    public statsManager() {
        loadStatsMap();
    }


    /* Initializing and Permanence */

    /**
     * Very simply a  method to initialize the stats of a creature upon creation to ensure that stats
     * will never be some sort of empty value.
     */
    public void initCreatureStats() {
        creatureStats.name = "Dummy";
        creatureStats.level = 1;
        creatureStats.attack = 25;
        creatureStats.defense = 25;
    }

    /**
     * A method that gets a specified creature from the Map and loads the stats.
     *
     * @param creatureName  name of the creature to be loaded, which is the key for the Map
     */
    public void loadStats(String creatureName) {
        creatureStats = statsMap.get(creatureName);
    }

    /**
     * Checks to see if there's an existing Map of creatureStats, then loads it for use. If not,
     * we initialize it, and a default creature, for first-time-play. THIS IS IGNORING
     * DELETION/ABANDONMENT OF CREATURE
     */
    public void loadStatsMap() {
        // Check to see if one exists; if so, load it.

        // FOR NOW, IT IS SET TO FALSE BECAUSE
        //      1. WE DON'T KNOW WHERE TO STORE IT
        //      2. WE'RE ONLY IMPLEMENTING ONE CREATURE FOR UNHACKATHON
        if (false) {
            // @TODO: Call up some File Manager; grab/set the Map
            // @TODO: Call up last creatureStat from the Map; set it.
        }
        else {
            // Create and set new creature stats, default values & level curves
            creatureStats = new creatureStats();
            initCreatureStats();
            buildEXPThreshold(creatureStats);

            // Clear the stats Map, then add the default monster
            statsMap.clear();
            statsMap.put(creatureStats.name, creatureStats);

            // @TODO: consider writing Map for data permanence at this point
        }
    }

    /**
     * A method that sets the experience threshold of the creature to level up by the formula
     * expConstant * (Next Level)^expFactor
     *
     * @param targetStats   The creatureStats of the creature in question from the statsMap
     */
     public void buildEXPThreshold(creatureStats targetStats) {
         targetStats.expThreshold = (int) (expConstant
                 * Math.pow((double) targetStats.level + 1, (double)expFactor));
     }

    /* Data Manipulation*/

    /**
     * This method serves to appropriate EXP gain for creatures, in addition to reacting to any
     * level-ups.
     *
     * @param expGain   the quantity of experience gained by the creature.
     */
    public void gainExperience(int expGain) {
        // Check to see if initial exp and exp gained would cause a level up. If so, calculate
        // the carry-over. THIS CARRY OVER IS ALWAYS EITHER NEGATIVE OR 0
        if (creatureStats.expThreshold - expGain <= 0) {
            int carryOver = creatureStats.expThreshold - expGain;
            buildEXPThreshold(creatureStats);

            // Because this carryover is always either negative or 0, adding it to the expThreshold
            // will end up counting as EXP gained.
            creatureStats.expThreshold += carryOver;

            // Proceed with the normal level-up bonuses.
            levelUp(creatureStats);
        }
        else {
            // Reaching this means that the exp gained did not cause a level up/cross the
            // threshold, so we just subtract from expThreshold to represent gaining EXP.
            creatureStats.expThreshold -= expGain;
        }
    }

    /**
     * Increments the creature's level and stats upon leveling up.
     *
     * @param targetStats
     */
    public void levelUp(creatureStats targetStats)  {
        targetStats.level++;
        incrementStats(targetStats);
    }

    /**
     * A method called upon level-up to increase the stats; currently set to increment by a random
     * integer from 1 to 5.
     *
     * @param targetStats  The creatureStats of the creature in question from the statsMap
     */
    public void incrementStats(creatureStats targetStats) {
        targetStats.attack += (int) ((Math.random() * 5));
        targetStats.defense += (int) ((Math.random() * 5));
    }
}
