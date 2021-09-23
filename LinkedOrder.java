////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    LinkedOrder 
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
/**
 * This class Creates the LinkedOrder object that holds an order and a next, previous Linked Order
 */
public class LinkedOrder
{
    private final Order ORDER;
    private LinkedOrder previous;
    private LinkedOrder next;

    /**
     * Constructor for LinkedOrder object
     * @param order the Order to be stored in the LinkedOrder
     */
    public LinkedOrder(Order order)
    {   
        if(order.compareTo(new Order("x",0))==-1)
          throw new IllegalArgumentException("the order's timeStamp is negative");
        this.ORDER=order;
        previous=null;
        next=null;
    }

    /**
     * Constructor for LinkedOrder object
     * @param order the Order to be stored in the LinkedOrder
     * @param prev the LinkedOrder previous to this
     * @param next the LinkedOrder following this
     */
    public LinkedOrder(Order order, LinkedOrder prev, LinkedOrder next)
    {
        if(order.compareTo(new Order("x",0))==-1)
          throw new IllegalArgumentException("the order's timeStamp is negative");
        this.ORDER=order;
        previous=prev;
        this.next=next;
    }
    
    /**
     * Gets order in the linkedOrder
     * @return Order the order stored
     */
    public Order getOrder()
    {
        return ORDER;  
    }

    /**
     * Gets previous in the linkedOrder
     * @return LinkedOrder before this
     */
    public LinkedOrder getPrevious()
    {
        return previous;  
    }

    /**
     * Gets next in the linkedOrder
     * @return LinkedOrder after this
     */
    public LinkedOrder getNext()
    {
        return next;  
    }

    /**
     * Stores a LinkedOrder as previous
     * @param previous the LinkedOrder to be stored
     */
    public void setPrevious(LinkedOrder previous)
    {
        this.previous=previous;
    }

    /**
     * Stores a LinkedOrder as next
     * @param next the LinkedOrder to be stored
     */
    public void setNext(LinkedOrder next)
    {
        this.next=next;
    }
}