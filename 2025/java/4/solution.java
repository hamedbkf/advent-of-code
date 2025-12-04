import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

class solution {
    public static void main (String args[]) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(Path.of("input"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for ( String line : lines )
            System.out.println(line);

        char[][] rolls = lines.stream().map(String::toCharArray).toArray(char[][]::new);

        int inputHeight = lines.size();
        int inputWidth  = lines.get(0).length();

        int height = inputHeight + 2;
        int width  = inputWidth + 2;

        // don't panic. this isn't c. it's dynamically allocated
        byte[][] grid = new byte[height][width];

        int accessible = 0;
        boolean removed = true;

        // for part 1 solution: just remove the while loop and "removed" variable
        while ( removed ) {
            removed = false;

            for ( int i = 0; i < height; i++ ) {
                for ( int j = 0; j < width; j++ ) {
                    if ( i == 0 || j == 0 || i == height-1 || j == width-1 )
                        grid[i][j] = 0;
                    else
                        grid[i][j] = (byte)( ( rolls[i-1][j-1] == '@' ) ? 1 : 0 );
                }
            }


            for ( int i = 1; i < height-1; i++ ) {
                for ( int j = 1; j < width-1; j++ ) {
                    int adjRolls = grid[i-1][j-1] + grid[i-1][j  ] + grid[i-1][j+1]
                                 + grid[i  ][j-1]                  + grid[i  ][j+1]
                                 + grid[i+1][j-1] + grid[i+1][j  ] + grid[i+1][j+1];
                    if ( grid[i][j] == 1 && adjRolls < 4 ) {
                        accessible++;
                        rolls[i-1][j-1] = 'x';
                        grid[i][j] = 0;
                        removed = true;
                    }
                    //
                    //System.out.print(grid[i][j]);
                    System.out.print(adjRolls);
                }
                System.out.println();
            }


            for ( int i = 0; i < inputHeight; i++ ) {
                for ( int j = 0; j < inputWidth; j++ )
                    System.out.print(rolls[i][j]);
                System.out.println();
            }

        }
            

        System.out.println("Accessible: " + accessible);


    }
   
}
