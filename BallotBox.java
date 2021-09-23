////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    BallotBox
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
/**
 * This class holds the BallotBox object that counts votes and finds the winner
 * 
 * @author Connor Phillips
 * 
 */
public class BallotBox
{
    private ArrayList<Ballot> ballots;
    /**
     * Constructor for BallotBox that initializes the ballots array list
     */
    public BallotBox()
    {
      ballots=new ArrayList<Ballot>();   
    }
    
    /**
     * Retrieves the int of the number of ballots submitted
     * @return int value of ballots array list size
     */
    public int getNumBallot()
    {
        return ballots.size();
    }
    
    /**
     * Submits a given ballot to the BallotBox
     * @param b the Ballot that is being submitted
     */
    public void submit(Ballot b)
    {
       for(int i=0;i<ballots.size();i++)
       {
         if(ballots.get(i).equals(b))
            return;
        }
       ballots.add(b);
    }
    
    /**
     * Finds the winner for a certain office
     * @param office the String value of the office the BallotBox is finding the winner of
     * @return the Candidate who won for the given office
     */
    public Candidate getWinner(String office)
    {
       ArrayList<Candidate> candidates=Ballot.getCandidates(office);
       //checks that candidates are running for the given office
       if(candidates.size()==0)
         return null;
       int[] counter=new int[candidates.size()];
       //loop that counts the votes for each candidate
       for(int i=0;i<ballots.size();i++)
       {
           for (int j=0;j<candidates.size();j++)
           {
             if(ballots.get(i).getVote(office).getName().equals(candidates.get(j).getName()))
                counter[j]++;
            }
        }
       int index=0;
       int max=0;
       //finds the candidate with the most votes
       for(int i=0;i<counter.length;i++)
       {
         if(max<counter[i])
           {
               max=counter[i];
               index=i;
            }
        }
       return candidates.get(index);
    }
}