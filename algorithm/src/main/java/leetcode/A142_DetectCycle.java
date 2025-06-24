package leetcode;

/**
 * 判断环形链表，并找到环的入口
 */
public class A142_DetectCycle {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 找到环， 让慢指针从链表头开始， 快指针从相遇点开始， 慢指针每次移动一步， 快指针每次移动两步， 直到相遇， 相遇点就是环的入口
                slow = head;
                while (true) {
                    if (slow == fast) {
                        return slow;
                    }
                    slow = slow.next;
                    fast = fast.next;
                }
            }
        }
        return null;
    }
}
