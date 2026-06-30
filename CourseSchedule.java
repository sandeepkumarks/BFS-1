// Time Complexity : O(V + E)
// Space Complexity : O(V + E)
// Did this code successfully run on LeetCode : Yes

// - Build an adjacency list and indegree array from the prerequisites.
// - Use a queue to process courses with zero indegree and reduce the indegree of their dependent courses.
// - If all courses are processed, no cycle exists; otherwise, a cycle prevents completing all courses.

class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int finishedCourses = 0;
        int[] indegrees = new int[numCourses];
        Map<Integer, List<Integer>> preRequisiteMap = new HashMap<>();

        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> current = null;
            if(preRequisiteMap.containsKey(prerequisites[i][1])) {
                current = preRequisiteMap.get(prerequisites[i][1]);
            } else {
                current = new ArrayList<>();
            }
            current.add(prerequisites[i][0]);
            preRequisiteMap.put(prerequisites[i][1], current);

            indegrees[prerequisites[i][0]]++;
        }

        Queue<Integer> independentCourses = new LinkedList<>();
        for(int i = 0; i < indegrees.length; i++) {
            if(indegrees[i] == 0) {
                independentCourses.offer(i);
                finishedCourses++;
            }
        }

        while(!independentCourses.isEmpty()) {
            int course = independentCourses.poll();
            List<Integer> dependentCourses = preRequisiteMap.get(course);
            if(dependentCourses == null) {
                continue;
            }
            for(int dependentCourse : dependentCourses) {
                indegrees[dependentCourse]--;
                if(indegrees[dependentCourse] == 0) {
                    independentCourses.offer(dependentCourse);
                    finishedCourses++;
                }
            }
        }

        return finishedCourses == numCourses;
    }
}