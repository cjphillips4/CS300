import processing.core.PApplet; 
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    Interactive Object
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
 * Parent class for several other interactable classes
 * @author connorphillips
 *
 */
public class InteractiveObject  
{
	private final String NAME; // the constant name identifying this interactive object
	private boolean isActive; // active means this interactive object is visible and
	private static PApplet processing = null;							// can be interacted with

	
	/**
	 * Constructor for Interactive Object
	 * @param name the Name to be stored
	 */
	public InteractiveObject(String name) 
	{
	 this.NAME=name;
	 isActive=true;
	} // initializes the name of this object,
	// and sets isActive to true

	/**
	 * Checks if two names are equal
	 * @param name to be compared against constant NAME
	 * @return true if names are equal, false otherwise
	 */
	public boolean hasName(String name) 
	{
		if (name.equals(NAME))
			return true;
		return false;
	} // returns true only when contents of name equal NAME

	
	/**
	 * gives boolean on active status
	 * @return true if isActive is true, false otherwise
	 */
	public boolean isActive() 
	{
		return isActive;
	} // returns true only when isActive is true

	/**
	 * sets isActive to true;
	 */
	public void activate() 
	{

		isActive=true;
	} // changes isActive to true

	/**
	 * sets isActive to false
	 */
	public void deactivate() 
	{
	    isActive=false;
	} // changes isActive to false

	/**
	 * updates status of Interactive Object
	 * @return null Action
	 */
	public Action update() 
	{
		return null;
	} // this method returns null
//subclass types will override this update() method to do more interesting things

	/**
	 * stores PApplet in processing
	 * @param processing1 PAapplet to be stored in processing
	 */
	public static void setProcessing(PApplet processing1) 
	{
		processing=processing1;
	} // initializes processing field
	
	/**
	 * retrieves the PApplet
	 * @return processing
	 */
	protected static PApplet getProcessing() 
	{
		return processing;
	} // accessor method to retrieve this static field
}
