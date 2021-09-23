////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    PokemonNode
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
public class PokemonNode
{
    private Pokemon data;
    private PokemonNode leftChild;
    private PokemonNode rightChild;
    
    public PokemonNode(Pokemon data)
    {
       if(data==null)
          throw new IllegalArgumentException("Pokemon is null.");
       leftChild=null;
       rightChild=null;
       this.data=data;
    }
    
    public PokemonNode getLeftChild()
    {
     return leftChild;   
    }
    
    public PokemonNode getRightChild()
    {
     return rightChild;   
    }
    
    public Pokemon getPokemon()
    {
     return data;   
    }
    
    public void setLeftChild(PokemonNode left)
    {
     leftChild=left;   
    }
    
    public void setRightChild(PokemonNode right)
    {
     rightChild=right;   
    }
}