package com.dsa.practice.arrays.codeSignal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripletCountTest {
    private TripletCount tripletCount;

    @BeforeEach
    void setUp() {
        tripletCount = new TripletCount();
    }

    @Test
    void testEmptyString() {
        assertEquals(0, tripletCount.getTripletCount(""));
    }

    @Test
    void testStringShorterThanThreeCharacters() {
        assertEquals(0, tripletCount.getTripletCount("a"));
        assertEquals(0, tripletCount.getTripletCount("ab"));
    }

    @Test
    void testSingleValidTripletLowercase() {
        assertEquals(1, tripletCount.getTripletCount("aaa"));
    }

    @Test
    void testSingleValidTripletCaseInsensitive() {
        assertEquals(1, tripletCount.getTripletCount("aXa"));
        assertEquals(1, tripletCount.getTripletCount("aXA"));
    }

    @Test
    void testNoValidTriplets() {
        assertEquals(0, tripletCount.getTripletCount("abc"));
        assertEquals(0, tripletCount.getTripletCount("abcdef"));
    }

    @Test
    void testMultipleValidTriplets() {
        assertEquals(2, tripletCount.getTripletCount("abCXccc"));
    }

    @Test
    void testOverlappingTriplets() {
        // Triplets: aaa, aaa (overlapping)
        assertEquals(2, tripletCount.getTripletCount("aaaa"));
    }

    @Test
    void testMixedCaseAndCharacters() {
        // Triplets: AbA, bBb, bAb
        assertEquals(3, tripletCount.getTripletCount("AbAbBb"));
    }

    @Test
    void testAllSameCharacterMixedCase() {
        // Triplets: AaA, aAa, AaA
        assertEquals(3, tripletCount.getTripletCount("AaAaA"));
    }

    @Test
    void testNullInputHandling() {
        // If null handling is expected to return 0
        assertEquals(0, tripletCount.getTripletCount(null));
    }
}