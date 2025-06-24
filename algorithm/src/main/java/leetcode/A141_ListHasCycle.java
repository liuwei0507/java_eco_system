package leetcode;

/**
 * 判断链表是否有环
 */
public class A141_ListHasCycle {
    /**
     * 快慢指针  -- 弗洛伊德龟兔赛跑算法
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
