package leetcode.array;

public class LC9 {
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0) return false;

        //121
        while (x / 10 != 0) {
            int i = x % 10;
            x = x / 10;
        }
    }
}
