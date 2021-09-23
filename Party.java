////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    Party
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
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * This class holds the Party object that has a name and list of Candidates
 * 
 * @author Connor Phillips
 * 
 */
public class Party
{
    private String name;
    private ArrayList<Candidate> candidates;
    /**
     * Constructor for Party object
     * 
     * @param name is the name of the party
     */
    public Party(String name)
    {
        candidates=new ArrayList<Candidate>();
        this.name=name; 
    }

    /**
     * Adds a candidate to the party in the correct office
     * @throws IllegalArgumentException if someone is already running for that office in the party
     */
    public void addCandidate(Candidate c)
    {        
        for(int i=0;i<candidates.size();i++)
            if(candidates.get(i).getOffice().equals(c.getOffice()))
                throw new IllegalArgumentException("Someone is already running for this office in "
                    + "this party.");
        candidates.add(c);
    }

    /**
     * Retrieves the name of the party
     * @return string value of the party's name
     */
    public String getName()
    {
        return name; 
    }

    /**
     * Retrieves the size of the candidate array
     * @return string value of the candidate's name
     */
    public int getSize()
    {
        return candidates.size(); 
    }

    /**
     * Retrieves the candidate in the party who is running for given office
     * @param office String value of the office being searched for
     * @return Candidate that is running for given office
     * @throws NoSuchElementException if no candidate is running for given office in the party
     */
    public Candidate getCandidate(String office)
    {
        for (int i=0;i<candidates.size();i++)
        {
            if(candidates.get(i).getOffice().equals(office))
                return candidates.get(i);
        }
        throw new NoSuchElementException("No candidate is running for this office in this party"); 
    }
}