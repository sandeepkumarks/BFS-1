// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on LeetCode : Yes

// - Use a queue to process the tree level by level.
// - Process all nodes currently in the queue to build one level of the result.
// - Add each node's children to the queue for the next level.

class BTLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> eachLevel  = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                eachLevel.add(current.val);

                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(eachLevel);

        }

        return result;
    }
}