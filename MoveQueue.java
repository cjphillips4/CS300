////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    MoveQueue
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
import java.util.NoSuchElementException;
/**
 * This class Creates a MoveQueue 
 */
public class MoveQueue implements PriorityQueueADT<BattleCharacter>
{
    private BattleCharacter[] data;
    private int size;
    /**
     * Contructor for the MoveQueue Object
     * @param capacity the size of the MoveQueue
     */
    public MoveQueue(int capacity)
    {
        if(capacity<=0)
            throw new IllegalArgumentException("Capacity is 0 or negativee");
        data=new BattleCharacter[capacity];
        size=0;
    }

    /**
     * Contructor for the MoveQueue Object
     */
    public MoveQueue()
    {
        data=new BattleCharacter[10];
        size=0;
    }

    /**
     * Returns a String representation of the current contents of the MoveQueue * in order from first to last.
     * @author Michelle
     */
    @Override
    public String toString(){
        String s = ("[ ");
        for (int i =0; i< size; i++){
            s += (data[i].toString() + " | "); 
        }
        s += ("]");
        return s;
    }

    /**
     * Checks if this priority queue is empty. Returns true if it is empty and false otherwise.
     */
    @Override
    public boolean isEmpty()
    {
        if(size==0)
            return true;
        return false;
    }

    /**
     * Returns the current size of this priority queue.
     * 
     * @return the size of this priority queue
     */
    @Override
    public int size()
    {
        return size; 
    }

    /**
     * Adds the given element to the priority queue in the correct position based on the natural
     * ordering of the elements.
     * 
     * @param element to be added to this queue
     * @throws IllegalArgumentException if element is null
     * @throws IllegalStateException    of this priority queue is full
     */
    @Override
    public void enqueue(BattleCharacter element)
    {
        if(size==data.length)
            throw new IllegalStateException("Queue is full!");
        if(element==null)
            throw new IllegalArgumentException("Item is null!");
        if(isEmpty())
            data[0]=element;
        else
        {
            data[size]=element;
            percolateUp(size);
        }
        size++;
    }

    /**
     * Returns and removes the element at the front (aka root position) of this queue (the element
     * having the highest priority).
     * 
     * @return the removed element
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public BattleCharacter dequeue()
    {
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty!");
        BattleCharacter store=data[0];
        data[0]=data[size-1];
        data[size-1]=null;
        size--;
        percolateDown(0);
        return store;
    }

    /**
     * Gets the root of the heap
     * @return the root of the heap
     */
    @Override
    public BattleCharacter peekBest()
    {
        return data[0];   
    }

    /**
     * Removes all the elements from this priority queue. The queue must be empty after this method
     * returns.
     */
    @Override
    public void clear()
    {
        int capacity=data.length;
        data=new BattleCharacter[capacity]; 
        size=0;
    }

    /**
     * Recursively propagates max-heap order violations up.
     * Checks to see if the current node i violates the max-heap order property by checking its * parent. If it does, swap them and continue to ensure the heap condition is satisfied.
     * @param i index of the current node in this heap
     */
    protected void percolateUp(int i) 
    {
        int index=i;
        int pIndex=(i-1)/2;
        if(data[index].compareTo(data[pIndex])>0)
        {
            swap(index,pIndex);
            percolateUp(pIndex); 
        }
    }

    /**
     * Recursively propagates max-heap order violations down.
     * Checks to see if the current node i violates the max-heap order
     * property by checking its children. If it does, swap it with the optimal * child and continue to ensure the heap condition is met.
     * @param i index of the current node in this heap
     */
    protected void percolateDown(int i) 
    { 
        int lChild=(2*i)+1;
        int rChild=(2*i)+2;
        if(lChild>=data.length || data[lChild]==null)
        {
            return;   
        }

        else if(rChild>=data.length)
            return;
        else if(data[rChild]==null)
        {
            if(data[i].compareTo(data[lChild])<0)
            {
                swap(i,lChild);
                return;
            }
        }
        else if(data[lChild].compareTo(data[rChild])>0)
        {if(data[i].compareTo(data[lChild])<0)
            {
                swap(i,lChild);
                percolateDown(lChild);
            }
        }
        else if(data[lChild].compareTo(data[rChild])<0)
        {
            if(data[i].compareTo(data[rChild])<0)
            {
                swap(i,rChild);
                percolateDown(rChild);
            }
        }
    }

    /**
     * Checks if the data array follows the heap conditions
     * @return true if conditions of max-heap are met, false otherwise
     */
    private boolean isHeap()
    {
        int lChild,rChild;
        for(int i=0;i<size;i++)
        {
            lChild=(2*i)+1;
            rChild=(2*i)+2;
            if(lChild<size && data[lChild]!=null)
                if(data[i].compareTo(data[lChild])<0)
                    return false;
            if(rChild<size && data[rChild]!=null)
                if(data[i].compareTo(data[rChild])<0)
                    return false;
        }
        return true;
    }

    /**
     * Calls percolate up on all elements in the array to help heapify the array
     */
    private void heapifyHelp()
    {
        for(int i=size-1;i>=0;i--)
            percolateUp(i);
    }

    /**
     * Eliminates all heap order violations from the heap data array
     */
    protected void heapify() 
    {
        //Calls heapifyHelp() until the heap has met all conditions
        while(!isHeap())
        {
            heapifyHelp();
        }
    }

    /**
     * Swaps two elements in the heap
     * @param a position of the first element to be swapped
     * @param b position of the second element to be swapped
     */
    private void swap(int a,int b)
    {
        BattleCharacter store=data[a];
        data[a]=data[b];
        data[b]=store;
    }

    /**
     * Updates the status of a character
     * @param updateChara the character to be updated
     */
    public void updateCharacter(BattleCharacter updateChara)
    {
        int gapIndex;
        for(int i=0;i<size;i++)
        {
            if(data[i].equals(updateChara))
            {
                data[i]=updateChara;
                if(data[i].getHP()<=0)
                {
                    data[i]=null;
                    gapIndex = i; // change this to the index of the character that died 
                    for (int j = gapIndex; j < size-1; j++) {
                        data[j] = data[j + 1];
                    }
                    data[size-1] = null;
                    size--; 
                }
                heapify();
                break;
            }
        }
    }
}