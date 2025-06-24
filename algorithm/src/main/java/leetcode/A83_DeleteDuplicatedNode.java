package leetcode;

/**
 * 删除有序链表中的重复节点
 */
public class A83_DeleteDuplicatedNode {
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            // 节点数小于2
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = p1.next;
        while (p2 != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;
                p2 = p1.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        if (p.val == p.next.val) {
            return deleteDuplicates(p.next);
        } else {
            p.next = deleteDuplicates(p.next);
            return p;
        }
    }
}

