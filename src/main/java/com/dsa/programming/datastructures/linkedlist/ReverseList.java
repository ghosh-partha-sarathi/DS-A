package com.dsa.programming.datastructures.linkedlist;

/**
 * Implement a method called reverse that reverses the order of the nodes in the linked list.*
 * When solving the reverse() method, students are not allowed to create a new list or use any additional data structures besides the linked list itself.*
 * They must reverse the nodes in the existing linked list by manipulating the pointers between them.*
 * Return type: void
 */
public class ReverseList {

    class LinkedList {

        private Node head;
        private Node tail;
        private int length;

        class Node {
            int value;
            Node next;

            Node(int value) {
                this.value = value;
            }
        }

        public LinkedList(int value) {
            Node newNode = new Node(value);
            head = newNode;
            tail = newNode;
            length = 1;
        }

        public Node getHead() {
            return head;
        }

        public Node getTail() {
            return tail;
        }

        public int getLength() {
            return length;
        }

        public void printList() {
            Node temp = head;
            while (temp != null) {
                System.out.println(temp.value);
                temp = temp.next;
            }
        }

        public void printAll() {
            if (length == 0) {
                System.out.println("Head: null");
                System.out.println("Tail: null");
            } else {
                System.out.println("Head: " + head.value);
                System.out.println("Tail: " + tail.value);
            }
            System.out.println("Length:" + length);
            System.out.println("\nLinked List:");
            if (length == 0) {
                System.out.println("empty");
            } else {
                printList();
            }
        }

        public void makeEmpty() {
            head = null;
            tail = null;
            length = 0;
        }

        public void append(int value) {
            Node newNode = new Node(value);
            if (length == 0) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            length++;
        }

        public Node removeLast() {
            if (length == 0) return null;
            Node temp = head;
            Node pre = head;
            while(temp.next != null) {
                pre = temp;
                temp = temp.next;
            }
            tail = pre;
            tail.next = null;
            length--;
            if (length == 0) {
                head = null;
                tail = null;
            }
            return temp;
        }

        public void prepend(int value) {
            Node newNode = new Node(value);
            if (length == 0) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            length++;
        }

        public Node removeFirst() {
            if (length == 0) return null;
            Node temp = head;
            head = head.next;
            temp.next = null;
            length--;
            if (length == 0) {
                tail = null;
            }
            return temp;
        }

        public Node get(int index) {
            if (index < 0 || index >= length) return null;
            Node temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public boolean set(int index, int value) {
            Node temp = get(index);
            if (temp != null) {
                temp.value = value;
                return true;
            }
            return false;
        }

        public boolean insert(int index, int value)  {
            if (index < 0 || index > length) return false;
            if (index == 0) {
                prepend(value);
                return true;
            }
            if (index == length) {
                append(value);
                return true;
            }
            Node newNode = new Node(value);
            Node temp = get(index - 1);
            newNode.next = temp.next;
            temp.next = newNode;
            length++;
            return true;
        }

        public Node remove(int index) {
            if (index < 0 || index >= length) return null;
            if (index == 0) return removeFirst();
            if (index == length - 1) return removeLast();

            Node prev = get(index - 1);
            Node temp = prev.next;

            prev.next = temp.next;
            temp.next = null;
            length--;
            return temp;
        }
    }

    private static LinkedList inputList=null;
    public static void main(String...a){
        ReverseList reverseListObj = new ReverseList();
        inputList=reverseListObj.getInputList();
        /* printing input list */
        inputList.printList();
        reverseListObj.reverse();
        /* printing list after reversal */
        System.out.println("printing list after reversal...");
        inputList.printList();
    }

    private LinkedList getInputList(){
        LinkedList aList = new LinkedList(10);
        aList.append(11);
        aList.append(22);
        aList.append(33);
        aList.append(44);
        aList.append(55);
        aList.append(66);
        aList.append(77);
        return aList;
    }
    public void reverse(){
        LinkedList.Node curNode = inputList.head;
        LinkedList.Node prevNode = null;
        LinkedList.Node nextNode = null;
        inputList.head=inputList.tail;
        inputList.tail=curNode;
        for(int index=0; index<inputList.length; index++){
            nextNode=curNode.next;
            curNode.next=prevNode;
            prevNode=curNode;
            curNode=nextNode;
        }
    }
}
