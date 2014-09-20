package com.unhackathon.creature.einnuj_stats_data;

import java.util.Map;

/**
 * The mastermind class that will interact with all other parts of 'creature'. Responsible for
 * manipulating all data related to creature stats.
 *
 * Created by einnuj on 9/20/2014.
 */
public class statsManager {
    // Hold the actual creature's stats for manipulation
    public creatureStats creatureStats;

    // Map for holding of creatureStats. Meant to be permanent.
    public Map<String, creatureStats> statsMap;

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
            // TODO: Call up some File Manager; grab/set the Map
            // TODO: Call up last creatureStat from the Map; set it.
        }
        else {
            // Create and set new creature stats, default values
            creatureStats = new creatureStats();
            initCreatureStats();

            // Clear the stats Map, then add the default monster
            statsMap.clear();
            statsMap.put(creatureStats.name, creatureStats);

            // TODO: consider writing Map for data permanence at this point
        }
    }

    /**
     * TODO
     *      -calculateNextLevelExperience
     *      -levelUp
     *      -buildLevelCurve
     *      -gainExperience
     *      -incrementStats
     *      -loadStats
     */
}
