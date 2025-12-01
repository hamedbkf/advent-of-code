import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class solution {
    public static void main (String args[]) {

        File input = new File("input");

        ArrayList<String> stepsArray = new ArrayList<String>();
        int stepsCount = 0;

        int dial = 50;
        int step1_zeros = 0;
        int zeros = 0;


        try (Scanner steps = new Scanner(input)) {
            while (steps.hasNextLine()) {
                String step = steps.nextLine();
                stepsArray.add(step);
                stepsCount++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        for (String step : stepsArray) {
            char direction = step.charAt(0);
            int rotations = Integer.parseInt(step.substring(1, step.length()));


            System.out.println("=========================================");
            System.out.println("dial: " + dial);
            System.out.println("step: " + step);

            System.out.println("zeros before div: " + zeros);
            zeros += rotations / 100;
            System.out.println("zeros after div: " + zeros);

            System.out.println("rotations before mod: " + rotations);
            rotations %= 100;
            System.out.println("rotations after mod: " + rotations);

            System.out.println("dial before: " + dial);
            if (direction == 'R') {
                dial = (dial + rotations);
                System.out.println("dial mid: " + dial);
                if (dial > 100) zeros++;
            }
            else {
                if (dial == 0) zeros--; // scuffed workaround but works(too lazy to think of better if-statement logic)
                dial = (dial - rotations);
                System.out.println("dial mid: " + dial);
                if (dial < 0) {
                    zeros++;
                    dial+= 100;
                }
            }
            dial %= 100;

            if (dial == 0) {
                step1_zeros++;
                zeros++;
            }

            System.out.println("dial after: " + dial);
            System.out.println("zeros after: " + zeros);
        }

        System.out.println("========== Results ==========");
        System.out.println("step1 zeros: " + step1_zeros);
        System.out.println("step2 zeros: " + zeros);
    }
   
}
