/** AVL Tree
 * March 2020
 * @author Angus Longmore
 * Based on Hussein Suleman's AVL Tree
 * reference: kukuruku.co/post/avl-trees/
 */

/** An AVL Tree data structure 
 * 
 */
public class AVLTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   public int opCount = 0;
   public int inCount = 0;

   /** An accessor method for the node instance variable, height
    * 
    * @param node the node who's height is to be returned
    * @return -1 if the node is null, or the node's height
    */
   public int height ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }
   
   /** Determines the difference in heights between the left and 
    * right child nodes of the given node
    * 
    * @param node The node whose child height differential should be calculated
    * @return The height differential between the left and right children
    */
   public int balanceFactor ( BinaryTreeNode<dataType> node )
   {
      return height (node.right) - height (node.left);
   }
   
   /** Recalculate the height of a given node
    * 
    * @param node the node whose height should be updated
    */
   public void fixHeight ( BinaryTreeNode<dataType> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
   
   /** Perform a rotation of nodes about a given node, to rebalance a subtree. 
    * Will rotate to the right
    * 
    * @param p The node about which the rotation will be performed
    * @return Pops the temporary node it created from the tree
    */
   public BinaryTreeNode<dataType> rotateRight ( BinaryTreeNode<dataType> p )
   {
      BinaryTreeNode<dataType> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   /** Perform a rotation of nodes about a given node, to rebalance a subtree. 
    * Will rotate to the left
    * 
    * @param p The node about which the rotation will be performed
    * @return Pops the temporary node it created from the tree
    */
   public BinaryTreeNode<dataType> rotateLeft ( BinaryTreeNode<dataType> q )
   {
      BinaryTreeNode<dataType> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   
   /** Automatically performs the required calculations and rotations to renalance 
    * subtrees and the tree as a whole
    * 
    * @param p The node about which to rebalance
    * @return The node
    */
   public BinaryTreeNode<dataType> balance ( BinaryTreeNode<dataType> p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   /** Helper method for verbose insert()
    * 
    * @param d data type to be inserted
    */
   public void insert ( dataType d )
   {
      root = insert (d, root);
   }

   /** Inserts new data into the tree, rebalancing the tree as it does so
    * 
    * @param d The data to be inserted
    * @param node A node to start the insertion recursion from
    * @return A call for balance(node) where node is the newly inserted node
    */
   public BinaryTreeNode<dataType> insert ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return new BinaryTreeNode<dataType> (d, null, null);
      inCount++; // Instrumentation
      if (d.compareTo (node.data) <= 0)
         node.left = insert (d, node.left);
      else
         node.right = insert (d, node.right);
      return balance (node);
   }
   
   /** Helper method for verbose delete()
    * 
    * @param d Data to be deleted from the tree
    */
   public void delete ( dataType d )
   {
      root = delete (d, root);
   } 
   
   /** Deletes specified data from the tree, starting the recursion at the given node, 
    * and balances the tree
    * @param d The data to be deleted 
    * @param node The node to start the recursion from
    * @return A call to balance the tree with a given node, or 
    */
   public BinaryTreeNode<dataType> delete ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else
      {
         BinaryTreeNode<dataType> q = node.left;
         BinaryTreeNode<dataType> r = node.right;
         if (r == null)
            return q;
         BinaryTreeNode<dataType> min = findMin (r);
         min.right = removeMin (r);
         min.left = q;
         return balance (min);
      }
      return balance (node);
   }
   
   /** Locates the minimum node in the tree
    * 
    * @param node The node to begin recursive searching from
    * @return Minimum node
    */
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left != null)
         return findMin (node.left);
      else
         return node;
   }

   /** Removes the minimum node in the tree and then balance the tree
    * 
    * @param node The node to begin recursive searching from
    * @return Minimum node
    */
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left == null)
         return node.right;
      node.left = removeMin (node.left);
      return balance (node);
   }

   /** A helper method for verbose find()
    * 
    * @param d The data to be located 
    * @return A call for a verbose find() with the root node as the start point
    */
   public BinaryTreeNode<dataType> find ( dataType d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   /** Locate given data in the AVL Tree
    * 
    * @param d The data to be located 
    * @param node
    * @return null if the data doesn't exist in the tree, or the node that contains the data
    */
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node )
   {
      opCount++; // Instrumentation
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }
   
   /** Helper method for verbose treeOrder()
    * 
    */
   public void treeOrder ()
   {
      treeOrder (root, 0);
   }

   /** Traverses the Tree and prints out a representation of the tree 
    * 
    * @param node The node to begin from
    * @param level The level of the tree to start at
    */
   public void treeOrder ( BinaryTreeNode<dataType> node, int level )
   {
      if (node != null)
      {
         for ( int i=0; i<level; i++ )
            System.out.print (" ");
         System.out.println (node.data);
         treeOrder (node.left, level+1);
         treeOrder (node.right, level+1);
      }
   }
}

