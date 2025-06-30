package leetcode;

/**
 * 23. 合并K个排序链表
 */
public class A23_MergeMultipleLists_2 {

    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);
        // 将链表头节点加入小顶堆中
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }
        //不断从堆顶移除最小元素，并加入到结果链表中
        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            t.next = min;
            t = min;
            if (min.next != null) {
                heap.offer(min.next);
            }
        }
        return s.next;
    }

}
