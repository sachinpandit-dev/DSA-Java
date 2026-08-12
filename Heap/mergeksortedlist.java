/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> minheap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        for(ListNode list : lists){
            if(list != null){
                minheap.add(list);
            }
            
        }

        ListNode dummy = new ListNode(-1);

        ListNode tail = dummy;

        while(!minheap.isEmpty()){

            ListNode current = minheap.poll();

            tail.next = current;

            

            tail = tail.next;

            if(current.next != null){
                minheap.add(current.next);
            }


        }

        return dummy.next;
        
    }
}