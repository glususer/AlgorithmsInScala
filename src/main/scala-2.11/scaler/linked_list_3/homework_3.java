package scaler.linked_list_3;

import java.util.HashMap;

public class homework_3 {

    public static class Solution {

        // Create the Doubly LinkedList Class
        class DLinkedListNode
        {
            int key;
            int value;
            homework_3.Solution.DLinkedListNode prev;
            homework_3.Solution.DLinkedListNode next;

            public DLinkedListNode(int key, int value)
            {
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        int capacity;

        //HashMap to store the Key Value and DoublyLinkedList Address
        HashMap<Integer, DLinkedListNode> keyVsNode;

        //HeadNode & TailNode to keep track of the list
        public DLinkedListNode head = null;
        public DLinkedListNode tail = null;

        public Solution(int capacity) {
            this.capacity = capacity;
            keyVsNode = new HashMap<Integer, DLinkedListNode>(this.capacity);
        }

        public int get(int key) {
            if ( keyVsNode.containsKey(key) )
            {
                DLinkedListNode currentNode = keyVsNode.get(key);

                // last node, so dont remove the tail
                if (currentNode.next != null)
                {
                    removeNode(currentNode);

                    appendAtTail(currentNode);
                }

                return currentNode.value;
            }

            return -1;
        }

        public void set(int key, int value) {

            if ( keyVsNode.containsKey(key) )
            {
                DLinkedListNode updateNode = keyVsNode.get(key);

                updateNode.value = value;

                // If the node is not the last then remove the node and its entry in hashmap and then again
                // insert the node with new value.
                if ( updateNode.next != null )
                {
                    removeNode(updateNode);

                    keyVsNode.remove(updateNode.key);

                    updateNode = new DLinkedListNode(key, value);

                    keyVsNode.put(key, updateNode);

                    appendAtTail(updateNode);
                }
            }
            else
            {
                DLinkedListNode newNode = new DLinkedListNode(key, value);

                if (keyVsNode.size() == capacity)
                {
                    DLinkedListNode deleteNode = popHeadNode();

                    keyVsNode.remove(deleteNode.key);

                    keyVsNode.put(key, newNode);

                    appendAtTail(newNode);

                }
                else
                {
                    keyVsNode.put(key, newNode);

                    appendAtTail(newNode);
                }
            }

        }

        public void removeNode(homework_3.Solution.DLinkedListNode currentNode)
        {
            // Condition for checking if its a single node
            if ( currentNode.next == null && currentNode.prev == null )
            {
                head = null;
                tail = null;

                return;
            }

            // Condition to check if the node is last node and update the tailNode
            if ( currentNode == tail)
            {
                tail = currentNode.prev;

                return;
            }

            // If the head is pointing to the currentNode point it to the nextNode
            if ( head == currentNode ){
                head = head.next;
            }

            currentNode.next.prev = currentNode.prev;

            if (currentNode.prev != null)
            {
                currentNode.prev.next = currentNode.next;
            }

        }

        public void appendAtTail(DLinkedListNode node)
        {

            if (head == null)
            {
                head = node;
                tail = node;

                return;
            }


            tail.next = node;

            node.prev = tail;

            tail = node;

        }

        public DLinkedListNode popHeadNode()
        {
            DLinkedListNode deleteNode = head;

            if (head.next == null && head.prev == null)
            {
                head = null;

                tail = null;
            }
            else
            {
                head = head.next;
            }

            return deleteNode;

        }

        public void printList()
        {
            homework_3.Solution.DLinkedListNode current = head;
            while(current !=null){
                System.out.print(current.key+"->"+current.value);
            }
            System.out.println("\n");

        }
    }

    //6 1 S 2 1 S 2 2 G 2 S 1 1 S 4 1 G 2
    public static void main(String[] args) {
        Solution sol = new Solution(1);
        sol.set(2, 1);
        sol.printList();

        sol.get( 2);
        sol.printList();


    /* sol.get(3);
     sol.printList();

     sol.get(2);
     sol.printList();

     sol.set(4, 3);
     sol.printList();

     sol.get(2);
     sol.printList();

     sol.get(3);
     sol.printList();

     sol.get(4);
     sol.printList();
*/

        // System.out.println(sol.get(20));

    /*sol.set(30,300);
    sol.printList();

    sol.set(40,120);
    sol.printList();

    sol.set(19,120);
    sol.printList();

    sol.set(18,120);
    sol.printList();

    sol.set(20,120);
    sol.printList();*/
    }
}
