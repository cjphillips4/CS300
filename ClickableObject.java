import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet; 
import processing.core.PImage;
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Clickable Object
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
 * This class extends Visible Object and makes methods for objects that can be clicked
 * @author connorphillips
 *
 */
public class ClickableObject extends VisibleObject {
	private Action action; // action returned from update when this object is clicked
	private boolean mouseWasPressed; // tracks whether the mouse was pressed
	                              // during the last update()
	/**
     * Constructor for Clickable object
     * @param name the String name of object
     * @param x the int width position of the object
     * @param y the int height position of the object
     * @param action the Action that holds the objects message
     */
	public ClickableObject(String name, int x, int y, Action action) 
	{
		super(name,x,y);
		this.action=action;	
		mouseWasPressed=false;
	}
	
	/**
     * Updates the status of the object
     * @return the Action depending on if an object is being clicked
     */
	@Override
	public Action update() 
	{
		super.update();
		if(!mouseWasPressed)
		{
			if(isOver(getProcessing().mouseX,getProcessing().mouseY)&&getProcessing().mousePressed)
			{
				mouseWasPressed=true;
				return action;
			}
		}
	  if(!getProcessing().mousePressed)
			mouseWasPressed=false;
		 return null;	
	    
	} // calls VisibleObject update, then returns
	                       // action only when mouse is first clicked
	                       // on this object


}
