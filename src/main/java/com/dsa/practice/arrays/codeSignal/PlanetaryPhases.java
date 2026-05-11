package com.dsa.practice.arrays.codeSignal;

import java.util.HashMap;
import java.util.Map;

/**
 * Instructions
 * On the planet Octavia, astronomers track time using 8 distinct lunar phases instead of weekdays: "NewMoon", "Crescent",
 * "Quarter", "Gibbous", "Full", "Waning", "Eclipse", and "Twilight". These phases repeat cyclically.
 * The Octavian year is divided into 12 seasons (identical to Earth months in length), and each date has a corresponding lunar
 * phase.
 * Given a specific date (a season and day number) and the lunar phase with which the year began, determine the lunar phase
 * for that date.
 *
 * Notes
 * The number of days in each season in a non-leap year is, in order:
 * • January - 31, February - 28, March - 31, April - 30, May - 31, June - 30, July - 31, August - 31, September - 30,
 * October - 31. November - 30. December - 31
 * The lunar phases in the Octavian calendar are, in order: NewMoon, Crescent, Quarter, Gibbous, Full, Waning,
 * Eclipse, and Twilight
 *
 * Example
 * For season = "January", dayCount = 4, and initialPhase = "Full", the output should be solution(season,
 * dayCount, initialPhase) = "Twilight"
 * Explanation:
 * • January 1-4 are Full, Waning, Eclipse, and Twilight, respectively. So, the answer is "Twilight"
 *
 * For season = "February", dayCount = 4, and initialPhase = "Crescent", the output should be
 * solution(season, dayCount, initialPhase) = "Gibbous"
 * Explanation:
 * • February 4th is the 35th day since the beginning of the year. That is 4 full cycles (8 days each), and 3 more phases
 * for the 5th cycle. So, given that the first day of the year is Crescent, the answer is Gibbous.
 */
public class PlanetaryPhases {
    public String getLunarPhase(String season, int dayCount, String initialPhase) {
        Map<Integer, String> phaseMap = new HashMap<>();
        phaseMap.put(1, "NewMoon");
        phaseMap.put(2, "Crescent");
        phaseMap.put(3, "Quarter");
        phaseMap.put(4, "Gibbous");
        phaseMap.put(5, "Full");
        phaseMap.put(6, "Waning");
        phaseMap.put(7, "Eclipse");
        phaseMap.put(0, "Twilight");
        Map<String, Integer> phaseIndexMap = new HashMap<>();
        phaseIndexMap.put("NewMoon", 1);
        phaseIndexMap.put("Crescent", 2);
        phaseIndexMap.put("Quarter", 3);
        phaseIndexMap.put("Gibbous", 4);
        phaseIndexMap.put("Full", 5);
        phaseIndexMap.put("Waning", 6);
        phaseIndexMap.put("Eclipse", 7);
        phaseIndexMap.put("Twilight", 0);
        Map<String, Integer> seasonDayCount = new HashMap<>();
        seasonDayCount.put("January", 0);
        seasonDayCount.put("February", 31);
        seasonDayCount.put("March", 59);
        seasonDayCount.put("April", 90);
        seasonDayCount.put("May", 120);
        seasonDayCount.put("June", 151);
        seasonDayCount.put("July", 181);
        seasonDayCount.put("August", 212);
        seasonDayCount.put("September", 243);
        seasonDayCount.put("October", 273);
        seasonDayCount.put("November", 304);
        seasonDayCount.put("December", 334);

        int seasonDays = seasonDayCount.get(season);
        int initialCount = phaseIndexMap.get(initialPhase);
        int phaseCount = (seasonDays+initialCount+dayCount-1) % 8;

        return phaseMap.get(phaseCount);
    }
}

