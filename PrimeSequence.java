/*=======================================================================
|   Source code:  PrimeSequence.java
|
|                 Class: PrimeSequence
|
|    Assignment:  Assignment 5 craps Sequence of Primes + EXTRA CREDIT
|
|        Course: COP 3337 (Intermediate Programming)
|        Section: U06
|
|
|
|        Language:  Java
|        Compile:
|                javac PrimeSequence.java Sequence.java
|
|
|    Purpose: To provide a prime sequences
|
|
|    Inherits From:  None
|
|
|     Interfaces:  Sequence
|
|
|  +-----------------------------------------------------------------------
|
|      Constants:  There are no public constants.
|
|
| +-----------------------------------------------------------------------
|
|   Constructors:  PrimeSequence(int startNumber) - Constructor that sets start number
|                  PrimeSequence(s) - Empty constructor which sets default start number
|
|  Class Methods:  No private class methods
|
|
|  Instance Methods:  next() Returns an int
|                     validateStartNumber(int number) Returns an int
|                     isPrime(int number) Returns a boolean
|                     setStartNumber(int number) Void
|                     setOverflowValue(int number) void
|                     getOverflowValue() returns an int
|
|  *===========================================================================*/


public class PrimeSequence implements Sequence
{


    /*
       2 is the first prime number. The "next" method displays
       the prime number after the start number thus 1 is the first start number number.
     */
    private final int FIRST_START_NUMBER = 1;
    private final int LAST_PRIME_BEFORE_OVERFLOW = 2147483647;

    private int startNumber = FIRST_START_NUMBER;
    private boolean overflow = false;
    private final int OVERFLOW_VALUE = 0;



    /**
     * A constructor that sets the start number
     *
     * @param startNumber number to start
     */
    public PrimeSequence(int startNumber)
    {
        this.startNumber = validateStartNumber(startNumber);
    }

    /**
     * A constructor that sets a default start number
     */
    public PrimeSequence()
    {
        startNumber = FIRST_START_NUMBER;
    }


    /**
     * Method that determines the next prime after the start number
     * @return If applicable, the next prime number
     */
    @Override
    public int next()
    {
        int nextPrime = startNumber;

        if(nextPrime == LAST_PRIME_BEFORE_OVERFLOW )
        {
            overflow = true;
        }

        if(!overflow )
        {
            do
            {
                nextPrime++;

            }while(!isPrime(nextPrime));

            return nextPrime;

        }else
        {
            return OVERFLOW_VALUE;
        }
    }



    /**
     * Validates user input to check for most efficient starting value.
     *
     * i.e Primes are positive. A start value of -900 would result in the first prime being 2
     *     thus a start value of 1 would accomplish the same result and take less computation.
     *
     * @param number starting value specified
     * @return A validated starting value
     */
    public int validateStartNumber(int number)
    {

        if(number >= FIRST_START_NUMBER)
        {
            return number;

        }else
        {
            return FIRST_START_NUMBER;
        }
    }




    /**
     * Determiens if a number is prime
     * Prime definition: A whole number greater than 1 that can not be made by multiplying other whole numbers.
     *                   Definition from: https://www.mathsisfun.com/definitions/prime-number.html
     *
     * Note:
     *
     *      The algorithm used was taken from:
     *
     *      https://stackoverflow.com/questions/2385909/what-would-be-the-fastest-method-to-test-for-primality-in-java
     *
     *      It is marked as the accepted answer and received the most up votes out of all the answers.
     *

     */



    public boolean isPrime(int numberToBeTested)
    {
        final int FIRST_PRIME = 2;
        final int SECOND_PRIME = 3;
        long sqrtN = 0;

        if(numberToBeTested < 2)
        {
            return false;
        }

        if(numberToBeTested == FIRST_PRIME || numberToBeTested == SECOND_PRIME)
        {
            return true;
        }

        // By definition if number % 2 = 0 or number % 3 = 0 then not prime
        if(numberToBeTested % 2 == 0 || numberToBeTested % 3 == 0)
        {
            return false;
        }


        sqrtN = (long) Math.sqrt(numberToBeTested) + 1;

        for(long i = 6L; i <= sqrtN; i += 6)
        {
            if(numberToBeTested % (i - 1) == 0 || numberToBeTested % (i + 1) == 0) return false;
        }

        return true;
    }


    /**
     * Setter method for start number
     *
     * @param number number to be set
     */
    public void  setStartNumber(int number)
    {
        startNumber = validateStartNumber(number);
    }


    /**
     * Get the overflow number
     * @return overflowValue value of overflow
     */
    public int  getOverflowValue()
    {
        return OVERFLOW_VALUE;
    }

}