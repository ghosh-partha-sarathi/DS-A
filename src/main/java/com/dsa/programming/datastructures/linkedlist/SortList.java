package com.dsa.programming.datastructures.linkedlist;

/**
 * Implement a method sort() to sort a given LinkedList
 */
public class SortList {
    static class LinkedList {
        private Node head;
        private Node tail;
        private int length;

        static class Node {
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
                // System.out.println(temp.value);
                System.out.print(temp.value+" -> ");
                temp = temp.next;
            }
            System.out.println();
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
    }

    private static LinkedList inputList=null;
    public static void main(String...a){
        SortList sortListObj = new SortList();
        inputList=sortListObj.getInputList1st();
        /* printing input list */
        inputList.printList();
        // sortListObj.sortUsingBubbleSortAlgorithm(inputList);
        // sortListObj.sortUsingSelectionSortAlgorithm(inputList);
        sortListObj.sortUsingInsertionSortAlgorithm(inputList);
        /* printing list after reversal */
        System.out.println("printing list after sorting...");
        inputList.printList();
    }

    /**
     * sorts a given singly linkedlist using bubble sort algorithm
     * @param inputList sample linked list to be sorted
     */
    public void sortUsingBubbleSortAlgorithm(LinkedList inputList) {
        System.out.println("Sorting input list using bubble sort algorithm...");
        if(inputList.length<=1) {
            inputList.printList();
            return;
        }
        LinkedList.Node curNode = inputList.head;
        LinkedList.Node compareNode = curNode;

        while(curNode.next!=null) {
            compareNode=curNode.next;
            while(compareNode!=null) {
                if(compareNode.value < curNode.value) {
                    int temp = compareNode.value;
                    compareNode.value = curNode.value;
                    curNode.value = temp;
                }
                compareNode=compareNode.next;
            }
            curNode=curNode.next;
        }

        System.out.println("Sorted List: ");
        inputList.printList();
    }

    /**
     * sorts a given singly linkedlist using selection sort algorithm
     * @param inputList sample linked list to be sorted
     */
    public void sortUsingSelectionSortAlgorithm(LinkedList inputList) {
        System.out.println("Sorting input list using selection sort...");
        if(inputList.length<=1) {
            inputList.printList();
            return;
        }
        LinkedList.Node curNode = inputList.head;
        LinkedList.Node minNode;

        while(curNode.next!=null) {
            minNode=curNode.next;
            int min = curNode.value;
            LinkedList.Node temp = null;
            while(minNode!=null) {
                if(minNode.value < min) {
                    min=minNode.value;
                    temp = minNode;
                }
                minNode=minNode.next;
            }
            if(temp!=null) {
                int tempVal = curNode.value;
                curNode.value = temp.value;
                temp.value = tempVal;
            }
            curNode=curNode.next;
        }

        System.out.println("Sorted List: ");
        inputList.printList();
    }

    /**
     * sorts a given singly linkedlist using insertion sort algorithm
     * @param inputList sample linked list to be sorted
     */
    public void sortUsingInsertionSortAlgorithm(LinkedList inputList) {
        System.out.println("Sorting input list using insertion sort...");
        if(inputList.length<=1) {
            inputList.printList();
            return;
        }
        LinkedList.Node compareNode = inputList.head.next;
        int compareIndex = 1;
        while(compareNode!=null) {
            LinkedList.Node curNode = inputList.head;
            int curIndex = 0;
            while(curNode!=null && curIndex<compareIndex) {
                if(compareNode.value < curNode.value) {
                    int temp = curNode.value;
                    curNode.value = compareNode.value;
                    compareNode.value = temp;
                }
                curNode=curNode.next;
                curIndex++;
            }
            compareNode=compareNode.next;
            compareIndex++;
        }

        System.out.println("Sorted List: ");
        inputList.printList();
    }

    private LinkedList getInputList1st(){
        LinkedList aList = new LinkedList(10);
        aList.append(22);
        aList.append(11);
        aList.append(33);
        aList.append(55);
        aList.append(44);
        aList.append(66);
        return aList;
    }
}
