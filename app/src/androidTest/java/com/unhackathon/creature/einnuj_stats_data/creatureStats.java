package com.unhackathon.creature.einnuj_stats_data;

/**
 * A class to hold values associated with a creature's stats.
 *
 * Created by einnuj on 9/20/2014.
 */
public class creatureStats {
    // Represents name of the creature
    public String name;

    // Value for the creature's level; byte to save space and because it should never be a fraction
    // nor exceed 100.
    public byte level;

    // Value for attack/defense of creature; int over floating points for performance, and because,
    // what are decimals used for in RPGs anyway?
    public int attack;
    public int defense;

    /**
     * TODO
     *      - Typing?
     *      - Evolution Levels?
     *      - Variable stats constants (per creature)?
     *      - Unique level curves?
     */
}
