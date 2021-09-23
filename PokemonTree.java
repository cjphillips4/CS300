
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//Title:    PokemonTree
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
 * This class implements a binary search tree (BST) which stores a set of
 * Pokemons. The left subtree contains the Pokemons who are less powerful than
 * the Pokemon stored at a parent node. The right subtree contains the Pokemons
 * who are more powerful than the Pokemon stored at a parent node.
 *
 */
/**
 * Contructor for the PokemonTree Object
 */
public class PokemonTree {
    private PokemonNode root; // root of this binary search tree
    private int size; // total number of Pokemons stored in this tree.

    public PokemonTree()
    {
        root=null;
        size=0;
    }

    /**
     * Checks whether this binary search tree (BST) is empty
     * 
     * @return true if this PokemonTree is empty, false otherwise
     */
    public boolean isEmpty() {
        if(root==null)
            return true;
        return false;
    }

    /**
     * Returns the number of Pokemons stored in this BST.
     * 
     * @return the size of this PokemonTree
     */
    public int size() {
        return size;
    }

    /**
     * Recursive helper method to add a new Pokemon to a PokemonTree rooted at
     * current.
     * 
     * @param current    The "root" of the subtree we are inserting new pokemon
     *                   into.
     * @param newPokemon The Pokemon to be added to a BST rooted at current.
     * @return true if the newPokemon was successfully added to this PokemonTree,
     *         false otherwise
     */
    public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) {
        if(current.getPokemon().compareTo(newPokemon)>0)
        {
            if(current.getLeftChild()==null)
            {
                current.setLeftChild(new PokemonNode(newPokemon));
                return true;
            }
            return addPokemonHelper(newPokemon,current.getLeftChild());
        }
        else if(current.getPokemon().compareTo(newPokemon)<0)
        {
            if(current.getRightChild()==null)
            {
                current.setRightChild(new PokemonNode(newPokemon));
                return true;
            }
            return addPokemonHelper(newPokemon,current.getRightChild());
        }
        else
            return false;
    }

    /**
     * Adds a new Pokemon to this PokemonTree
     * 
     * @param newPokemon a new Pokemon to add to this BST.
     * @return true if the new was successfully added to this BST, and returns false
     *         if there is a match with this Pokemon already already stored in this
     *         BST.
     */
    public boolean addPokemon(Pokemon newPokemon) {
        // TODO Complete the implementation of this method.
        boolean store=false;

        if(newPokemon==null)
            return false;
        else if (isEmpty()) { // Add new to an empty PokemonTree
            this.root=new PokemonNode(newPokemon);
            store=true;
        } else { // Add new to an non-empty PokemonTree
            int val=root.getPokemon().compareTo(newPokemon);
            if(val==0)
                return false;
            else if(val<0)
            {
                if(root.getRightChild()==null)
                {
                    root.setRightChild(new PokemonNode(newPokemon));
                    size++;
                    return true;
                }
                else
                    store=addPokemonHelper(newPokemon,root.getRightChild());
            }
            else
            {
                if(root.getLeftChild()==null)
                {
                    root.setLeftChild(new PokemonNode(newPokemon));
                    size++;
                    return true;
                }
                else
                    store=addPokemonHelper(newPokemon,root.getLeftChild());
            }
        }
        if(store)
        {
            size++;
            return true;
        }
        return false;
    }

    /**
     * Recursive helper method which returns a String representation of the BST
     * rooted at current. An example of the String representation of the contents of
     * a PokemonTree is provided in the description of the above toString() method.
     * 
     * @param current reference to the current PokemonNode within this BST.
     * @return a String representation of all the Pokemons stored in the sub-tree
     *         PokemonTree rooted at current in increasing order with respect to the
     *         CP values. Returns an empty String "" if current is
     *         null.
     */
    public static String toStringHelper(PokemonNode current) {
        String end="";
        if(current==null)
            return "";
        end+=toStringHelper(current.getLeftChild());
        end+=current.getPokemon().toString();
        end+=toStringHelper(current.getRightChild());
        return end; // remove this statement.
    }

    /**
     * Returns a String representation of all the Pokemons stored within this BST in
     * the increasing order, separated by a newline "\n". For instance:
     * "[Pikachu CP:123 (A:1 S:2 D:3)]" + "\n" + "[Eevee CP:224 (A:2 S:2 D:4)]" + "\n" + 
     * [Lapras CP:735 (A:7 S:3 D:5)] + "\n" + "[Mewtwo CP:999 (A:9 S:9 D:9)]" + "\n"
     * 
     * @return a String representation of all the Pokemons stored within this BST
     *         sorted in an increasing order with respect to the CP values.
     *         Returns an empty string "" if this BST is empty.
     */
    public String toString() {
        String end;
        if(isEmpty())
            return "";

        else
            end=toStringHelper(root);
        return end;
    }

    /**
     * Search for a Pokemon (Pokemon) given the CP value as lookup key.
     * 
     * @param cp combat power of a Pokemon
     * @return the Pokemon whose CP value equals our lookup key.
     * @throws a NoSuchElementException with a descriptive error message if there is
     *           no Pokemon found in this BST having the provided CP value
     */
    public Pokemon lookup(int cp) {
        return lookupHelper(cp, root);
    }

    /**
     * Recursive helper method to lookup a Pokemon given a reference Pokemon with
     * the same CP in the subtree rooted at current
     * 
     * @param find    a reference to a Pokemon target we are lookup for a match in
     *                the BST rooted at current.
     * @param current "root" of the subtree we are looking for a match to find
     *                within it.
     * @return reference to the Pokemon stored stored in this BST which matches
     *         find.
     * @throws NoSuchElementException with a descriptive error message if there is
     *                                no Pokemon whose CP value matches target value,
     *                                stored in this BST.
     */
    public static Pokemon lookupHelper(int cp, PokemonNode current) {
        if(current==null)
        {
            throw new NoSuchElementException("No Pokemon matches this cp.");    
        }
        else if(current.getPokemon().getCP()==cp)
            return current.getPokemon();
        else
        {
            if(cp>current.getPokemon().getCP())
                return lookupHelper(cp,current.getRightChild());
            else
                return lookupHelper(cp,current.getLeftChild());
        } // remove this statement (added to let this code to compile)
    }

    /**
     * Computes and returns the height of this BST, counting the number of nodes
     * (PokemonNodes) from root to the deepest leaf.
     * 
     * @return the height of this Binary Search Tree
     */
    public int height() {
        if(isEmpty())
            return 0;
        return heightHelper(root);
    }

    /**
     * Recursive helper method that computes the height of the subtree rooted at
     * current
     * 
     * @param current pointer to the current PokemonNode within a PokemonTree
     * @return height of the subtree rooted at current, counting the number of
     *         PokemonNodes
     */
    public static int heightHelper(PokemonNode current) {
        int lHeight=0;
        int rHeight=0;
        if(current.getLeftChild()!=null)
            lHeight=heightHelper(current.getLeftChild());
        if(current.getRightChild()!=null)
            rHeight=heightHelper(current.getRightChild());

        if(lHeight>rHeight)
            return 1+lHeight;
        else
            return 1+rHeight;// remove this statement.
    }

    /**
     * Returns the Pokemon of the least powerful Pokemon in this BST.
     * 
     * @return the Pokemon of the least powerful Pokemon in this BST and null if this tree
     *         is empty.
     */
    public Pokemon getLeastPowerfulPokemon() {
        if(isEmpty())
            return null;
        return leastPowerfulHelper(root);
    }

    private Pokemon leastPowerfulHelper(PokemonNode current)
    {
        if(current.getLeftChild()!=null)
            return leastPowerfulHelper(current.getLeftChild());
        return current.getPokemon();
    }

    /**
     * Returns the Pokemon of the most powerful Pokemon in this BST.
     * 
     * @return the Pokemon of the most powerful Pokemon in this BST, and null if this tree
     *         is empty.
     */
    public Pokemon getMostPowerfulPokemon() {
        if(isEmpty())
            return null;
        return mostPowerfulHelper(root);   
    }

    private Pokemon mostPowerfulHelper(PokemonNode current)
    {
        if(current.getRightChild()!=null)
        {
            return mostPowerfulHelper(current.getRightChild());   
        }
        return current.getPokemon();
    }
}
