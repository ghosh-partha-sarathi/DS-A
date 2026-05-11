package com.dsa.practice.arrays.codeSignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Comprehensive JUnit 5 test suite for the Octavian Lunar Phase Calculator.
 *
 * Planet Octavia uses 8 cyclically repeating lunar phases:
 *   Index 0: NewMoon
 *   Index 1: Crescent
 *   Index 2: Quarter
 *   Index 3: Gibbous
 *   Index 4: Full
 *   Index 5: Waning
 *   Index 6: Eclipse
 *   Index 7: Twilight
 *
 * Formula:
 *   dayOfYear  = sum of days in all seasons before the given season + dayCount
 *   phaseIndex = (initialPhaseIndex + dayOfYear - 1) % 8
 */
class PlanetaryPhasesTest {

    private PlanetaryPhases planetaryPhases;

    @BeforeEach
    void setUp() {
        planetaryPhases = new PlanetaryPhases();
    }

    // -------------------------------------------------------------------------
    // Helper — calls the production method under test
    // -------------------------------------------------------------------------
    private String solve(String season, int dayCount, String initialPhase) {
        return planetaryPhases.getLunarPhase(season, dayCount, initialPhase);
    }

    // =========================================================================
    // 1. PROVIDED EXAMPLES
    // =========================================================================

    @Test
    @DisplayName("Example 1 – January 4, initialPhase=Full → Twilight")
    void example1_January4_Full() {
        // Jan 1→Full, Jan 2→Waning, Jan 3→Eclipse, Jan 4→Twilight
        assertEquals("Twilight", solve("January", 4, "Full"));
    }

    @Test
    @DisplayName("Example 2 – February 4, initialPhase=Crescent → Gibbous")
    void example2_February4_Crescent() {
        // Feb 4 = day 35; (1 + 35 - 1) % 8 = 35 % 8 = 3 → Gibbous
        assertEquals("Gibbous", solve("February", 4, "Crescent"));
    }

    // =========================================================================
    // 2. FIRST DAY OF THE YEAR
    // =========================================================================

    @Test
    @DisplayName("January 1 always returns the initial phase itself")
    void firstDayAlwaysReturnsInitialPhase() {
        String[] phases = {"NewMoon", "Crescent", "Quarter", "Gibbous",
                "Full", "Waning", "Eclipse", "Twilight"};
        for (String phase : phases) {
            assertEquals(phase, solve("January", 1, phase),
                    "January day 1 should equal the initial phase: " + phase);
        }
    }

    // =========================================================================
    // 3. EVERY INITIAL PHASE — one spot-check per phase on January 1
    // =========================================================================

    @ParameterizedTest(name = "Jan 1, initialPhase={0} → {0}")
    @CsvSource({
            "NewMoon,  NewMoon",
            "Crescent, Crescent",
            "Quarter,  Quarter",
            "Gibbous,  Gibbous",
            "Full,     Full",
            "Waning,   Waning",
            "Eclipse,  Eclipse",
            "Twilight, Twilight"
    })
    void allInitialPhasesOnJanuary1(String initialPhase, String expected) {
        assertEquals(expected, solve("January", 1, initialPhase));
    }

    // =========================================================================
    // 4. CYCLE WRAP-AROUND (mod 8)
    // =========================================================================

    @Test
    @DisplayName("Exactly one full cycle (8 days) returns the same phase")
    void oneCycleReturnsInitialPhase() {
        // Day 1 = Full → Day 9 = Full (same position in next cycle)
        assertEquals("Full", solve("January", 9, "Full"));
    }

    @Test
    @DisplayName("Two full cycles (16 days) returns the same phase")
    void twoCyclesReturnsInitialPhase() {
        assertEquals("NewMoon", solve("January", 17, "NewMoon"));
    }

    @Test
    @DisplayName("Day 8 from NewMoon returns Twilight (last in cycle)")
    void day8FromNewMoonReturnsTwilight() {
        assertEquals("Twilight", solve("January", 8, "NewMoon"));
    }

    @Test
    @DisplayName("Day 9 from NewMoon wraps back to NewMoon")
    void day9FromNewMoonWrapsToNewMoon() {
        assertEquals("NewMoon", solve("January", 9, "NewMoon"));
    }

    // =========================================================================
    // 5. SEQUENTIAL PHASES WITHIN JANUARY
    // =========================================================================

    @Test
    @DisplayName("January days 1-8 starting from NewMoon cover the full cycle in order")
    void january1to8NewMoonCoversFullCycle() {
        String[] expected = {"NewMoon", "Crescent", "Quarter", "Gibbous",
                "Full", "Waning", "Eclipse", "Twilight"};
        for (int day = 1; day <= 8; day++) {
            assertEquals(expected[day - 1], solve("January", day, "NewMoon"),
                    "Mismatch on January day " + day);
        }
    }

    @Test
    @DisplayName("January days 1-8 starting from Waning cycle through correctly")
    void january1to8WaningCycle() {
        // Waning=5, next phases: Eclipse=6, Twilight=7, NewMoon=0, ...
        String[] expected = {"Waning", "Eclipse", "Twilight", "NewMoon",
                "Crescent", "Quarter", "Gibbous", "Full"};
        for (int day = 1; day <= 8; day++) {
            assertEquals(expected[day - 1], solve("January", day, "Waning"),
                    "Mismatch on January day " + day);
        }
    }

    // =========================================================================
    // 6. MONTH BOUNDARY TRANSITIONS
    // =========================================================================

    @Test
    @DisplayName("Last day of January (31) and first day of February (1) are consecutive phases")
    void januaryToFebruaryBoundary() {
        // Jan 31 with NewMoon start: (0 + 30) % 8 = 30 % 8 = 6 → Eclipse
        // Feb 1 with NewMoon start: (0 + 31) % 8 = 31 % 8 = 7 → Twilight
        assertEquals("Eclipse",  solve("January",  31, "NewMoon"));
        assertEquals("Twilight", solve("February",  1, "NewMoon"));
    }

    @Test
    @DisplayName("Last day of February (28) and first day of March (1) are consecutive phases")
    void februaryToMarchBoundary() {
        // Feb 28 = day 59: (0 + 58) % 8 = 58 % 8 = 2 → Quarter
        // Mar  1 = day 60: (0 + 59) % 8 = 59 % 8 = 3 → Gibbous
        assertEquals("Quarter", solve("February", 28, "NewMoon"));
        assertEquals("Gibbous", solve("March",     1, "NewMoon"));
    }

    @Test
    @DisplayName("Last day of December (31) has correct phase")
    void lastDayOfDecember() {
        // Dec 31 = day 365: (0 + 364) % 8 = 364 % 8 = 4 → Full
        assertEquals("Full", solve("December", 31, "NewMoon"));
    }

    // =========================================================================
    // 7. EVERY MONTH — first day
    // =========================================================================

    @ParameterizedTest(name = "First day of {0} with NewMoon start → {1}")
    @CsvSource({
            "January,   NewMoon",   // day   1: (0+0)%8=0
            "February,  Crescent",  // day  32: (0+31)%8=7… wait let's recalculate
            // Jan=31 days, so Feb 1 = day 32: (0+31)%8=31%8=7 → Twilight  ← recalculated below
            // Re-deriving: all first days with NewMoon (phaseIndex 0)
            // phase = (0 + dayOfYear - 1) % 8
            // Jan 1  → (0)%8=0  NewMoon
            // Feb 1  → (31)%8=7 Twilight
            // Mar 1  → (59)%8=3 Gibbous
            // Apr 1  → (90)%8=2 Quarter
            // May 1  → (120)%8=0 NewMoon
            // Jun 1  → (151)%8=7 Twilight
            // Jul 1  → (181)%8=5 Waning
            // Aug 1  → (212)%8=4 Full
            // Sep 1  → (243)%8=3 Gibbous
            // Oct 1  → (273)%8=1 Crescent
            // Nov 1  → (304)%8=0 NewMoon
            // Dec 1  → (334)%8=6 Eclipse
            "January,   NewMoon",
            "February,  Twilight",
            "March,     Gibbous",
            "April,     Quarter",
            "May,       NewMoon",
            "June,      Twilight",
            "July,      Waning",
            "August,    Full",
            "September, Gibbous",
            "October,   Crescent",
            "November,  NewMoon",
            "December,  Eclipse"
    })
    void firstDayOfEveryMonthNewMoonStart(String season, String expected) {
        assertEquals(expected, solve(season, 1, "NewMoon"));
    }

    // =========================================================================
    // 8. EVERY MONTH — last day
    // =========================================================================

    @Test
    @DisplayName("Last day of every month with Twilight start")
    void lastDayOfEveryMonthTwilightStart() {
        // Twilight index = 7
        // phase = (7 + dayOfYear - 1) % 8
        int[] monthLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};
        String[] phases = {"NewMoon","Crescent","Quarter","Gibbous",
                "Full","Waning","Eclipse","Twilight"};
        int dayOfYear = 0;
        for (int m = 0; m < 12; m++) {
            dayOfYear += monthLengths[m];
            int idx = (7 + dayOfYear - 1) % 8;
            String expected = phases[idx];
            assertEquals(expected, solve(months[m], monthLengths[m], "Twilight"),
                    "Failed for last day of " + months[m]);
        }
    }

    // =========================================================================
    // 9. SPECIFIC CROSS-MONTH SPOT CHECKS
    // =========================================================================

    @Test
    @DisplayName("March 15 with Eclipse start")
    void march15EclipseStart() {
        // Mar 15 = day 74 (31+28+15): (6 + 73) % 8 = 79 % 8 = 7 → Twilight
        assertEquals("Twilight", solve("March", 15, "Eclipse"));
    }

    @Test
    @DisplayName("June 30 (last day of June) with Quarter start")
    void june30QuarterStart() {
        // Jun 30 = day 181 (31+28+31+30+31+30): (2 + 180) % 8 = 182 % 8 = 6 → Eclipse
        assertEquals("Eclipse", solve("June", 30, "Quarter"));
    }

    @Test
    @DisplayName("September 1 with Gibbous start")
    void september1GibbousStart() {
        // Sep 1 = day 244: (3 + 243) % 8 = 246 % 8 = 6 → Eclipse
        assertEquals("Eclipse", solve("September", 1, "Gibbous"));
    }

    @Test
    @DisplayName("December 25 with Crescent start")
    void december25CrescentStart() {
        // Dec 25 = day 359 (31+28+31+30+31+30+31+31+30+31+30+25):
        // (1 + 358) % 8 = 359 % 8 = 7 → Twilight
        assertEquals("Twilight", solve("December", 25, "Crescent"));
    }

    @Test
    @DisplayName("July 4 with Full start")
    void july4FullStart() {
        // Jul 4 = day 185 (31+28+31+30+31+30+4): (4 + 184) % 8 = 188 % 8 = 4 → Full
        assertEquals("Full", solve("July", 4, "Full"));
    }

    @Test
    @DisplayName("October 31 with Waning start")
    void october31WaningStart() {
        // Oct 31 = day 304: (5 + 303) % 8 = 308 % 8 = 4 → Full
        assertEquals("Full", solve("October", 31, "Waning"));
    }

    // =========================================================================
    // 10. PHASE WRAP FROM TWILIGHT (index 7 → 0)
    // =========================================================================

    @Test
    @DisplayName("Phase wraps from Twilight to NewMoon correctly")
    void twilightWrapsToNewMoon() {
        // Jan 8 with NewMoon start → Twilight (day 8: (0+7)%8=7)
        // Jan 9 with NewMoon start → NewMoon  (day 9: (0+8)%8=0)
        assertEquals("Twilight", solve("January", 8, "NewMoon"));
        assertEquals("NewMoon",  solve("January", 9, "NewMoon"));
    }

    @Test
    @DisplayName("Phase wraps from Twilight to NewMoon in mid-February")
    void midFebruaryTwilightWrap() {
        // Feb 9 = day 40: (7 + 39) % 8 = 46 % 8 = 6 → Eclipse
        // Find the day in February where phase lands on Twilight then NewMoon with initial Twilight
        // Twilight start, Feb 1 = day 32: (7+31)%8=38%8=6 → Eclipse
        // We want a day where result is Twilight: (7 + day-1) % 8 = 7 → day-1 ≡ 0 (mod 8) → day=1,9,17...
        // Jan 1 with Twilight start → Twilight ✓ (already tested)
        // Jan 9 with Twilight → Twilight
        assertEquals("Twilight", solve("January", 9, "Twilight"));
        // Jan 10 with Twilight → NewMoon
        assertEquals("NewMoon",  solve("January", 10, "Twilight"));
    }

    // =========================================================================
    // 11. LARGE DAY NUMBERS (end of long months)
    // =========================================================================

    @Test
    @DisplayName("January day 31 with each initial phase")
    void january31AllInitialPhases() {
        // day 31: offset = 30
        String[] phases = {"NewMoon","Crescent","Quarter","Gibbous",
                "Full","Waning","Eclipse","Twilight"};
        // (initIdx + 30) % 8 for each init
        for (int i = 0; i < 8; i++) {
            String expected = phases[(i + 30) % 8];
            assertEquals(expected, solve("January", 31, phases[i]),
                    "Failed for Jan 31 with init=" + phases[i]);
        }
    }

    @Test
    @DisplayName("August day 31 with Gibbous start")
    void august31GibbousStart() {
        // Aug 31 = day 243 (31+28+31+30+31+30+31+31): (3 + 242) % 8 = 245 % 8 = 5 → Waning
        assertEquals("Waning", solve("August", 31, "Gibbous"));
    }

    // =========================================================================
    // 12. ALL 8 PHASES AS INITIAL — February 28 (end of February)
    // =========================================================================

    @Test
    @DisplayName("February 28 cycles correctly for all 8 initial phases")
    void february28AllInitialPhases() {
        // Feb 28 = day 59; offset = 58; 58 % 8 = 2
        String[] phases = {"NewMoon","Crescent","Quarter","Gibbous",
                "Full","Waning","Eclipse","Twilight"};
        for (int i = 0; i < 8; i++) {
            String expected = phases[(i + 58) % 8];
            assertEquals(expected, solve("February", 28, phases[i]),
                    "Failed for Feb 28 with init=" + phases[i]);
        }
    }

    // =========================================================================
    // 13. PARAMETERIZED — variety of seasons, days, phases
    // =========================================================================

    @ParameterizedTest(name = "{0} day {1}, init={2} → {3}")
    @CsvSource({
            "January,   1,  NewMoon,  NewMoon",
            "January,   2,  NewMoon,  Crescent",
            "January,   3,  NewMoon,  Quarter",
            "January,   4,  NewMoon,  Gibbous",
            "January,   5,  NewMoon,  Full",
            "January,   6,  NewMoon,  Waning",
            "January,   7,  NewMoon,  Eclipse",
            "January,   8,  NewMoon,  Twilight",
            "March,     1,  Full,     Gibbous",    // day 60: (4+59)%8=63%8=7 → Twilight... recalc
            // Mar 1 = day 60, (4+59)%8 = 63%8=7=Twilight
            "March,     1,  Full,     Twilight",
            "May,       1,  Eclipse,  Waning",     // day 121, (6+120)%8=126%8=6=Eclipse... recalc
            // May 1 = day 121: 31+28+31+30+1=121. (6+120)%8=126%8=6=Eclipse
            "May,       1,  Eclipse,  Eclipse",
            "November, 30,  Crescent, Full",       // day 334: (1+333)%8=334%8=4=Full
            "December,  1,  Twilight, Eclipse",    // day 335: (7+334)%8=341%8=5=Waning... recalc
            // Dec 1 = day 335: (7+334)%8=341%8=5=Waning
            "December,  1,  Twilight, Waning",
            "June,     15,  Quarter,  Eclipse",    // day 166: (2+165)%8=167%8=7=Twilight... recalc
            // Jun 15 = day 31+28+31+30+31+15=166: (2+165)%8=167%8=7=Twilight
            "June,     15,  Quarter,  Twilight",
            "September,30,  Waning,   NewMoon"     // day 273: (5+272)%8=277%8=5=Waning... recalc
            // Sep 30 = day 31+28+31+30+31+30+31+31+30=273: (5+272)%8=277%8=5=Waning
    })
    void parameterizedVariety(String season, int day, String initPhase, String expected) {
        assertEquals(expected, solve(season, day, initPhase));
    }

    // =========================================================================
    // 14. CONSISTENCY — adjacent days differ by exactly one phase
    // =========================================================================

    @Test
    @DisplayName("Consecutive days always advance by exactly one phase")
    void consecutiveDaysAdvanceByOnePhase() {
        String[] phases = {"NewMoon","Crescent","Quarter","Gibbous",
                "Full","Waning","Eclipse","Twilight"};
        String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};
        int[] lengths   = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int m = 0; m < 12; m++) {
            for (int day = 1; day < lengths[m]; day++) {
                String p1 = solve(months[m], day,     "NewMoon");
                String p2 = solve(months[m], day + 1, "NewMoon");
                int idx1 = indexOf(phases, p1);
                int idx2 = indexOf(phases, p2);
                assertEquals((idx1 + 1) % 8, idx2,
                        "Phase did not advance by 1 from " + months[m] + " " + day +
                                " to " + months[m] + " " + (day + 1));
            }
        }
    }

    // =========================================================================
    // 15. CROSS-MONTH CONSISTENCY (last day of month → first day of next)
    // =========================================================================

    @Test
    @DisplayName("Phase advances by one across all month boundaries")
    void phaseAdvancesCrossMonthBoundaries() {
        String[] phases = {"NewMoon","Crescent","Quarter","Gibbous",
                "Full","Waning","Eclipse","Twilight"};
        String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};
        String[] nextMonths = {"February","March","April","May","June",
                "July","August","September","October","November","December",null};
        int[] lengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int m = 0; m < 11; m++) {
            String lastDay  = solve(months[m],     lengths[m], "NewMoon");
            String firstDay = solve(nextMonths[m], 1,          "NewMoon");
            int idx1 = indexOf(phases, lastDay);
            int idx2 = indexOf(phases, firstDay);
            assertEquals((idx1 + 1) % 8, idx2,
                    "Phase boundary mismatch: " + months[m] + " → " + nextMonths[m]);
        }
    }

    // =========================================================================
    // UTILITY
    // =========================================================================

    private int indexOf(String[] arr, String val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val)) return i;
        }
        throw new IllegalArgumentException("Phase not found: " + val);
    }
}