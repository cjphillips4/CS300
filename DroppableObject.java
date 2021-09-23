import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet; 
import processing.core.PImage;
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Droppable Object
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
 * This class extends Draggable Object and keeps the status of if an object is being dropped
 * @author connorphillips
 *
 */
public class DroppableObject extends DraggableObject 
{
	
	private VisibleObject target; // object over which this object can be dropped
	private Action action; // action that results from dropping this object
	                     // over target
	// initialize new object
	/**
     * Constructor for Droppable object
     * @param name the String name of object
     * @param x the int width position of the object
     * @param y the int height position of the object
     * @param target the Visible object that reacts to the droppable
     * @param action the Action to be taken when the droppable is dropped on the target
     */
	public DroppableObject(String name, int x, int y, VisibleObject target, Action action)
	{
		super(name,x,y);
		this.target=target;
		this.action=action;
	}
	
	/**
     * Drops the object when not being dragged
     * @return the Action to be taken depending on where object is dropped
     */
	@Override
	protected Action drop() 
	{
		if(isOver(target) && target.isActive())
		{
			target.deactivate();
			this.deactivate();
			return action;
		}
		return null;
	} // returns action and deactivates objects
	                        // in response to successful drop
	// When this object is over its target and its target is active:
	// deactivate both this object and the target object, and return action,
	// otherwise return null

}
