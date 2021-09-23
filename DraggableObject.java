import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet; 
import processing.core.PImage;
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Draggable Object
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
 * This class extends Visible Object and keeps the status of if an object is being dragged
 * @author connorphillips
 *
 */
public class DraggableObject extends VisibleObject
{
	private boolean mouseWasPressed; // similar to use in ClickableObject
	private boolean isDragging; // true when this object is being dragged by the user
	private int oldMouseX; // horizontal position of mouse during last update
	private int oldMouseY; // vertical position of mouse during last update
	
	/**
     * Constructor for Draggable object
     * @param name the String name of object
     * @param x the int width position of the object
     * @param y the int height position of the object
     */
	public DraggableObject(String name, int x, int y) 
	{
		super(name,x,y);
		mouseWasPressed=false;
		isDragging=false;
	} // initializes new  object
	
	
	/**
     * Updates the status of the object
     * @return the Action depending on if an object is being dragged
     */
	@Override
	public Action update() 
	{
		super.update();
		mouseWasPressed=getProcessing().mousePressed;
		if(!isDragging && getProcessing().mousePressed && isOver(getProcessing().mouseX,getProcessing().mouseY))
		{
			isDragging=true;
			oldMouseX=getProcessing().mouseX;
		    oldMouseY=getProcessing().mouseY;
		}
		else if(isDragging && mouseWasPressed)
		{
			move(getProcessing().mouseX-oldMouseX, getProcessing().mouseY-oldMouseY);
		    oldMouseX=getProcessing().mouseX;
	        oldMouseY=getProcessing().mouseY;
		}
	      else if(isDragging && !mouseWasPressed)
	    {
	    	isDragging=false;
	    	return drop();
	    }
		return null;
	
	} // calls VisibleObject update() first, then moves
	                       // according to mouse drag
	// each time isDragging changes from true to false, the drop() method below will be
	// called once and any action objects returned from that method should then be
	// returned from update()
	
	/**
     * Drops the object when not being dragged
     * @return action null
     */
	protected Action drop() 
	{  
		return null; 
	} // this method returns null.
	// subclass types will override this drop() method to perform more interesting behavior
}
