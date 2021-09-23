////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    MessageStackIterator
//Course:   CS 300 Fall 2020
//
//Author:   Connor Phillips
//Email:    cjphillips4@wisc.edu
//Lecturer: Mouna Kacem
//
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Persons:         NONE
//Online Sources:  NONE
//
////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class Creates a MessageStackIterator 
 */
public class MessageStackIterator implements Iterator <Message>
{
    private LinkedNode<Message> cur;
    /**
     * Contructor for the MessageStackIterator Object
     * @param head the LinkedNode to start the stack
     */
    public MessageStackIterator(LinkedNode<Message> head)
    {
        this.cur=head;   
    }

    /**
     * Gets the next message in the stack
     * @return Message that is next in the stack
     */
    public Message next()
    {
        if(cur==null)
            throw new NoSuchElementException("There is not another message.");
        Message send=cur.getData();
        cur=cur.getNext();
        return send;
    }

    /**
     * finds if the item in stack has a next value
     * @return true if it does have a next value,false otherwise
     */
    public boolean hasNext()
    {
        if(cur!=null)
            return true;
        return false;
    }
}