package leetcode;

/**
 * 回文链表
 */
public class A234_Palindrome {

    /**
     * 判断链表是否是回文 II
     * 思路：
     * 1. 找到链表的中间节点，并反转前半部分链表
     * 3. 逐个比较
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode n1 = null;// 反转后新链表的头
        ListNode o1 = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 反转
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }

        if (fast != null) { // 奇数个节点
            slow = slow.next;
        }

        while (n1 != null) {
            if (n1.val != slow.val) {
                return false;
            }
            slow = slow.next;
            n1 = n1.next;
        }
        return true;
    }

    /**
     * 判断链表是否是回文
     * 思路：
     * 1. 找到链表的中间节点
     * 2. 中间节点后半个链表反转
     * 3. 反转后的链表与原链表逐一比较
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode middle = slow;
        ListNode n1 = null;
        while (middle != null) {
            ListNode o2 = middle.next;
            middle.next = n1;
            n1 = middle;
            middle = o2;
        }
        ListNode reverse = n1;
        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1, null))));
        new A234_Palindrome().isPalindrome(head);
    }

}
