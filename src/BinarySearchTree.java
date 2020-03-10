
/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *  Based on Hussein Suleman's BinarySearchTree
 */

/**  
 * A Binary Tree data structure with search functionality made with generics
 *
 */
public class BinarySearchTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   public int opCount = 0;
   public int inCount = 0;

   /** A Helper method for insert
    * 
    * @param d the data to be inserted
    */
   public void insert ( dataType d )
   {
      if (root == null)
         root = new BinaryTreeNode<dataType> (d, null, null);
      else
         insert (d, root);
   }

   /** Inserts a new data piece into the tree starting from specified node, using recursion
    * 
    * @param d the data to be inserted
    * @param node a specified node to start from
    */
   public void insert ( dataType d, BinaryTreeNode<dataType> node )
   {
      inCount++; // Instrumentation for insertion
      if (d.compareTo (node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.right);
      }
   }
   /** Helper method for verbose find()
    * 
    * @param d data to be found
    * @return Returns a call to a more verbose return method
    */
   public BinaryTreeNode<dataType> find ( dataType d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }

   /** Attempts to find a given piece of data
    * 
    * @param d Data to find
    * @param node A node to start from
    * @return null if the data doesnt exist, or the data if it does
    */
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node )
   {
      this.opCount++; //Instrumentation
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0) {
         this.opCount++; //Instrumentation
         return (node.left == null) ? null : find (d, node.left);
      }
      else {
         this.opCount++; //Instrumentation
         return (node.right == null) ? null : find (d, node.right);
      }
   }
   
   /** Helper method for verbose delete()
    * 
    * @param d data to be deleted
    */
   public void delete ( dataType d )
   {
      root = delete (d, root);
   }   

   /** Attempts to delete given data, starting from a given node
    * 
    * @param d Data to be deleted
    * @param node Node to start from
    * @return The node is has deleted, or null if the data doesnt exist
    */
   public BinaryTreeNode<dataType> delete ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null) return null;
      this.opCount++; //Instrumentation
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0) {
         this.opCount++; //Instrumentation
         node.right = delete (d, node.right);
      }
      else if (node.left != null && node.right != null )
      {
         node.data = findMin (node.right).data;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   /** Finds the minimum data in the entire tree
    * 
    * @param node a node to start from
    * @return the minimum data node
    */
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   /** Removes the minimum data value from the tree
    * 
    * @param node A node to start from
    * @return the node it has removed, or null
    */
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
}