package ir.ac.kntu.style;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import ir.ac.kntu.AngryFariborz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 120 points
 *
 * @author mhrimaz
 */
public class CheckOutputTest {
    private final static int TIME_LIMIT = 1_000_000;

    static {
        System.err.println("$$$GRADER$$$ | { type:\"MSG\" , key:\"TOTAL\" , value:200, priority:1  }  | $$$GRADER$$$");
    }


    private final static String SEPARATOR = "\n";

    private static String runMainMethod(Method main, String inputs, String[] args)
            throws IOException, InvocationTargetException, IllegalAccessException {

        File input = new File("input.txt");
        input.createNewFile();
        File output = new File("output.txt");
        output.createNewFile();
        try (InputStream inputStream = new FileInputStream(input); PrintStream printStream = new PrintStream(output);) {

            try (PrintWriter w = new PrintWriter(input)) {
                w.println(inputs);
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
                System.setIn(inputStream);
                System.setOut(printStream);
                main.invoke(null,(Object) args);
                String found = reader.lines().map(line -> line.trim()
                        .replaceAll("[\\r\\n\\s]+", ""))
                        .sorted()
                        .collect(Collectors.joining(" "));
                return found;
            }
        }
    }

    private static boolean doSimulation(String inputs, String expected) {
        return doSimulation(inputs,expected,false);
    }

    private static boolean doSimulation(String inputs, String expected, boolean onlyContains) {
        Method main;
        try {
            main = AngryFariborz.class.getMethod("main", String[].class);
        } catch (Exception e) {
            return false;
        }
        String outputs = null;
        try {
            outputs = runMainMethod(main, inputs, null);
        } catch (Exception e) {
            System.err.println("e = " + e);
            return false;
        }

        String[] split = expected.split("[\\r\\n\\s]+");
        Arrays.sort(split);
        String expectedOutput = String.join(" ", split);
        if(onlyContains){
            return outputs.contains(expected);
        }else{
            return outputs.equals(expectedOutput);
        }
    }
    @Test(timeout = TIME_LIMIT)
    public void testCase0() {
        boolean constantReturn = false;
        constantReturn = constantReturn || doSimulation("4", "YES",true);
        constantReturn = constantReturn || doSimulation("6", "YES",true);
        constantReturn = constantReturn || doSimulation("8", "YES",true);
        constantReturn = constantReturn || doSimulation("9", "YES",true);
        constantReturn = constantReturn || doSimulation("10", "YES",true);
        if(constantReturn) {
            System.err.println("$$$GRADER$$$ | { type:\"MSG\" , key:\"FATAL_FAIL\" , value:\"CONTACT YOUR TA!\" , priority:1 }| $$$GRADER$$$");
        }
        assertFalse(constantReturn);

    }


    @Test(timeout = TIME_LIMIT)
    public void testCase1() {
        assertTrue(doSimulation("6", "NO"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:5 , reason:\"The Number is Not Magical.\" } | $$$GRADER$$$");
    }

    @Test(timeout = TIME_LIMIT)
    public void testCase2() {
        assertTrue(doSimulation("5", "YES",true));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:5 , reason:\"The Number is Magical.\" } | $$$GRADER$$$");
    }

    @Test(timeout = TIME_LIMIT)
    public void testCase3() {
        assertTrue(doSimulation("94","YES",true));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:5 , reason:\"The Number is Magical.\" } | $$$GRADER$$$");


        boolean eq1 = false;
        boolean eq2 = false;
        boolean eq3 = false;

        eq1 = eq1 || doSimulation("94", "(9+4)$13" ,true);
        eq1 = eq1 || doSimulation("94", "((9)+(4))$13" ,true);
        eq1 = eq1 || doSimulation("94", "9+4$13" ,true);
        eq1 = eq1 || doSimulation("94", "(9)+(4)$13" ,true);


        eq2 = eq2 || doSimulation("94", "(9-4)$5" ,true);
        eq2 = eq2 || doSimulation("94", "((9)-(4))$5" ,true);
        eq2 = eq2 || doSimulation("94", "9-4$5" ,true);
        eq2 = eq2 || doSimulation("94", "(9)-(4)$5" ,true);

        eq3 = eq3 || doSimulation("94", "(9/4)$2" ,true);
        eq3 = eq3 || doSimulation("94", "((9)/(4))$2" ,true);
        eq3 = eq3 || doSimulation("94", "9/4$2" ,true);
        eq3 = eq3 || doSimulation("94", "(9)/(4)$2" ,true);



        assertTrue(eq1);
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:15 , reason:\"First Equation is TRUE.\" } | $$$GRADER$$$");
        assertTrue(eq2);
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:15 , reason:\"Second Equation is TRUE.\" } | $$$GRADER$$$");
        assertTrue(eq3);
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:15 , reason:\"Third Equation is TRUE.\" } | $$$GRADER$$$");
    }

    @Test(timeout = TIME_LIMIT)
    public void testCase4() {
        assertTrue(doSimulation("941","YES",true));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:10 , reason:\"The Number is Magical.\" } | $$$GRADER$$$");
    }

    @Test(timeout = TIME_LIMIT)
    public void testCase5() {
        assertTrue(doSimulation("9156747","YES",true));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:10 , reason:\"The Number is Magical.\" } | $$$GRADER$$$");

    }


    @Test(timeout = TIME_LIMIT)
    public void testCase6() {
        assertTrue(doSimulation("20","YES",true));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:10 , reason:\"The Number is Magical.\" } | $$$GRADER$$$");

        boolean eq1 = false;
        boolean eq2 = false;

        eq1 = eq1 ||doSimulation("20", "(2+0)$2" ,true);
        eq1 = eq1 ||doSimulation("20", "((2)+(0))$2" ,true);
        eq1 = eq1 ||doSimulation("20", "2+0$2" ,true);
        eq1 = eq1 ||doSimulation("20", "(2)+(0)$2" ,true);

        eq2 = eq2 ||doSimulation("20", "(2-0)$2" ,true);
        eq2 = eq2 ||doSimulation("20", "2-0$2" ,true);
        eq2 = eq2 ||doSimulation("20", "(2)-(0)$2" ,true);
        eq2 = eq2 ||doSimulation("20", "((2)-(0))$2" ,true);


        assertTrue(eq1);
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:15 , reason:\"First Equation is TRUE.\" } | $$$GRADER$$$");
        assertTrue(eq2);
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:15 , reason:\"Second Equation is TRUE.\" } | $$$GRADER$$$");
    }


}
