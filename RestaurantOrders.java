////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    RestaurantOrders
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
 * This class holds a list of Linked Orders and allows you to place and remove orders
 *
 */
public class RestaurantOrders implements SortedListADT<Order>
{
    private LinkedOrder head;
    private LinkedOrder tail;
    private int size;
    private final int CAPACITY;

    /**
     * Constructor for RestaurantOrders object
     */
    public RestaurantOrders()
    {
        CAPACITY=20;
        size=0;
        head=null;
    } 

    /**
     * Constructor for RestaurantOrders object
     * @param capacity maximum size of the list
     */
    public RestaurantOrders(int capacity)
    {
        if(capacity<=0)
            throw new IllegalArgumentException("The capactity is 0 or less.");
        this.CAPACITY=capacity;
    }

    /**
     * Gives capacity of list
     * @return capacity of the RestaurantOrders
     */
    public int capacity()
    {
        return CAPACITY;  
    }

    /**
     * @return a string containing the orders starting from the front
     */
    public String readForward()
    {
        LinkedOrder cur=head;
        String print="The list contains "+size+" order(s): [";
        while(cur!=null)
        {
            print+=" "+cur.getOrder().getDishes();
            cur=cur.getNext();
        }
        print+=" ]";
        return print;
    }

    /**
     * @return a string containing the orders starting from the front
     */
    public String readBackward()
    {
        LinkedOrder cur=tail;
        String print="The list contains "+size+" order(s): [";
        while(cur!=null)
        {
            print+=" "+cur.getOrder().getDishes(); 
            cur=cur.getPrevious();
        }
        print+=" ]";
        return print;
    }

    /**
     * clears the RestaurantOrders
     */
    @Override
    public void clear()
    {
        tail=null;
        head=null;
        size=0;
    }

    /**
     * Retrieves the order at a certain index
     * @param index the int value of what index to retrieve
     * @return the order specified at a certain index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    @Override
    public Order get(int index)
    {
        int count=0;
        if(index>=size)
            throw new IllegalArgumentException("index out of bounds");
        LinkedOrder cur=head;
        while(count!=index && cur!=null)
        {
            cur=cur.getNext();
            count++;
        }
        return cur.getOrder();
    }

    /**
     * Gets the index of a given order
     * @param finalOrder the order that's index is being searched for
     * @return the int value of index where the order was found,-1 if not found
     */
    @Override
    public int indexOf(Order finalOrder)
    {
        int index=0;
        LinkedOrder cur=head;
        while(cur!=null)
        {
            if(cur.getOrder().equals(finalOrder))
                return index;
            index++;
        }
        return -1;
    }

    /**
     * gives a boolean value depending on if list is empty
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        if(head==null)
            return true;
        return false;
    }

    /**
     * Adds order to list, sorted earliest to latest
     * @param newOrder the Order to be placed
     * @throws IllegalArgumentException if order is null, timestamp negative, 
     *     or has same timestame as another order
     */
    @Override
    public void placeOrder(Order newOrder)
    {
        if(newOrder==null)
            throw new IllegalArgumentException("Order was null");
        else if(newOrder.compareTo(new Order("x",0))==-1)
            throw new IllegalArgumentException("the order's timeStamp is negative");
        else if(size<CAPACITY)
        {
            if(isEmpty())
            {
                head=new LinkedOrder(newOrder,null,tail);
                tail=new LinkedOrder(newOrder,head,null);
                size++;
            }
            else if(size==1)
            {
                if(newOrder.compareTo(head.getOrder())==-1)
                {
                    head.setPrevious(new LinkedOrder(newOrder,null,head));
                    head=head.getPrevious();
                    tail=head.getNext();
                    size++;
                }
                else if(newOrder.compareTo(head.getOrder())==1)
                {
                    head.setNext(new LinkedOrder(newOrder,head,null));
                    tail=head.getNext();
                    size++;
                }
                else if(newOrder.compareTo(head.getOrder())==0)
                    throw new IllegalArgumentException("Same time stamps.");
            }
            else if(newOrder.compareTo(head.getOrder())==-1)
            {
                head.setPrevious(new LinkedOrder(newOrder,null,head));
                head=head.getPrevious();
                size++;
            }
            else if(newOrder.compareTo(head.getOrder())==0)
            {
                throw new IllegalArgumentException("Same time stamps.");
            }

            else 
            {
                LinkedOrder cur=head.getNext();
                while(cur!=null)
                {
                    if(newOrder.compareTo(cur.getPrevious().getOrder())==0)
                        throw new IllegalArgumentException("An order has the same time stamp");
                    else if(newOrder.compareTo(cur.getPrevious().getOrder())==1)
                    {
                        if(newOrder.compareTo(cur.getOrder())==0)
                            throw new IllegalArgumentException("An order has the same time stamp");
                        else if(newOrder.compareTo(cur.getOrder())==-1)
                        {
                            LinkedOrder add=new LinkedOrder(newOrder,cur.getPrevious(),cur);
                            cur.getPrevious().setNext(add);
                            cur.setPrevious(add);
                            size++;
                            break;
                        }
                    }
                    if(newOrder.compareTo(cur.getOrder())==1 && cur.getNext()==null)
                    {
                        tail.setNext(new LinkedOrder(newOrder,tail,null));
                        tail=tail.getNext();
                        size++;
                        break;
                    }
                    cur=cur.getNext();
                }
            }
        }
    }

    /**
     * removes an order at a given index
     * @param index the int value of the order to be removed
     * @return the order that was removed
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public Order removeOrder(int index)
    {
        if(index>size-1 || index<0)
            throw new IndexOutOfBoundsException("Index isn't in the bounds of list.");
        else if(index==0)
        {
            Order store=head.getOrder();
            if(size==1)
                clear();
            else{
                head=head.getNext();
                head.setPrevious(null);
                size--;
            }
            return store;
        }
        else if(index==size-1)
        {
            Order store=tail.getOrder();
            tail.getPrevious().setNext(null);
            tail=tail.getPrevious();
            size--;
            return store;
        }
        else
        {
            int count=0;
            LinkedOrder cur=head;
            while(cur!=null)
            {
                if(count==index)
                {
                    cur.getPrevious().setNext(cur.getNext());
                    cur.getNext().setPrevious(cur.getPrevious());
                    size--;
                    return cur.getOrder();
                }
                count++;
                cur=cur.getNext();
            }
            return null;
        }
    }

    /**
     * gets the size of the list
     * @return int value of the list size
     */
    @Override
    public int size()
    {
        return size;   
    }
}