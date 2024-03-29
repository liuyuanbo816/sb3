package leetcode.array;

public class LC2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);

        l1.next = l2;
        l2.next = l3;

        System.out.println(getListNodeLen(l1));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Len = getListNodeLen(l1);
        int l2Len = getListNodeLen(l2);

        int minLen = Math.min(l1Len, l2Len);




    }

    public static int getListNodeLen(ListNode ln) {
        int res = 1;
        while (ln.next != null) {
            res++;
            ln = ln.next;
        }
        return res;
    }
}
