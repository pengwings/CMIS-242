/**
 * Project1.java
 * Brian Yu
 * 1/26/2020
 * This class reads in a file of weights and returns the minimum weight, maximum weight, and average weight.
 */

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Project1 {
    // main method that allows user to choose file of weights from main directory and returns min, max, and average
    public static void main(String[] args) throws FileNotFoundException { //method will exit if file isn't found
        // creates JFileChooser object
        JFileChooser chooser = new JFileChooser();
        int open = chooser.showOpenDialog(null);
        // file is only created if user picks file and clicks OK, otherwise program will exit
        if (open == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Scanner scan = new Scanner(file);
            Weight[] weights = new Weight[25]; //creates array to store read weights with max of 25
            int weightCount = 0; //counts weights in file
            while (scan.hasNextLine()) {
                if (weightCount < 25) {
                    int scannedPounds = scan.nextInt();
                    double scannedOunces = scan.nextDouble();
                    Weight scannedWeight = new Weight(scannedPounds, scannedOunces);
                    weights[weightCount] = scannedWeight;
                    weightCount++;
                } else { //prints error message and exits if file has more than 25 weights
                    System.out.println("The file selected has more than 25 weights.");
                    System.exit(0);
                }
            }
            // prints error message and exits if file is empty
            if (weightCount == 0) {
                System.out.println("The file selected is empty.");
                System.exit(0);
            }
            System.out.println("The minimum weight is " + findMinimum(weights, weightCount).toString());
            System.out.println("The maxmimum weight is " + findMaximum(weights, weightCount).toString());
            System.out.println("The average weight is " + findAverage(weights, weightCount).toString());
        }

    }
    // private class method to find minimum weight in file
    private static Weight findMinimum(Weight[] weightArray, int count) {
        Weight minimum = weightArray[0];
        for(int i=1; i<count; i++) {
            if(weightArray[i].lessThan(minimum)) {
                minimum = weightArray[i];
            }
        }
        return minimum;
    }
    //private class method to find maximum weight in file
    private static Weight findMaximum(Weight[] weightArray, int count) {
        Weight maximum = weightArray[0];
        for(int i=1; i<count; i++) {
            if(!weightArray[i].lessThan(maximum)) {
                maximum = weightArray[i];
            }
        }
        return maximum;
    }
    //private class method to find average weight in file
    private static Weight findAverage(Weight[] weightArray, int count) {
        Weight average = weightArray[0];
        for(int i=1; i<count; i++) {
            average.addTo(weightArray[i]);
        }
        average.divide(count);
        return average;
    }

}
