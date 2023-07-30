package org.zmz.sb3.newfeatures.algorithm.basic;

public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int m = (low + high) >>> 1;
            int midVal = arr[m];
            if (midVal < target)
                low = m + 1;
            else if (target < midVal)
                high = m - 1;
            else return m;
        }
        // 返回待插入的位置
        return -(low + 1);
    }

}
