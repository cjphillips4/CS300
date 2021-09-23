////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    Ballot
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
 * This class holds the Ballot object that has static and instance methods
 *     Has a static array list parties and static int counter.  
 *     Instance variable votes and final int ID
 * 
 * @author Connor Phillips
 * 
 */
public class Ballot
{
    private static ArrayList<Party> parties=new ArrayList<Party>();
    private static int counter=0;
    /**
     * adds a party to the static parties array
     * @param p the Party to be added to parties
     */  
    public static void addParty(Party p)
    {
        for(int i=0;i<parties.size();i++)
        {
            if(parties.get(i).getName().equals(p.getName()))
                return;
        }   
        parties.add(p);   
    }

    /**
     * Creates an array of all candidates running for a certain office
     * @param office a String value for the office to find candidates for
     * @return an array list of candidates that are running for the given office
     */
    public static ArrayList<Candidate> getCandidates(String office)
    {
        ArrayList<Candidate> list=new ArrayList<Candidate>();
        for (int i=0;i<parties.size();i++)
        {
            try
            {
                list.add(parties.get(i).getCandidate(office));
            }
            catch(NoSuchElementException e)
            {
                continue;
            }
        }
        return list;    
    }

    private Candidate[] votes;
    private final int ID;
    /**
     * Constructor for Ballot object
     */
    public Ballot()
    {
        ID=counter;
        votes=new Candidate[3];
        counter++;
    }

    /**
     * Retrieves the candidate that was voted for in an office
     * @param office String value of the office a canidate voted for
     * @return Canidate that was voted for in a specific office, null if no one was voted for
     */
    public Candidate getVote(String office)
    {
        for (int i=0;i<votes.length;i++)
        {
            if(votes[i]!=null && votes[i].getOffice().equals(office))
                return votes[i];
        }
        return null;     
    }

    /**
     * Retrieves the ID of the ballot
     * @return int value of the ballot ID
     */
    private int getID()
    {
        return ID; 
    }

    /**
     * Checks if an object is equal to the ballot
     * @param o an Object to be tested as a ballot and check if the ballots have the same ID
     * @return true if the ballots are equal, false if the the object isn't a ballot or
     *     ballots don't share the same ID
     */
    @Override
    public boolean equals(Object o)
    {
        Ballot c; 
        try{
            c=(Ballot) o;
        }
        catch(ClassCastException e1)
        {
            return false;  
        }
        if(c.getID()==ID)
            return true;
        return false;
    }  

    /**
     * sets a value of the votes array to the candidate they want to vote for
     * 
     * @param c the Candidate the ballot wants to vote for
     */
     
    public void vote(Candidate c)
    {
        if (c.getOffice().equals("President"))
            votes[0]=c;
        else if (c.getOffice().equals("Vice President"))
            votes[1]=c;
        else if (c.getOffice().equals("Secretary"))
            votes[2]=c;
    }
}