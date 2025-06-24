package leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int... vals) {
        ListNode head = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            head = new ListNode(vals[i], head);
        }
        return head;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
