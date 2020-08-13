/*=============================================================================
|    Source code: SequenceDemo.java
|    Assignment:  Assignment 5 Sequence of Primes + EXTRA CREDIT
|
|        Course:  COP 3337 (Intermediate Programming)
|        Section:  U06
|
|
|
|       Language: Java
|       Compile/Run:
|
|       javac SequenceDemo.java PrimeSequence.java Sequence.java
|       java SequenceDemo input1 input2
|
|  +-----------------------------------------------------------------------------
|
|  Description:  This program analyzes a user set number of primes
|
|
|       Input:    (Command line input)
|                 [Starting number] The next prime after the starting number will be picked
|                 [Primes to be sequenced]
|
|       Output:   The program displays prime numbers and information about final digit occurrence.
|
|       Process:  The program's steps are as follows:
|
|                      1.  The user runs the program on the command line specifying a start value and primes to be sequenced
|                          that is greater than or equal to 1.
|
|                           i.e java SequenceDemo 2 25
|
|                      2.  The program constructs prime amount determined by primes to sequence
|
|
|                      3.  The program displays the information about the primes generated
|
|
|                 No particular algorithms are used in the SequenceDemo class
|
|
|   Required Features Not Included: All required features are included.
|
|   Known Bugs:  None; the program operates correctly.
|
|  *===========================================================================*/


import java.util.Scanner; // used for validating command line input

public class SequenceDemo
{
    static final int ROW_INDEX = 0;
    static final int COLUMN_INDEX = 1;

    final static int MAX_PERCENTAGE_INDEX = 0;
    final static int PERCENTAGE_INDEX = 1;

    final static int START_NUMBER_INDEX = 0;
    final static int AMOUNT_OF_PRIMES_INDEX = 1;

    final static int NON_NUMBER_IN_INPUT_CODE = -2;
    final static int INVALID_ARGS_LENGTH_CODE = -1;
    final static int VALID_ARGS_CODE = 1;



    final static int LAST_INDEX = 1; // Used as - 1 to get last index



    public static void main(String[] args)
    {

       boolean validInputLength = validLengthArgs(args);

       if(!validInputLength)
       {
           displayInvalidInputComment();
       }else
       {
           Scanner startNumber = new Scanner(args[START_NUMBER_INDEX]);
           Scanner amountOfPrimes = new Scanner(args[AMOUNT_OF_PRIMES_INDEX]);

           PrimeSequence primeInfo = new PrimeSequence();

           runProgram(startNumber, amountOfPrimes, args, primeInfo);
       }

    }

    /**
     * Checks to see if there is sufficient command line input
     *
     * @param argsInput command line inputs
     * @return boolean value denoting if there is sufficient input
     */

    public static boolean validLengthArgs(String[] argsInput)
    {
        final int EMPTY_ARRAY = 0;
        final int INSUFFICIENT_INPUT = 1;

        return (!((argsInput.length == EMPTY_ARRAY) || (argsInput.length == INSUFFICIENT_INPUT)));
    }

    /**
     *
     * Determines if the input is valid. If it is then it runs the program
     * If the input is invalid then it will display invalid input information.
     *
     * @param startNum User specified start number
     * @param primeAmount User specified prime amount
     * @param args user command line input
     * @param primeAnalysis primeSequence object
     */
    public static void runProgram(Scanner startNum, Scanner primeAmount, String[] args, PrimeSequence primeAnalysis)
    {
        int inputCode = validateCommandLineInput(startNum, primeAmount);
        int startNumber = 0;
        int amountOfPrimes = 0;

        if(inputCode == VALID_ARGS_CODE)
        {
             startNumber =  Integer.parseInt(args[START_NUMBER_INDEX]);
             amountOfPrimes = Integer.parseInt(args[AMOUNT_OF_PRIMES_INDEX]);

            displayInformation(primeAnalysis, startNumber, amountOfPrimes);
        }else
        {
            displayInvalidInputComment();
        }
    }


    /**
     * Determines if the command line inputs are ints >= 1
     *
     * @param input1 Scanner object of args[START_NUMBER_INDEX]
     * @param input2 Scanner object of args[AMOUNT_OF_PRIMES_INDEX]
     * @return code denoting the success or specific failure of input
     */
    public static int  validateCommandLineInput(Scanner input1, Scanner input2)
    {

        final int MINIMUM_INPUT = 1;

        if(!input1.hasNextInt() || !input2.hasNextInt()) // Not an Int
        {
            return NON_NUMBER_IN_INPUT_CODE;

        }else
        {
            if(input1.nextInt() >= MINIMUM_INPUT && input2.nextInt() >= MINIMUM_INPUT) // 1 -
            {
                return VALID_ARGS_CODE;

            }else
            {
                return INVALID_ARGS_LENGTH_CODE;
            }
        }
    }


    /**
     * Calls the required methods to display output
     *
     * @param primeSeqObject primeSequence object
     * @param startNumber User specified start number
     * @param numberOfSequence user specified number of sequence
     */
    public static void displayInformation(PrimeSequence primeSeqObject, int startNumber, int numberOfSequence)
    {

        int[] primeArray = createPrimeNumberSequence(primeSeqObject, startNumber,  numberOfSequence);

        displayPrimeSequenceTable(primeArray, startNumber);
        displayHistogram(primeArray);

    }


    /**
     * Creates an array of prime numbers starting at the first prime after first start number
     *
     * @param primeSeqObj primeSeqObject primeSequence object
     * @param firstStartNumber User specified start number
     * @param numberOfSequence user specified number of sequence
     * @return an array of prime numbers
     */
    public static int[] createPrimeNumberSequence(PrimeSequence primeSeqObj, int firstStartNumber, int numberOfSequence)
    {

        int[] primeNumbers = new int[numberOfSequence];
        int startNumber = firstStartNumber;


        for (int i = 0; i < numberOfSequence; i++)
        {
            primeSeqObj.setStartNumber(startNumber);
            startNumber = primeSeqObj.next();
            primeNumbers[i] = startNumber;
        }

        return primeNumbers;
    }


    /**
     * Displays the table containing all the prime numbers generated by the user input
     *
     * @param primeNumbers An array of prime numbers to be displayed
     * @param firstStartNumber The number the user wished to start as
     */
    public static void displayPrimeSequenceTable(int[] primeNumbers, int firstStartNumber)
    {
        final int OVERFLOW_ERROR = 0;
        boolean displayOverflowLength = false;
        int[] tableDimensions = squareTableDimensions(primeNumbers.length);
        int start_next_row_counter = 0;
        int spacesToAlignNumbers = 0;


        System.out.println("prime Sequence Table:");
        System.out.println("Printing a sequence of " + primeNumbers.length + " prime numbers, starting with " +
                "the first prime after " + firstStartNumber + ":");

        if(primeNumbers[primeNumbers.length - LAST_INDEX] == OVERFLOW_ERROR)
        {
            spacesToAlignNumbers = Integer.toString(primeNumbers[0]).length();
            displayOverflowLength = true;
        }


        for (int primeNumber : primeNumbers)
        {
            if(!displayOverflowLength)
            {
                spacesToAlignNumbers = Integer.toString(primeNumbers[primeNumbers.length - LAST_INDEX]).length();
            }

            System.out.printf("%" + spacesToAlignNumbers + "d %3s", primeNumber, "");
            start_next_row_counter++;

            if (start_next_row_counter == tableDimensions[COLUMN_INDEX])
            {
                start_next_row_counter = 0;
                System.out.println();
            }

        }
    }

    /**
     * Displays the histogram
     *
     * @param primeNumbers An array of prime numbers to be displayed
     */

    public static void displayHistogram(int[] primeNumbers)
    {

        final int DEFAULT_ASTERISK_SPACE = 10;
        final int MAX_PERCENT_PAIR_LENGTH_INDEX = 11;
        final int STRAIGHT_BAR_PADDING = 8;
        final int TOTAL_TEXT_PADDING = 4;
        final int EXTRA_PADDING = 1;

        int asteriskSpace = DEFAULT_ASTERISK_SPACE;
        int spaceRightOfTotalText = 0;

        int occurrenceInPercentage = 0;
        int totalOccurrencePercentage = 0;

        int[] digitOccurrences = lastDigitOccurrence(primeNumbers);
        int[][] digitPercentInformation = digitPercentageInformation(digitOccurrences, primeNumbers);

        int maxOccurrencePercentage = digitPercentInformation[MAX_PERCENTAGE_INDEX][0];

        int totalDigitOccurrence = primeNumbers.length;

        String totalDisplayText = "Total(actual count, %%)";
        String percentPairSpace = "";
        String spaceRightOfBar = "";
        String asteriskBar = "";
        String straightBar = "";
        String asteriskPercentage = "1";

        String percentPairInfo[] = percentPairFormatInformation(digitOccurrences, digitPercentInformation[PERCENTAGE_INDEX]);

        int longestPercentPairLength = Integer.valueOf(percentPairInfo[MAX_PERCENT_PAIR_LENGTH_INDEX]);

        if(maxOccurrencePercentage >= DEFAULT_ASTERISK_SPACE)
        {
            asteriskSpace = digitPercentInformation[MAX_PERCENTAGE_INDEX][0] + EXTRA_PADDING;
        }


        System.out.println("\n\nLast Digit Histogram: ");
        System.out.println("Scaled as %, each * = " + asteriskPercentage + "%");
        System.out.println("Total count may vary slightly from 100% due to rounding percentages \n");

        for(int digit = 0; digit < digitOccurrences.length; digit++)
        {
            occurrenceInPercentage = digitPercentInformation[PERCENTAGE_INDEX][digit];

            asteriskBar = repeatWord(occurrenceInPercentage, "*"); // * decoration

            spaceRightOfBar = dynamicSpaceRequired(asteriskSpace, asteriskBar);

            percentPairSpace = dynamicSpaceRequired((longestPercentPairLength + EXTRA_PADDING), percentPairInfo[digit]);

            System.out.printf("[%d]%s %s", digit, asteriskBar, spaceRightOfBar );
            System.out.printf("(%d,%s% d%%)", digitOccurrences[digit], percentPairSpace,  occurrenceInPercentage);
            System.out.println();

            totalOccurrencePercentage += occurrenceInPercentage;
        }

        straightBar = repeatWord(asteriskSpace + STRAIGHT_BAR_PADDING + longestPercentPairLength,"_"); // _ decoration
        spaceRightOfTotalText = ((asteriskSpace + TOTAL_TEXT_PADDING) - totalDisplayText.length());

        System.out.println(straightBar);

        System.out.printf(totalDisplayText + "%" + spaceRightOfTotalText + "s" + " " + "(%d,%d%%)", "" ,
                totalDigitOccurrence, totalOccurrencePercentage);

        System.out.println("\n+Histogram entries in (0) row indicate overflow results");
    }


    /**
     * Information needed to format the percent pair in the histogram
     *
     * @param digitOccurrence number of times a digit occurred
     * @param percentageOfOccurrence percentage of digit occurred
     *
     * @return A string containing information required for formatting
     */

    public static String[] percentPairFormatInformation(int[] digitOccurrence, int[] percentageOfOccurrence)
    {
        final int AMOUNT_OF_INFO = 12;
        int maxPercentPairLength = 0;

        String[] percentPairInfoForFormatting =  new String[AMOUNT_OF_INFO];
        String formatInfo = "";

        for(int digit = 0; digit < digitOccurrence.length; digit++)
        {
             formatInfo = String.format("(%d, %d%%)", digitOccurrence[digit], percentageOfOccurrence[digit]);
            percentPairInfoForFormatting[digit] = formatInfo;
        }

        maxPercentPairLength = percentPairInfoForFormatting[0].length(); // 0 first element

        for(int i = 1; i < digitOccurrence.length; i++)
        {
            if(percentPairInfoForFormatting[i].length() > maxPercentPairLength)
            {
                maxPercentPairLength = percentPairInfoForFormatting[i].length();
            }
        }

        percentPairInfoForFormatting[AMOUNT_OF_INFO - LAST_INDEX] = String.valueOf(maxPercentPairLength);

        return percentPairInfoForFormatting;
    }


    /**
     * Repeats a string a specified number of times
     *
     * @param amountOfWord The number of times the word will be repeated
     * @param word Word to be repeated
     *
     * @return repeatedWord A staining containing the user specified word repeated amountOfWord times
     */

    public static String repeatWord(int amountOfWord, String word)
    {
        String repeatedWord = "";

        for(int i = 0; i < amountOfWord; i++)
        {
            repeatedWord += word;
        }
        return repeatedWord;
    }

    /**
     * Calculates the amount of spaces needed to output the data inline with the other data
     *
     * @param spaceRequired The desired amount of space each output should have
     * @param value The data that will be outputted
     *
     * @return A formatted string of the space required to keep the data inline
     */

    public static String dynamicSpaceRequired(int spaceRequired, String value)
    {

        int lengthOfValue = String.valueOf(value).length();
        int newSpaceRequired = (spaceRequired - lengthOfValue);

        String numberOfSpace = String.valueOf(newSpaceRequired);
        String percentFormat = "%" + numberOfSpace + "s";

        return String.format(percentFormat, "");

    }

    /**
     * Determent the row and col needed to make a square table with a set number of elements
     *
     * @param numOfSequence number of elements to be squared
     * @return dimensions array with values for a square dimension
     */

    public static int[] squareTableDimensions(int numOfSequence)
    {

        int row = 0;
        int col = 0;
        int[] dimensions = {row, col};

        final int MAX_COLUMN = 10;
        final int MAX_COLUMN_AFTER_ELEMENT = 100;

        if(numOfSequence >= MAX_COLUMN_AFTER_ELEMENT)
        {

            col = MAX_COLUMN;
            row = (int) Math.ceil(numOfSequence / col);

        }else
        {

            col = (int) Math.ceil(Math.sqrt(numOfSequence));
            row =  col ;

        }

        dimensions[ROW_INDEX] = row;
        dimensions[COLUMN_INDEX] = col;

        return dimensions;
    }


    /**
     * Determines the amount of time a digit Occurred in the last position of a number
     *
     * @param primeNumbers prime numbers to be examined
     * @return digitOccurrences An array containing the number of times a last digit occurred
     */

    public static int[] lastDigitOccurrence(int[] primeNumbers)
    {
        final int AMOUNT_OF_DIGITS = 10;
        int[] digitOccurrences = new int[AMOUNT_OF_DIGITS];

        String stringInt = "";
        String lastNumber = "";

        for (int primeNumber : primeNumbers)
        {
            stringInt = Integer.toString(primeNumber);
            lastNumber = stringInt.substring(stringInt.length() - LAST_INDEX);
            digitOccurrences[Integer.parseInt(lastNumber)] += 1;
        }

        return digitOccurrences;
    }


    /**
     * Calculates percentage information needed for digits to be displayed in the histogram
     *
     * Formula:
     *      100 * (number of desired outcome  / total number of outcomes)
     *
     * @param digitOccurrences amount of times a digit occurred
     * @param primeNumbers prime numbers to be displayed
     * @return digitInformation percentage information about digits
     */

    public static int[][] digitPercentageInformation(int[] digitOccurrences, int[] primeNumbers)
    {
        final int AMOUNT_OF_INFO = 3;
        final int AMOUNT_OF_DIGITS  = 10;

        int[] percentageOfOccurrence = new int[AMOUNT_OF_DIGITS];
        int[][] digitInformation = new int[AMOUNT_OF_INFO][];

        int[] maxPercentage = {(int) Math.ceil(100 * ( (float) digitOccurrences[0] / primeNumbers.length))};


        for(int i = 0; i <  AMOUNT_OF_DIGITS; i++)
        {

            percentageOfOccurrence[i] = (int) Math.ceil(100 * ( (float) digitOccurrences[i] / primeNumbers.length));

            if(percentageOfOccurrence[i] > maxPercentage[0])
            {
                maxPercentage[MAX_PERCENTAGE_INDEX] = percentageOfOccurrence[i];
            }

        }

        digitInformation[MAX_PERCENTAGE_INDEX] = maxPercentage;
        digitInformation[PERCENTAGE_INDEX] = percentageOfOccurrence;

        return digitInformation;
    }

    /**
     * Displays error and exits
     */
    public static void displayInvalidInputComment()
    {
        System.out.println();
        System.out.println("one or more of the two\n" +
                "inputs were invalid - a valid input would greater than or equal to 1 for\n" +
                "each of the two inputs.");

        System.out.println();

        System.exit(1);

    }

}