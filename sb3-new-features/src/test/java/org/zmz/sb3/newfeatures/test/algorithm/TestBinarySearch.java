package org.zmz.sb3.newfeatures.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;

import static org.zmz.sb3.newfeatures.algorithm.basic.BinarySearch.binarySearch;

public class TestBinarySearch {


    public static final int[] arr = {7, 13, 23, 45, 66, 79, 123, 2414};

    @Test
    public void testOk() {
        Assert.isTrue(0 == binarySearch(arr, 7), "OK");
        Assert.isTrue(1 == binarySearch(arr, 13), "OK");
        Assert.isTrue(2 == binarySearch(arr, 23), "OK");
        Assert.isTrue(3 == binarySearch(arr, 45), "OK");
        Assert.isTrue(4 == binarySearch(arr, 66), "OK");
        Assert.isTrue(5 == binarySearch(arr, 79), "OK");
        Assert.isTrue(6 == binarySearch(arr, 123), "OK");
        Assert.isTrue(7 == binarySearch(arr, 2414), "OK");
    }

    @Test
    public void testFail() {
        Assert.isTrue(-4 == Arrays.binarySearch(arr, 36), "OK");
        Assert.isTrue(-9 == Arrays.binarySearch(arr, 3000), "OK");
    }
}
