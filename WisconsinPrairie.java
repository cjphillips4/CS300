////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    Wisconsin Prarie
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
////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * This class creates a background that allows the user to create, delete, and drag cow PNGs
 */
public class WisconsinPrairie 
{
	// PApplet object that represents the graphic interface of the WisconsinPrairie application
	private static PApplet processing; 
	// PImage object that represents the background image
	private static PImage backgroundImage;
	// array storing the current cows present in the Prairie
	private static Cow[] cows; 
	// Generator of random numbers
	private static Random randGen; 

	public static void setup(PApplet processingObj) 
	{
		// initialize the processing field to the one passed
	    processing = processingObj; 
		// initialize and load the image of the background
		backgroundImage = processing.loadImage("images/background.png");
		//initializes cows array and the random generator
		cows = new Cow[10];
		randGen = new Random();
	}
	/**
	 * Calls the startApplication method which essentially runs the project
	 */
	public static void main(String[] args) 
	{
		Utility.startApplication();
	}

	/**
	 * Draws and updates the application display window. 
	 *  This callback method called in an infinite loop.
	 */
	public static void draw() 
	{
		//Repeatedly draws background image to cover up previously drawn cows
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		//repeatedly draws the initialed cows in the cows array
		for (int i = 0; i < cows.length; i++) 
		{
			if (cows[i] != null)
				cows[i].draw();

		}
	}

	/**
	 * Checks if the mouse is over a given cow whose reference is provided as input
	 * parameter
	 * @param cow reference to a given cow object 
	 * @return true if the mouse is over
	 *         the given cow object (i.e. over the image of the cow), false otherwise
	 */
	public static boolean isMouseOver(Cow cow) 
	{
		PImage cowPic = cow.getImage();
		//Checks if mouse is over a cows image rectangle of coordinate
		if (processing.mouseX < (cow.getPositionX() + (cowPic.width / 2))
				&& processing.mouseX > (cow.getPositionX() - (cowPic.width / 2)))
			if (processing.mouseY < (cow.getPositionY() + (cowPic.height / 2))
					&& processing.mouseY > (cow.getPositionY() - (cowPic.height / 2)))
				return true;
		return false;
	}

	/**
	 * Callback method called each time the user presses the mouse
	 */
	public static void mousePressed() 
	{
		int i = 0;
		//loops through cows array to check if mouse is over a cow,
		//then sets their dragging variable to true,then breaks
		while (i < cows.length) 
		{
			if (cows[i] != null && isMouseOver(cows[i])) 
			{
				cows[i].setDragging(true);
				break;
			}
			i++;
		}
		//updates the cows position if its dragging variable is true
		if(i<cows.length && cows[i].isDragging())
		{
		cows[i].setPositionX(processing.mouseX);
		cows[i].setPositionY(processing.mouseY);
		}
	}

	/**
	 * Callback method called each time the mouse is released
	 */
	public static void mouseReleased() 
	{
		//sets all non null indexes in cows to have a false dragging variable
		for (int i = 0; i < cows.length; i++)
			if (cows[i] != null) 
			    cows[i].setDragging(false);
	}

	/**
	 * Callback method called each time the user presses a key
	 */
	public static void keyPressed() 
	{
		//checks if c key is pressed then creates a cow at the first available index,
		//breaks when spot is found
		if (processing.key == 'c' || processing.key == 'C' )
			for (int i = 0; i < cows.length; i++) 
			{
				if (cows[i] == null) 
				{
					cows[i] = new Cow(processing, (float) randGen.nextInt(processing.width),
							(float) randGen.nextInt(processing.height));
					break;
				}
			}
		//checks if d key is pressed then checks if mouse is over any cow, sets that cow to null
		//breaks when spot is found
		else if (processing.key == 'd' || processing.key == 'D') 
		{
			for (int i = 0; i < cows.length; i++) 
			{
				if (cows[i] != null && isMouseOver(cows[i])) 
				{
					cows[i] = null;
					break;
				}
			}
		}

	}

}
