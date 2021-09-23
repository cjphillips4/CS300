////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Title:    File Explorer
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
import java.io.File; 
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class FileExplorer 
{
     /**
     * This method takes the given File object and gets the names of
     *     all the folders and files inside it without going deeper
     * 
     * @param currentFolder is the File that content will be listed
     * @return An array list of String containing the names of all the folders and files
     * @throws NotDirectoryException if the file doesnt exist or it is not a directory
     */
	public static ArrayList<String> listContents(File currentFolder) throws NotDirectoryException
	{
		if(!currentFolder.exists() || !currentFolder.isDirectory())
			throw  new NotDirectoryException("The file does not exist");
		ArrayList<String> list=new ArrayList<String>();
		String[] names=currentFolder.list();
		for(int i=0;i<names.length;i++)
		{
		    list.add(names[i]);	
		}
        return list;
    }
     /**
     * This method performs a deep search to find the names of all files in a  given file and its
     *      subfiles using recursion
     * 
     * @param currentFolder is the File that content will be listed
     * @return An array list of String containing the names of all the files found in the 
     *     deep search
     * @throws NotDirectoryException if the file is not a directory
     */
	public static ArrayList<String> deepListContents (File currentFolder)
			throws NotDirectoryException 
	{
		if(!currentFolder.isDirectory())
			throw  new NotDirectoryException("The file is not a directory.");
		ArrayList<String> names=new ArrayList<String>();
		ArrayList<String> temp=new ArrayList<String>();
		File[] list=currentFolder.listFiles();
		//Makes sure the folder holds a file
		if(list.length==0)
		{   
			return names;
		}
		else 
		  for(int i=0;i<list.length;i++)
			  //adds file name to list if not a directory
			  if(!list[i].isDirectory())
				  names.add(list[i].getName());
			  else 
			  {
				  //Recursively creates an array that's content will be added to names
				  temp=(deepListContents(list[i]));
				  for (int j=0;j<temp.size();j++)
				  {
					  names.add(temp.get(j));
				  }
			  }
		
		  return names;		
	}
	
	/**
     * This method searches a given file and its subfiles for a specific String that is returned 
     *     if found
     * 
     * @param currentFolder is the File that content will be searched
     * @param fileName name of a file that is searched for
     * @return the path to the file that contains the file name
     * @throws NotDirectoryException if the file doesn't exist, isnt a directory, file name is null, 
     *     or no file is found matching the file name
     */
	public static String searchByName (File currentFolder, String fileName) 
	{
		if(!currentFolder.exists())
			throw new NoSuchElementException("The passed in file is null");
		else if(!currentFolder.isDirectory())
		 throw new NoSuchElementException("The file passed in is not a directory");
		else if(fileName==null)
			throw new NoSuchElementException("The passed in file name is null");
		String result=searchByNameHelper(currentFolder,fileName);
		//throws NoSucchElementException if no file containing the name is located
		if (result==null)
			throw new NoSuchElementException("The filename was not found");
		else
			return result;
		
         
	}
	
	/**
     * Helper method for search by name that uses recursion
     * 
     * @param currentFolder is the File that content will be searched
     * @param fileName name of a file that is searched for
     * @return the path to the file that contains the file name
     */
	private static String searchByNameHelper(File currentFolder, String fileName)
	{
		File[] list=currentFolder.listFiles();
		String result=null;
		//Makes sure the folder contains files
		if(list.length==0)
			return result;
		else
		{
			for (int i=0;i<list.length;i++)
			{
				//returns the path to file if the name is found
				if(list[i].getName().equals(fileName) && !list[i].isDirectory())
				{
					return list[i].getPath();
				}
				//Recursively moves through file
				else if(list[i].isDirectory())
					if(searchByNameHelper(list[i],fileName)!=null)
						return searchByNameHelper(list[i],fileName);
		   	}
			
		}
		
		return result;
	}
	
	/**
     * This method searches a given file and its subfiles for a specific key, all file names that
     *     have the key are added to an array list of Strings 
     * 
     * @param currentFolder is the File that content will be searched
     * @param key the phrase/string that is searched for in file names
     * @return An array List of all the file names that contain the key
     */
	public static ArrayList<String> searchByKey (File currentFolder, String key) 
	{
		ArrayList<String> names=new ArrayList<String>();
		File[] list=currentFolder.listFiles();
		ArrayList<String> temp=new ArrayList<String>();
		list=currentFolder.listFiles();
		if(!currentFolder.isDirectory() || !currentFolder.exists() || key==null)
		{   
			return names;
		}
		else 
		  for(int i=0;i<list.length;i++)
			  //adds file name to names if the file name contains the key
			  if(!list[i].isDirectory() && list[i].getName().indexOf(key)!=-1)
				  names.add(list[i].getName());
			  else 
			  {
				  //Recursively creates array list to be added to names
				  temp=(searchByKey(list[i],key));
				  for (int j=0;j<temp.size();j++)
				  {
					  names.add(temp.get(j));
				  }
			  }
		
		  return names;
		
	}
	
	/**
     * This method searches a file and all subfiles for files within a certain byte range, 
     *     then adds their names to an Array List of Strings
     * 
     * @param currentFolder is the File that content will be searched
     * @param sizeMin the minimum byte size for a file, inclusive
     * @param sizeMax the maximum byte size for a file, inclusive
     * @return an array list of file names that are within the inclusive byte range
     */
	public static ArrayList<String> searchBySize (File currentFolder, long sizeMin, long sizeMax) 
	{
		ArrayList<String> names=new ArrayList<String>();
		File[] list=currentFolder.listFiles();
		ArrayList<String> temp=new ArrayList<String>();
		list=currentFolder.listFiles();
		if(!currentFolder.isDirectory() || !currentFolder.exists())
		{   
			return names;
		}
		else 
		  for(int i=0;i<list.length;i++)
			  //adds file name to names if the file is within the byte range
			  if(!list[i].isDirectory() && (list[i].length()>=sizeMin && list[i].length()<=sizeMax))
				  names.add(list[i].getName());
			  else 
			  {
				  //Recursively creates array list to be added to names
				  temp=(searchBySize(list[i],sizeMin,sizeMax));
				  for (int j=0;j<temp.size();j++)
				  {
					  names.add(temp.get(j));
				  }
			  }
		
		  return names;
		
		}
  
}
