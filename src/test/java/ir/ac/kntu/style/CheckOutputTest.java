package ir.ac.kntu.style;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import ir.ac.kntu.AngryFariborz;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 110 points
 * @author mhrimaz, sfathi
 */
public class CheckOutputTest {
    private final static int TIME_LIMIT = 100_000;

    static {
        System.err.println("$$$GRADER$$$ | { type:\"MSG\" , key:\"TOTAL\" , value:150, priority:1  }  | $$$GRADER$$$");
    }


    private final static String SEPARATOR = "\n";
    

    private static boolean doSimulation(String inputs, String expected) {
        InputStream inputStream = null;
        PrintStream printStream = null;
        try {
            File input = new File("input.txt");
            input.createNewFile();
            File output = new File("output.txt");
            output.createNewFile();
            inputStream = new FileInputStream(input);
            printStream = new PrintStream(output);
            try (PrintWriter w = new PrintWriter(input)) {
                w.println(inputs);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
                System.setIn(inputStream);
                System.setOut(printStream);
                AngryFariborz.main(new String[]{});
                String found = reader.lines().map(line->line.trim().replaceAll("[\\r\\n\\s]+","")).sorted().collect(Collectors.joining(" "));
                if(found == null){
                    found = "";
                }
                System.err.println("found+\"\\\"\" = " + found+"\"");
                String[] splited = expected.split("[\\r\\n\\s]+");
                Arrays.sort(splited);
                String expectedOutput = String.join(" ",splited);
                System.err.println("expectedOutput = " + expectedOutput);
                return found.equals(expectedOutput);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                printStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }



    @Test(timeout = TIME_LIMIT)
    public void testCase1(){
        assertTrue(doSimulation("6", "NO"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:10 , reason:\"Your Output is correct.\" } | $$$GRADER$$$" );
    }

    @Test(timeout = TIME_LIMIT)
    public void testCase2(){
        assertTrue(doSimulation("94", "YES"+SEPARATOR+"(9+4)$13"+SEPARATOR+"(9-4)$5"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:100 , reason:\"Your Output is correct.\" } | $$$GRADER$$$" );
    }


}
