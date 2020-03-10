/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *  Based on Hussein Suleman's Binary Tree Node
 */


/** A node that can store data for a Binary Tree
 * 
 * @param <dataType> generic data type
 */

public class BinaryTreeNode<dataType>
{
   dataType data;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   int height;

   /** Constructor Method to assign data and child nodes and sets the height of the tree to 0
    * 
    * @param d Data to be stored
    * @param l the node to this node's left
    * @param r the node to this node's right
    */
   
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }
   
   /** returns child node to it's left
    * 
    * @return Left child node
    */
   BinaryTreeNode<dataType> getLeft () { return left; }

   /** returns child node to it's left
    * 
    * @return Left child node
    */
   BinaryTreeNode<dataType> getRight () { return right; }
}
