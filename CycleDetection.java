/**
 * Created by HaMi on 2017-09-28.
 * This program checks a LinkedList for any cycles
 * hasCycle() returns true if there is a cycle and false is there isn't
 * detectAndRemove() checks for a cycle and then removes the cycle from the LinkedList
 */

class LinkedList {
    private Node head;

    private static class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        // This LinkedList has a cycle
        LinkedList list = new LinkedList();
        Node loopNode = new Node(3);
        list.add(new Node(1));
        list.add(new Node(2));
        list.add(loopNode);
        list.add(new Node(4));
        list.add(new Node(5));
        list.add(loopNode);
        boolean a = hasCycle(list.head);
        System.out.println("There was a cycle detected: " + a);
        list.detectAndRemove(list.head);
        list.print(list.head);

        System.out.println("----------");

        // This LinkedList does not have a cycle
        LinkedList list2 = new LinkedList();
        list2.add(new Node(1));
        list2.add(new Node(2));
        boolean b = hasCycle(list2.head);
        System.out.println("There was a cycle detected: " + b);
        list2.detectAndRemove(list2.head);
        list2.print(list2.head);
    }

    // Adding nodes to the LinkedList
    private void add(Node node) {
        // if LinkedList is empty then we make the new node the head node
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    // this method just returns true or false if there is a cycle detected
    private static boolean hasCycle(Node head) {
        if (head == null) return false;
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    // this method detects and removes the loop from the LinkedList
    private void detectAndRemove(Node head) {
        // covering the base cases; if the list is empty of only has one node
        if (head == null || head.next == null) {
            return;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        // checks to see if there is a loop
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        // if the loop exists
        if (fast == slow) {
            slow = head;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            // since fast.next and slow.next is both pointing to the same place and fast.next is inside the loop, we will set fast.next to point null in order to end the cycle
            fast.next = null;
        }
        // if there was no cycle
        else {
            System.out.println("There was no cycle detected in the LinkedList");
        }
    }

    // method to print the contents of the LinkedList
    private void print(Node node) {
        System.out.print("The LinkedList is: ");
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}

