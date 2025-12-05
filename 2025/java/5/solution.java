import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class solution {
    public static void main (String[] args) {
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader( new FileReader("input"));
            String line;
            while ( (line = reader.readLine()) != null ){
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Long>> ranges = new ArrayList<>();
        HashMap<Long, Boolean> ingredients = new HashMap<>();

        boolean parsingRanges = true;
        for ( String line : lines ) {
            if ( line.length() == 0 ) { parsingRanges = false; continue; };

            if ( parsingRanges ) {
                List<Long> edges = Arrays.stream(line.split("-"))
                    .mapToLong(Long::parseLong)
                    .boxed()
                    .toList();
                ranges.add(edges);
            } else {
                ingredients.put(Long.parseLong(line), false);
            }
        }

        int freshCount = 0;

        for ( Long ii : ingredients.keySet() ) {
            for ( int ri = 0; ri < ranges.size(); ri++ ) {
                Long start = ranges.get(ri).get(0);
                Long end   = ranges.get(ri).get(1);
                Boolean fresh = ingredients.get(ii);

                if ( !fresh && ii >= start && ii <= end ) {
                    ingredients.replace(ii, true);
                    freshCount++;
                }
            }
        }

        System.out.println("part1 freshCount: " + freshCount);


        // part 2

        System.out.println("============ part2 ============");

        ranges.sort( (a, b) -> { return a.get(0).compareTo(b.get(0)); } );

        Long totalFreshCount = 0l;

        Long currStart = ranges.get(0).get(0);
        Long currEnd   = ranges.get(0).get(1);
        Long currRange;
        for ( int ri = 0; ri < ranges.size()-1; ri++ ) {
            Long nextStart = ranges.get(ri+1).get(0);
            Long nextEnd   = ranges.get(ri+1).get(1);

            if ( currEnd >= nextStart ) {
                if ( currEnd < nextEnd) {
                    System.out.println(String.format("Merging: [%d..%d] + [%d..%d] => [%d..%d]", 
                                                    currStart, currEnd, nextStart, nextEnd, currStart, nextEnd));
                    currEnd = nextEnd;
                }
            } else {
                currRange = currEnd - currStart + 1;
                totalFreshCount += currRange;
                System.out.println(String.format("++Adding: [%d..%d] / |d| = %d",
                                                        currStart, currEnd, currRange));
                currStart = nextStart;
                currEnd = nextEnd;
            } 

        }
        currRange = currEnd - currStart + 1;
        totalFreshCount += currRange;
        System.out.println(String.format("++Adding: [%d..%d] / |d| = %d",
                                                currStart, currEnd, currRange));


        System.out.println("totalFreshCount: " + totalFreshCount);
    }
}
