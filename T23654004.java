
import java.util.ArrayList;
import java.util.Scanner;

public class T23654004 extends ExpressionTree {

   public static void main(String args[]) {
	   T23654004 y = new T23654004("5 + 6 * 7");
      Utility.print(y);
      y = new T23654004(Utility.getInput());
      Utility.print(y);
   }
   
   public String fullyParenthesised() {
      StringBuilder ans= new StringBuilder();
      fullyParenthesised((BinaryNode<String>)root(),ans);      
      return "";
   }
   
   //To Help
   public void fullyParenthesised(BinaryNode<String> bn, StringBuilder sb) {
	   if(bn==null)
		   return;
	   if(isLeaf(bn))
		   sb.append(bn.getData());
	   if(isInternal(bn)||isRoot(bn)) {
		   sb.append("(");
		   fullyParenthesised(bn.getLeft(),sb);
		   sb.append(" "+bn.getData()+" ");
		   fullyParenthesised(bn.getRight(),sb);
		   sb.append("(");
	   }
   }

   public T23654004(String s) {
      super();
      //Do Not understand what to do. 
   }
}


class Utility {
   public static String getInput() {
      System.out.println("Enter an algebraic expression: ");
      Scanner s = new Scanner(System.in);
      String answer =  s.nextLine();
      s.close();
      return answer;
   }

   public static void print(ExpressionTree y) {
      System.out.println("Prefix: " + y.prefix());
      System.out.println("Postfix: " + y.postfix());
      System.out.println("Fully parenthesised: " + y.fullyParenthesised());
      System.out.println("-----------------");
   }   
}

abstract class ExpressionTree extends BinaryTree<String> {
   public ExpressionTree() {
      super();
   }
   public abstract String fullyParenthesised();
   
   public String postfix() {
      String ans = "";
      ArrayList<TreeNode> l = postOrder(); 
      for (TreeNode b:l) ans += b.toString() + " ";
      return ans;
   }

   public String prefix() {
      String ans = "";
      ArrayList<TreeNode> l = preOrder(); 
      for (TreeNode b:l) ans += b.toString() + " ";
      return ans;
   }
}

class BinaryTree<T> extends Tree {
   
   public BinaryTree() {
      super();
   }

   public void addRoot(T t) throws Exception {
      if (root() != null)
         throw new Exception("The tree is not empty");
      setRoot(new BinaryNode<T>(null, null, null, t));
      size++;
   }

   public void addLeft(BinaryNode<T> node, T t) throws Exception {
      if (node.getLeft() != null)
         throw new Exception("Left child already exists");
      node.setLeft(new BinaryNode<T>(node, null, null, t));
      size++;
   }

   public void addRight(BinaryNode<T> node, T t) throws Exception {
      if (node.getRight() != null)
         throw new Exception("Right child already exists");
      node.setRight(new BinaryNode<T>(node, null, null, t));
      size++;
   }

   // removes a leaf but promotes and removes a descendant otherwise
   // returns the parent of the node that is actually removed
   @SuppressWarnings("unchecked")
   public BinaryNode<T> removeNode(BinaryNode<T> node) {
      if (isLeaf(node)) { // base case
         BinaryNode<T> parent = (BinaryNode<T>) node.getParent();
         if (parent == null)
            setRoot(null);
         else
            parent.removeChild(node);
         size--;
         return parent;
      }
      BinaryNode<T> lower = descendant(node);
      promote(lower, node);
      return removeNode(lower);
   }

   public void promote(BinaryNode<T> lower, BinaryNode<T> node) {
      node.data = lower.data;
   }

   public BinaryNode<T> descendant(BinaryNode<T> node) {
      if (node.left != null) return node.left;
      return node.right;
   }

   @SuppressWarnings("unchecked")
   public ArrayList<BinaryNode<T>> inOrder() {
      ArrayList<BinaryNode<T>> answer = new ArrayList<BinaryNode<T>>();
      inOrder((BinaryNode<T>) root(), answer);
      return answer;
   }

   public void inOrder(BinaryNode<T> node, ArrayList<BinaryNode<T>> order) {
      if (node == null)
         return;
      inOrder(node.getLeft(), order);
      order.add(node);
      inOrder(node.getRight(), order);
   }
}

class BinaryNode<T> implements TreeNode {

   T data;
   BinaryNode<T> left, right, parent;
   
   public BinaryNode() {
      parent = left = right = null;
      data = null;
   }
   
   public BinaryNode(BinaryNode<T> p, BinaryNode<T> l, BinaryNode<T> r, T d) {
      parent = p;
      left = l;
      right = r;
      data = d;
   }

   @Override
   public ArrayList<? extends TreeNode> getChildren() {
      ArrayList<BinaryNode<T>> answer = new ArrayList<>();
      if (left != null) answer.add(left);
      if (right != null) answer.add(right);
      return answer;
   }

   @Override
   public TreeNode getParent() {
      return parent;
   }
   
   @Override
   public String toString() {
      return data.toString();
   }
   
   public void setLeft(BinaryNode<T> n) {
      left = n;
   }

   public void setRight(BinaryNode<T> n) {
      right = n;
   }

   public BinaryNode<T> getLeft() {
      return left;
   }

   public BinaryNode<T> getRight() {
      return right;
   }

   public void removeChild(BinaryNode<T> n) {
      if (getLeft() == n)
         setLeft(null);
      if (getRight() == n)
         setRight(null);
   }

   public void setParent(BinaryNode<T> node) {
      parent = node;
   }

   public T getData() {
      return data;
   }

   public void setData(T newData) {
      data = newData;
   }
}

class Tree {
   private TreeNode root;
   public int size;

   public Tree() {
      setRoot(null);
      size = 0;
   }

   public TreeNode root() {
      return root;
   }

   public TreeNode parent(TreeNode node) {
      return node.getParent();
   }

   public boolean isRoot(TreeNode node) {
      return node == root();
   }

   public boolean isInternal(TreeNode node) {
      return node.getChildren().size() > 0;
   }

   public boolean isLeaf(TreeNode node) {
      return !isInternal(node);
   }

   public int size() {
      return size;
   }

   public boolean empty() {
      return size == 0;
   }

   public int depth(TreeNode node) {
      if (isRoot(node))
         return 0;
      return 1 + depth(node.getParent());
   }

   public int height(TreeNode node) {
      if (isLeaf(node))
         return 0;
      int maxChildHeight = 0;
      ArrayList<? extends TreeNode> c = node.getChildren();
      for (TreeNode t:c) {
         int hc = height(t);
         if (hc > maxChildHeight)
            maxChildHeight = hc;
      }
      return maxChildHeight + 1;
   }

   public int height() {
      if (root() == null)
         return -1;
      return height(root());
   }

   public ArrayList<TreeNode> preOrder() {
      ArrayList<TreeNode> answer = new ArrayList<>();
      preOrder(root(), answer);
      return answer;
   }

   public void preOrder(TreeNode node, ArrayList<TreeNode> nodeOrder) {
      if (node == null) return;
      nodeOrder.add(node);
      for (TreeNode n:node.getChildren()) {
         preOrder(n, nodeOrder);
      }
   }

   public ArrayList<TreeNode> postOrder() {
      ArrayList<TreeNode> answer = new ArrayList<TreeNode>();
      postOrder(root(), answer);
      return answer;
   }

   public void postOrder(TreeNode node, ArrayList<TreeNode> nodeOrder) {
      if (node == null)
         return;
      for (TreeNode n:node.getChildren()) {
         postOrder(n, nodeOrder);
      }
      nodeOrder.add(node);
   }

   public ArrayList<TreeNode> levelOrder() {
      ArrayList<TreeNode> waiting = new ArrayList<>();
      if (root() == null) return waiting;
      waiting.add(root());
      int done = 0;
      while (done < waiting.size()) {
         TreeNode oldNode = waiting.get(done++);
         for (TreeNode n:oldNode.getChildren())
            waiting.add(n);
      }
      return waiting;
   }

   public void setRoot(TreeNode root) {
      this.root = root;
   }
}

interface TreeNode {
   public ArrayList<? extends TreeNode> getChildren();
   public TreeNode getParent();
   public String toString();
}
