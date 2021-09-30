package com.marcusfriberg;
// Imports
import java.lang.Math;

// Class to return a random number between 0 and a given maxNumber.
// Is used to get a random element in an arrayList where maxNumber
// should be the .size() of the list.
public class GetRandomNumberInRange {
    public GetRandomNumberInRange() {
    }

    public int getRandomNumber(int maxNumber) {
        // Setting variables for selected range
        int minNumber = 0;
        int range = maxNumber - minNumber;
        int id = (int) (Math.random() * range);
        return id;
    }
}

