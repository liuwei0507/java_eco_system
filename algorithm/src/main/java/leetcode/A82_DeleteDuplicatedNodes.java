package leetcode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 重复的元素一个不留
 */
public class A82_DeleteDuplicatedNodes {
    public ListNode deleteDuplicates1(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        if (p.val == p.next.val) {
            ListNode x = p.next.next;
            while (x != null && x.val == p.val) {
                x = x.next;
            }
            // x为与p.val不同的节点
            return deleteDuplicates(x);
        } else {
            p.next = deleteDuplicates(p.next);
            return p;
        }
    }

    /**
     * 双指针方式
     *
     * @param p
     * @return
     */
    public ListNode deleteDuplicates(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        ListNode s = new ListNode(-1, p);
        ListNode p1 = s;
        ListNode p2 = p1.next;
        ListNode p3 = p2.next;
        while (p3 != null) {
            if (p2.val == p3.val) {
                while (p3 != null && p2.val == p3.val) {
                    p3 = p3.next;
                }
                // p3为与p2.val不同的节点
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
            p2 = p1.next;
            if (p2 == null) {
                return s.next;
            }
            p3 = p2.next;
        }
        return s.next;
    }

    public static void main(String[] args) {
//        [1,2,3,3,4,4,5]
        ListNode o7 = new ListNode(5, null);
        ListNode o6 = new ListNode(4, o7);
        ListNode o5 = new ListNode(4, o6);
        ListNode o4 = new ListNode(3, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode r = new A82_DeleteDuplicatedNodes().deleteDuplicates(o1);
        System.out.println(r);
    }
}
