package com.sasank.project;

import java.util.Random;

public class RandomIntegerGeneration {
    public int[] number1 = new int[10];
    public int[] number2=new int[10];


    public RandomIntegerGeneration(){ }

    public void refresh(int randomRangeMin, int randomRangeMax, String quiz){
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            number1[i] = random.nextInt(randomRangeMax - randomRangeMin + 1) + randomRangeMin;
            number2[i] = random.nextInt(randomRangeMax - randomRangeMin + 1) + randomRangeMin;
            if (quiz=="Subtraction" && number1[i] < number2[i]) {
                // Swap the numbers if number1 is smaller than number2
                int temp = number1[i];
                number1[i] = number2[i];
                number2[i] = temp;
            }
        }
    }
}
