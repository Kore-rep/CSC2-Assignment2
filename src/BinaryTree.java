/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *  Based on Hussein Suleman's Binary Tree
 */


/** A Binary Tree data structure for more efficient storage and retrieval
 * Uses one instance variable to store it's root node
 * @param <dataType> Generic data to be stored in the tree
 */
public class BinaryTree<dataType>
{
   BinaryTreeNode<dataType> root;
   
   /** Constructs a binary tree
    * 
    */
   public BinaryTree ()
   {
      root = null;
   }
   
   /** Helper method for verbose getHeight()
    * 
    * @return a call for getHeight(node)
    */
   public int getHeight ()
   {
      return getHeight (root);
   }   

   /** Calculates the longest branch of the tree i.e. The Height
    * 
    * @param node A node to start from (usually root node)
    * @return the height of the tree
    */
   public int getHeight ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   /** Helper Method for verbose getSize()
    * 
    * @return
    */
   public int getSize ()
   {
      return getSize (root);
   }   
   /** Returns the number of nodes in the tree
    * 
    * @param node node to start from (usually root node)
    * @return the size of the tree
    */
   public int getSize ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   /** Displays the data stored in a specified node

    * 
    * @param node a node to display data for
    */
   public void visit ( BinaryTreeNode<dataType> node )
   {
      System.out.println (node.data);
   }
   
   /** Helper method for verbose preOrder()
    * 
    */
   public void preOrder ()
   {
      preOrder (root);
   }
   /** Traverses all nodes starting with specified node top to bottom
    * 
    * @param node a node to start from
    */
   public void preOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   /** Helper method for verbose postOrder()
    * 
    */
   public void postOrder ()
   {
      postOrder (root);
   }

   /** Traverses all nodes from the bottom up, not in ascending order
    * 
    * @param node node to start from
    */
   public void postOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   /** Helper method for verbose inOrder()
    * 
    */
   public void inOrder ()
   {
      inOrder (root);
   }

   /** Traverses the tree in ascending order
    * 
    * @param node node to start from
    */
   public void inOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }

   /** Traverses the tree level by level
    * 
    */
   public void levelOrder ()
   {
      if (root == null)
         return;
      BTQueue<dataType> q = new BTQueue<dataType> ();
      q.enQueue (root);
      BinaryTreeNode<dataType> node;
      while ((node = q.getNext ()) != null)
      {
         visit (node);
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
   }
}
