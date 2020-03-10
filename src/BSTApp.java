
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
            if (args.length == 1) {
                processFile(args[0]);
                findAll(args[0]);
            }
            if (args.length == 4){
                processFile(args[3]);
                findAll(args[3]);
            } else {
                processFile("src/LSData.txt");
            }
            
        } catch (IOException e) {
            System.out.println("Cannot find txt file");

        }
        if (args.length == 0) {
            bt.inOrder();
        } 
        if (args.length == 3) {
            String checker = args[0] + "_" + args[1] + "_" + args[2];          
            System.out.println ("Search : ");
            BinaryTreeNode<LSDataPiece> testNode = (bt.find(new LSDataPiece(checker)));
            if (testNode == null) {
                System.out.println("Could not find any areas!");    
            } else
                bt.visit(testNode);
        }
        System.out.println("Searching took " + bt.opCount + " comparisons");
        // System.out.println("Inserting took " + bt.inCount + " comparisons");
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
                bt.insert(new LSDataPiece(inScanner.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Cannot find specified file " + file);
        }
        

    }
    /** Given a text file, uses each line of the file as a parameter and searches for it
     * outputting comparison operation counts
     * 
     * @param file The file to be used for parameters
     * @throws IOException Standard error for file I/O
     */

    public static void findAll(String file) throws IOException {
        File inFile = new File(file);
        try {
            Scanner searchScanner = new Scanner(inFile);
            while (searchScanner.hasNextLine()) {
                BinaryTreeNode<LSDataPiece> findNode = bt.find(new LSDataPiece(searchScanner.nextLine()));
                if (findNode != null) {
                    System.out.println(bt.opCount);
                } else {
                    System.out.println("Catastrophic Failure");
                    break;
                }
            }
            System.out.println("insertOps " + bt.inCount);
        } catch (IOException e) {
            System.out.println("Couldn't find specified file " + file);
        }
    }
}