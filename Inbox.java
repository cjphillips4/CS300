////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Inbox
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
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
/**
 * This class Creates an inbox using a stack of read and unread messages
 */
public class Inbox
{
    private MessageStack readMessageBox;
    private MessageStack unreadMessageBox;

    /**
     * Constructor for Inbox Object
     */
    public Inbox()
    {
        readMessageBox=new MessageStack();
        unreadMessageBox=new MessageStack();
    }

    /**
     * reads the message in unread and moves it to read
     * @return the String value of the message
     */
    public String readMessage()
    {
        Message hold;
        try
        {
            hold=unreadMessageBox.pop();
        }
        catch(EmptyStackException e1)
        {
            return "Nothing is unread";
        }
        readMessageBox.push(hold);
        return hold.getSUBJECT()+": "+hold.getTEXT();
    }

    /**
     * peeks at the top message in read messages
     * @return the string value at the top of read messages
     */
    public String peekReadMessage()
    {
        Message hold;
        try
        {
            hold=readMessageBox.peek();
        }
        catch(EmptyStackException e1)
        {
            return "Nothing in Read";
        }
        return hold.getSUBJECT()+": "+hold.getTEXT();
    }

    /**
     * marks all unread messages as read and moves them to the read stack
     * @return the int count of how many messages were read
     */
    public int markAllMessagesAsRead()
    {
        int count=0;
        while(!unreadMessageBox.isEmpty())
        {
            readMessageBox.push(unreadMessageBox.pop());
            count++;
        }
        return count;
    }

    /**
     * Pushes a new message onto the unread messages stack
     * @param newMessage the message to be pushed
     */
    public void receiveMessage(Message newMessage)
    {
        unreadMessageBox.push(newMessage);
    }

    /**
     * Emptys the read message box by popping the entire stack
     * @return the int count of messages deleted
     */
    public int emptyReadMessageBox()
    {
        int count=0;
        while(!readMessageBox.isEmpty())
        {
            readMessageBox.pop();
            count++;
        }
        return count;
    }

    /**
     * creates a string show how many messages are read and unread
     * @return the string value of how many messages are read and unread
     */
    public String getStatistics()
    {
        return "Unread ("+unreadMessageBox.size()+")"+"\n"+"Read ("+readMessageBox.size() +")";  
    }

    /**
     * traverse all the unread messages
     * @returns a string value showing the contents of all unread messages
     */
    public String traverseUnreadMessages()
    {
        MessageStackIterator read=unreadMessageBox.iterator();
        Message use;
        try
        {
            use=read.next();
        }
        catch (NoSuchElementException e1)
        {
            return "Unread(0)\n";  
        }

        String end="Unread("+unreadMessageBox.size()+")\n";
        while(use!=null)
        {
            end+=use.getID()+" "+use.getSUBJECT()+"\n"; 
            if(read.hasNext())
                use=read.next();
            else
                break;
        }
        return end;
    }

    /**
     * traverse all the read messages
     * @returns a string value showing the contents of all read messages
     */
    public String traverseReadMessages()
    {
        MessageStackIterator read=readMessageBox.iterator();
        Message use;
        try
        {
            use=read.next();
        }
        catch (NoSuchElementException e1)
        {
            return "Read(0)\n";  
        }

        String end="Unread("+readMessageBox.size()+")\n";
        while(use!=null)
        {
            end+=use.getID()+" "+use.getSUBJECT()+"\n"; 
            if(read.hasNext())
                use=read.next();
            else
                break;
        }
        return end;
    }
}