import java.util.LinkedList;
import java.util.Queue;

public class BSTDemo {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(3);
        bst.insert(17);
        bst.insert(12);
        bst.insert(19);
        bst.insert(7);
        bst.insert(1);
        bst.insert(4);
        bst.insert(13);

        //traverse
        System.out.println("\n----------InOrder Traverse----------");
        bst.inOrderTraverse();
        System.out.println("\n----------PreOrder Traverse----------");
        bst.preOrderTraverse();
        System.out.println("\n----------PostOrder Traverse----------");
        bst.postOrderTraverse();
        System.out.println("\n----------Level Order Traverse----------");
        bst.traverseLevelOrder();

        //find
        System.out.println("\n--------------------");
        int testVal1 = 7, testVal2 = 14;
        System.out.println(testVal1+ " "+ String.valueOf(bst.find(testVal1)? " exists in BST": " doesn't exist in BST"));
        System.out.println(testVal2+ " "+ String.valueOf(bst.find(testVal2)? " exists in BST": " doesn't exist in BST"));

        //delete
        // case 1 : no children => null in parent node
        // case 2 : one child => replace with child
        // case 3 : two children => find min node from right sub-tree , replace with deleted node, delete the min value node
        if(bst.find(testVal1)){
            bst.delete(testVal1);
        }
        System.out.println("\n----------After delete 7 : InOrder Traverse----------");
        bst.inOrderTraverse();

        if(bst.find(5)){
            bst.delete(5);
        }
        System.out.println("\n----------After delete 5 : InOrder Traverse----------");
        bst.inOrderTraverse();
    }

    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
            left = right = null;
        }

    }

    static class BST{
        Node root;

        public BST(){
            root = null;
        }


        public void insert(int val){
            root = insertRecursive(root, val);
        }

        private Node insertRecursive(Node current, int val) {
            if(current == null){
                return new Node(val);
            }

            if(val < current.data){
                current.left = insertRecursive(current.left, val);
            }
            else if(val > current.data){
                current.right = insertRecursive(current.right, val);
            }
            return current;
        }

        public void inOrderTraverse(){
            inOrderRecursive(root);
        }

        private void inOrderRecursive(Node root) {
            if(root != null){
                inOrderRecursive(root.left);
                System.out.print(root.data + "  ");
                inOrderRecursive(root.right);
            }
        }

        public void preOrderTraverse(){
            preOrderRecursive(root);
        }

        private void preOrderRecursive(Node root) {
            if(root != null){
                System.out.print(root.data + "  ");
                preOrderRecursive(root.left);
                preOrderRecursive(root.right);
            }
        }

        public void postOrderTraverse(){
            postOrderRecursive(root);
        }

        private void postOrderRecursive(Node root) {
            if(root != null){
                postOrderRecursive(root.left);
                postOrderRecursive(root.right);
                System.out.print(root.data + "  ");
            }
        }

        public boolean find(int val){
            return findRecursive(root, val);
        }

        private boolean findRecursive(Node cur, int val) {
            if(cur == null){
                return false;
            }

            if(cur.data == val)return true;

            return val<cur.data? findRecursive(cur.left, val): findRecursive(cur.right, val);
        }


        public void traverseLevelOrder(){
            if(root == null)return;

            Queue<Node> nodeList = new LinkedList<>();
            nodeList.add(root);

            while(!nodeList.isEmpty()){
                Node node = nodeList.poll();

                System.out.print(node.data+ " ");

                if(node.left != null){
                    nodeList.add(node.left);
                }
                if(node.right != null){
                    nodeList.add(node.right);
                }
            }

        }
        
        public void delete(int val){
            root = deleteRecursive(root, val);
        }

        private Node deleteRecursive(Node cur, int val) {
            if(cur == null)return null;

            if(cur.data == val){
                //delete
                //no child
                if(cur.left == null && cur.right == null){
                    return null;
                }
                //one child
                else if(cur.left == null){
                    return cur.right;
                }
                else if(cur.right == null){
                    return cur.left;
                }
                // two children
                else{
                    int minVal = findMinimum(cur.right); // finding minimum value from right sub-tree
                    cur.data = minVal; // replace the value with the item need to delete
                    cur.right = deleteRecursive(cur.right, minVal); // recursive call to delete the minimum from we found
                    return cur;
                }
            }
            else if(cur.data > val){
                cur.left = deleteRecursive(cur.left, val);
            }
            else{
                cur.right = deleteRecursive(cur.right, val);
            }
            return cur;
        }

        private int findMinimum(Node cur){
            return cur.left == null? cur.data: findMinimum(cur.left);
        }
    }
}


