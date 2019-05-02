package com.advancedProblemSolving;

public class LowestCommonAncestor {

    public TreeNode lcaNode = null;

    public static void main(String[] args) {
        LowestCommonAncestor l = new LowestCommonAncestor();
        TreeNode root = l.createTree();

        l.findLCA(root, 2, 3);
        System.out.println("Lowest common ancestor of 2 and 3 : " + l.lcaNode.val);


        l.findLCA(root, 4, 8);
        System.out.println("Lowest common ancestor of 4 and 8 : " + l.lcaNode.val);

    }

    private boolean findLCA(TreeNode root, int p, int q) {

        if (root == null)
            return false;

        boolean left = findLCA(root.left, p, q);
        boolean right = findLCA(root.right, p, q);

        boolean nodeFound = false;
        if (root.val == p || root.val == q) {
            nodeFound = true;
        }

        if((left && right) || (nodeFound && left) || (nodeFound && right)){
            this.lcaNode = root;
        }

        // if any flag is true return true
        return (left || right || nodeFound);
    }

    /**
     * Creates a default tree and returns root
     * <p>
     *         7
     *      /     \
     *     5       8
     *   /  \     /  \
     *  4    3   9    1
     *   \
     *    2
     *
     * @return TreeNode root
     */
    private TreeNode createTree() {

        // create leaf node objects first
        TreeNode node2 = new TreeNode(null, null, 2);
        TreeNode node3 = new TreeNode(null, null, 3);
        TreeNode node9 = new TreeNode(null, null, 9);
        TreeNode node1 = new TreeNode(null, null, 1);

        // intermediate nodes
        TreeNode node4 = new TreeNode(null, node2, 4);
        TreeNode node5 = new TreeNode(node4, node3, 5);
        TreeNode node8 = new TreeNode(node9, node1, 8);

        // root node
        TreeNode root = new TreeNode(node5, node8, 7);
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
}