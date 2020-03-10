/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *  
 */

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class AVLApp
{
   private static AVLTree<LSDataPiece> at;
   public static void main ( String [] args )
   {
      AVLTree<LSDataPiece> at = new AVLTree<LSDataPiece> ();  
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
        at.inOrder();
    } 
    if (args.length == 3) {
        String checker = args[0] + "_" + args[1] + "_" + args[2];          
        System.out.println ("Search : ");
        BinaryTreeNode<LSDataPiece> testNode = (at.find(new LSDataPiece(checker)));
        if (testNode == null) {
            System.out.println("Could not find any areas!");    
        } else
            at.visit(testNode);
    }
    System.out.println("Searching took " + at.opCount + " comparisons");
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
                at.insert(new LSDataPiece(inScanner.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Cannot find specified file " + file);
        }
        

    }

   public static void findAll(String file) throws IOException {
      File inFile = new File(file);
      try {
          Scanner searchScanner = new Scanner(inFile);
          while (searchScanner.hasNextLine()) {
              BinaryTreeNode<LSDataPiece> findNode = at.find(new LSDataPiece(searchScanner.nextLine()));
              if (findNode != null) {
                  System.out.println(at.opCount);
              } else {
                  System.out.println("Catastrophic Failure");
                  break;
              }
          }
          System.out.println("insertOps " + at.inCount);
      } catch (IOException e) {
          System.out.println("Couldn't find specified file " + file);
      }
  }
}
