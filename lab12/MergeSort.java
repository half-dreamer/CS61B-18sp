<<<<<<< HEAD
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> queueOfQueue = new Queue<>();
        for (Item item : items) {
            Queue<Item> itemQueue = new Queue<>();
            itemQueue.enqueue(item);
            queueOfQueue.enqueue(itemQueue);
        }
        return queueOfQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> mergedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            Item minItem = getMin(q1, q2);
            mergedQueue.enqueue(minItem);
        }
        return mergedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Item> leftQueue = new Queue<>();
        Queue<Item> rightQueue = new Queue<>();
        if (items.size() == 1) {
            return items;
        }
        int mid = items.size() / 2;
        while (mid > 0) {
            leftQueue.enqueue(items.dequeue());
            mid -- ;
        }
        while (!items.isEmpty()) {
            rightQueue.enqueue(items.dequeue());
        }
        Queue<Item> sortedLeft = mergeSort(leftQueue);
        Queue<Item> sortedRight = mergeSort(rightQueue);
        return mergeSortedQueues(sortedLeft,sortedRight);
    }

    public static void  main(String[] args) {
        Queue<Integer> testQueue = new Queue<>();
        testQueue.enqueue(5);
        testQueue.enqueue(7);
        testQueue.enqueue(2);
        testQueue.enqueue(3);
        testQueue.enqueue(0);
        testQueue.enqueue(4);
        for (int i : testQueue) {
            System.out.print(i);
        }
        System.out.println();
        Queue<Integer> sortedQueue = mergeSort(testQueue);
        for (int i : sortedQueue) {
            System.out.print(i);
        }
    }
}
=======
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> queueOfQueue = new Queue<>();
        for (Item item : items) {
            Queue<Item> itemQueue = new Queue<>();
            itemQueue.enqueue(item);
            queueOfQueue.enqueue(itemQueue);
        }
        return queueOfQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> mergedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            Item minItem = getMin(q1, q2);
            mergedQueue.enqueue(minItem);
        }
        return mergedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Item> leftQueue = new Queue<>();
        Queue<Item> rightQueue = new Queue<>();
        if (items.size() == 1) {
            return items;
        }
        int mid = items.size() / 2;
        while (mid > 0) {
            leftQueue.enqueue(items.dequeue());
            mid -- ;
        }
        while (!items.isEmpty()) {
            rightQueue.enqueue(items.dequeue());
        }
        Queue<Item> sortedLeft = mergeSort(leftQueue);
        Queue<Item> sortedRight = mergeSort(rightQueue);
        return mergeSortedQueues(sortedLeft,sortedRight);
    }

    public static void  main(String[] args) {
        Queue<Integer> testQueue = new Queue<>();
        testQueue.enqueue(5);
        testQueue.enqueue(7);
        testQueue.enqueue(2);
        testQueue.enqueue(3);
        testQueue.enqueue(0);
        testQueue.enqueue(4);
        for (int i : testQueue) {
            System.out.print(i);
        }
        System.out.println();
        Queue<Integer> sortedQueue = mergeSort(testQueue);
        for (int i : sortedQueue) {
            System.out.print(i);
        }
    }
}
>>>>>>> 7afade8f1c7ec3c5c20e6a621ffec6bb636477bf
