
/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *  
 */
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


/** A driver class for the Binary Tree
 * 
 */
public class BSTApp {

    private static BinarySearchTree<LSDataPiece> bt;

    /** Main method that differs output depending on parameters
     * 
     * @param args User provided parameters
     */
    public static void main(String[] args) {
        bt = new BinarySearchTree<LSDataPiece> ();
        
        try {
            if (args.length == 1) { // If only passed one argument, assume it is the name of a text file
                processFile(args[0]);
                findAll(args[0]); // Then assume the user wanted to perform a performace benchmark
                
            }
            /*if (args.length == 4){
                processFile(args[3]);
                findAll(args[3]);
                
                
            } */
            else { // If given only one argument, simply load the default data set entirely
                bt.inCount = 0;
                processFile("LSData.txt");
            }
            
            
        } catch (IOException e) {
            System.out.println("Cannot find txt file");

        }
        
        if (args.length == 0) {// The default data set has been loaded in the previous step
             
            
            bt.inOrder(); // Simply traverse the data set and print out 
            
        } 
        if (args.length == 3) { // If there are 3 arguments, it must be a search
            String checker = args[0] + "_" + args[1] + "_" + args[2];  // Create a string to search for 
            System.out.println ("Search : ");
            BinaryTreeNode<LSDataPiece> testNode = (bt.find(new LSDataPiece(checker))); // Create a temporary node object to compare other nodes to
            if (testNode == null) {
                System.out.println("Could not find any areas!");    
            } else
                bt.visit(testNode);
        }
        
        System.out.println("Searching took " + bt.opCount + " comparisons");
        System.out.println("Inserting took " + bt.inCount + " comparisons");
    }
    /** Reads in a text file and creates objects with the data it contains
     * 
     * @param file A specififed text file to read data from
     * @throws IOException A standard error for file I/O
     */
    public static void processFile(String file) throws IOException {
        File inFile = new File(file);
        try{ 
            Scanner inScanner = new Scanner(inFile);
            while (inScanner.hasNextLine()) {
                bt.insert(new LSDataPiece(inScanner.nextLine())); // Insert the contents of the given file into the AVL tree
            }
        } catch (IOException e) {
            System.out.println("Cannot find specified file " + file); // Fall back
        }        

    }
    /** Given a text file, uses each line of the file as a parameter and searches for it
     * outputting comparison operation counts
     * 
     * @param file The file to be used for parameters
     * @throws IOException Standard error for file I/O
     */

    public static void findAll(String file) throws IOException { // Assumes a file has already been loaded into the tree
        File inFile = new File(file);
        try {
            Scanner searchScanner = new Scanner(inFile);
            while (searchScanner.hasNextLine()) { // Loops through a given filea and searches for each line 
                BinaryTreeNode<LSDataPiece> findNode = bt.find(new LSDataPiece(searchScanner.nextLine()));
                if (findNode != null) {
                    System.out.println(bt.opCount);
                    bt.opCount = 0;
                } else {
                    System.out.println("Catastrophic Failure"); // There was a failure somewhere along the line
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't find specified file " + file);
        }
    }
}