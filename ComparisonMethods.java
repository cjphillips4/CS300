////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    ComparisonMethods
// Course:   CS 300 Fall 2020
//
// Author:   Connor Phillips
// Email:    cjphillips4@wisc.edu
// Lecturer: Mouna Kacem
//
////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public class ComparisonMethods
{
   /**
    * ​Complexity: O(N) 
    */
   /**
     * Finds the sum of the number 1 through n using a loop strucure
     * 
     * @param n is the number that 1 and all consecutive numbers will be added up to
     * @return long value of the sum of 1 through n
     */
    public static long bruteForce(long n)
    {
        long sum=0;
        for(int i=1;i<=n;i++)
        {
            sum+=i;
        }
        return sum;
    }
    
    
    /**
    * ​Complexity: O(1) 
    */
    /**
     * Finds the sum of the number 1 through n using a formula
     * 
     * @param n is the number that 1 and all consecutive numbers will be added up to
     * @return long value of the sum of 1 through n
     */
    public static long constantTime(long n)
    {  
        return (long)(n*(n+1))/2;          
    }
}