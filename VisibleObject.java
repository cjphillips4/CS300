import java.io.File;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet; 
import processing.core.PImage;
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Visible Object
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
 * This class extends Interactive Objects and deals with all interactives that are visible
 * @author connorphillips
 *
 */
public class VisibleObject extends InteractiveObject
{
	private PImage image; // the graphical representation of this object
	private int x; // the horizontal position (in pixels of this object’s left side)
	private int y; // the vertical position (in pixels of this object’s top side)

	/**
	 * Constructor for Visible Object
	 * @param name name to be stored
	 * @param x int value of width postiion
	 * @param y int value of height position
	 */
	public VisibleObject(String name, int x, int y) 
	{
		super(name);
		this.x=x;
		this.y=y;
		image=getProcessing().loadImage("images"+File.separator+name+".png");	
	} // initialize this new VisibleObject // the image for this visible object should be loaded from :
	// "images"+File.separator+ name +".png"
	
	/**
     * Updates status of Visible Object
     * @return the Action to be taken on the object
     */
	@Override
	public Action update() 
	{
		getProcessing().image(image,x,y);
		return null;
	} // draws image at its position before returning null
	
	/**
	 * Changes the x and y value by adding dx and dy to them
	 * @param dx int value to be added to x
	 * @param dy int value to be added to y
	 */
	public void move(int dx, int dy) 
	{
		x+=dx;
		y+=dy;
	}	
	
	/**
	 * Checks if a point is over the object
	 * @param x int value of the width position
	 * @param y int value of the height position
	 * @return true if they are over eachother, false otherwise
	 */
	public boolean isOver(int x, int y) 
	{
		if(x>=this.x && x<=this.x+image.width)
			if(y>=this.y && y<=this.y+image.height)
				return true;
		return false;
	} // return true only when point x,y is over image
	
	/**
	 * Checks if two objects overlap
	 * @param other the Object to be compared to this object
	 * @return true if they overlap, false otherwise
	 */
	public boolean isOver(VisibleObject other) 
	{
		return other.isOver(this.x,this.y);
	} // return true only when other’s image
    // overlaps this one’s
}
