////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    Candidate
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
///////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * This class holds the Canidate object that has a name and office
 * 
 * @author Connor Phillips
 * 
 */
public class Candidate
{
    protected static final String[] OFFICE={"President", "Vice President", "Secretary"};
    private String name;
    private String office;
    /**
     * Constructor for Candidate object
     * @param name is the name of the candidate
     * @param office is the name of the office a candidate is running for
     */
    public Candidate(String name, String office)
    {
        this.name=name;
        boolean check=false;
        for (int i=0;i<OFFICE.length;i++)
            if(OFFICE[i].equals(office))
                check=true;
        if(!check)
            throw new IllegalArgumentException("The office is not valid");
        this.office=office;
    }

    /**
     * Retrieves the name of the candidate
     * @return string value of the candidate's name
     */
    public String getName()
    {
        return name;   
    }

    /**
     * Retrieves the office of the candidate
     * @return string value of the candidate's office
     */
    public String getOffice()
    {
        return office;   
    }

    /**
     * Retrieves the toString of the candidate
     * @return string representation of the candidate's name and office
     */
    public String toString()
    {
        return name+" ("+office+")";   
    }
}