// Lecturer: Mouna Kacem
//
////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
////////////////////////////////////////////////////////////////////////////////////////////////////
import  java.io.FileWriter;
import  java.io.File;
import  java.util.NoSuchElementException;
import  java.io.IOException;
public class Benchmarker
{
    /**
     * Compares the output of the bruteForce and constantTime methods to make sure 
     *    their outputs are equal.  Also calculates the runtimes for each method
     * 
     * @param n is the number that will be passed into the bruteForce and constantTime methods
     * @return a String that contains the n value, runtime of bruteForce, and runtime of constantTime
     * @throws NoSuchElementException if the outputs of the comparison methods are not equal
     */
    public static String compare(long n)
    {
        long start1=System.currentTimeMillis();
        long storeBrute=ComparisonMethods.bruteForce(n);
        //end1 stores both the time bruteForce ends and constantTime starts
        long end1=System.currentTimeMillis();
        long storeConstant=ComparisonMethods.constantTime(n);
        long end2=System.currentTimeMillis();
        if(storeBrute!=storeConstant)
            throw new NoSuchElementException("The comparison method outputs do not match");
        return n+"\t"+(end1-start1)+"\t"+ (end2-end1)+"\n";   
    }

    /**
     * Uses a FileWriter to write the results of a series of compare method outputs
     * 
     * @param f the File to be written in
     * @param queryNs the array of longs to be passed into the compare method
     */
    public static void createResultsFile(File f,long[] queryNs) 
     {
       FileWriter fw;
        try
        {
            fw=new FileWriter(f);
        }
        catch(IOException e1)
        {
            System.out.println("Exception encountered, unable to complete method.");
            return;
        }
        for(int i=0;i<queryNs.length;i++)
        {
            try
            {
                fw.write(compare(queryNs[i]));
            }
            catch(NoSuchElementException e2)
            {
                System.out.println(e2.getMessage());   
            }
            catch(IOException e3)
            {
                System.out.println("Exception encountered while writing for value N ="+queryNs[i]);  
            }
        }
        try
        {
            fw.close();
        }
        catch(IOException e3)
        {
            System.out.println("Exception encountered while closing file");
            return;   
        }
    }

    /**
     * The main method to execute the other Benchmarker methods
     */
    public static void main(String[] args)
    {
        long[] input={344444444,300000,5453};
        File f=new File("Comparisons");
        createResultsFile(f,input);
    }
}