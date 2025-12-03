import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

class solution {
    public static void main (String args[]) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(Path.of("input"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        long totalMaxJoltage = 0;
        
        for ( String line : lines ) {

            int[] bank = line.chars().map(c -> c - '0').toArray();
            System.out.println("line: " + line);
            //System.out.println("bank: " + java.util.Arrays.toString(bank));

            // change to 2 for part 1 result
            int num = 12;

            long maxBankJoltage = 0;
            int maxIndex = -1;
            for ( int i = 1; i <= num; i++ ) {

                int max = 0;
                for ( int j = maxIndex + 1; j < (bank.length -num +i) ; j++) {
                    if ( max < bank[j] ) {
                        max = bank[j];
                        maxIndex = j;
                        //System.out.print("//bank[j]=" + bank[j] + " j=" + j);
                        //System.out.print("//length=" + bank.length);
                        //System.out.println("//end=" + (bank.length -num +i));
                    }
                }

                System.out.print("//maxBankJoltage:" + maxBankJoltage);
                System.out.print("//max:" + max);
                maxBankJoltage = ( maxBankJoltage * 10 ) + max;
                System.out.println();
            }

            

            System.out.println("maximum bank joltage: " + maxBankJoltage);
            totalMaxJoltage += maxBankJoltage;
            System.out.println("=============================================");
            


        }

        System.out.println("total maximum joltage: " + totalMaxJoltage);

    }
   
}
