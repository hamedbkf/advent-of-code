import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

class solution {
    public static void main (String args[]) {

        final String pathString; 
        if (args.length > 0) pathString = args[0];
        else pathString = "input";

        String input = null;

        try {
            input = Files.readString(Path.of(pathString)).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> ranges = Arrays.asList(input.split(","));
        System.out.println(input);

        List<Long> part1_invalidIds = new ArrayList<>();
        List<Long> invalidIds = new ArrayList<>();

        for (String range : ranges) {
            String[] edges = range.split("-");
            long start = Long.parseLong(edges[0]);
            long end = Long.parseLong(edges[1]);
            System.out.println("=============================================");
            System.out.println("Range: [[ " + start + " .. " + end + " ]]");

            for (long current = start; current <= end; current++) {

                String num = Long.toString(current);
                int numLen = num.length();

                List<Integer> factors = new ArrayList<>();
                for (int f = 1; f < numLen; f++) {
                    if (numLen % f == 0)
                        factors.add(f);
                }
                //System.out.println("Factors: " + factors);


                for (int splitSize : factors) {

                    //System.out.println(splitSize);
                    List<String> splitsList = new ArrayList<>();

                    for ( int si = 0; si < (numLen / splitSize); si++) { 

                        splitsList.add( num.substring(si * splitSize, (si * splitSize) + splitSize));
                    }
                    //System.out.println(splitsList);
                    
                    boolean isInvalid = true;
                    for ( int splitIndex = 1; splitIndex < splitsList.size() ; splitIndex++) { 
                        if ( ! splitsList.get(0).equals(splitsList.get(splitIndex)) ) {
                            //System.out.println("Split comparing: " + splitsList.get(splitIndex));
                            isInvalid = false;
                            break;
                        }
                    }


                    if ( isInvalid ) {
                        System.out.println("Adding: " + current);
                        invalidIds.add(current); 
                        break;
                    }
                    
                }

                if (numLen < 2 || numLen % 2 == 1) continue;
                int splitSize = numLen / 2;

                String firstHalf  = num.substring(0, splitSize);
                String secondHalf = num.substring(splitSize, numLen);


                if (firstHalf.equals(secondHalf)) {
                    //System.out.println(firstHalf + " | " + secondHalf);
                    //System.out.println(num);
                    part1_invalidIds.add(current);
                }
                
            }
            
        }

        long part1_sumIds = part1_invalidIds.stream().mapToLong(Long::longValue).sum();
        long sumIds = invalidIds.stream().mapToLong(Long::longValue).sum();
        System.out.println("========== Results ==========");
        System.out.println("part1 ids sum: " + part1_sumIds);
        System.out.println("part2 ids sum: " + sumIds);
        //System.out.println(invalidIds);

    }
   
}
