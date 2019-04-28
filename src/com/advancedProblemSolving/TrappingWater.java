package com.advancedProblemSolving;

public class TrappingWater {
    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingWater tw = new TrappingWater();
        int maxTrappedWater = tw.findMaxTrappedWater(heights);
        System.out.println("Max trapped waters : " + maxTrappedWater);
    }

    private int findMaxTrappedWater(int[] heights) {
        if (heights.length == 0)
            return 0;

        int max = 0;

        int[] leftSubArray = new int[heights.length];
        int[] rightSubArray = new int[heights.length];

        leftSubArray[0] = heights[0];
        rightSubArray[heights.length - 1] = heights[heights.length - 1];

        for (int i = 1; i < heights.length; i++) {
            leftSubArray[i] = Math.max(leftSubArray[i - 1], heights[i]);
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            rightSubArray[i] = Math.max(rightSubArray[i + 1], heights[i]);
        }

        for (int i = 0; i < heights.length; i++) {
            max += Math.min(leftSubArray[i], rightSubArray[i]) - heights[i];
        }

        return max;
    }
}
