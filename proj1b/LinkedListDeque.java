public class LinkedListDeque<T> implements Deque<T> {
    private class ListNode {
        public ListNode prev;
        public T item;
        public ListNode next;

        //have not considered the empty list
        //ListNode constructor
        public ListNode(ListNode pre, T n, ListNode nex) {
            prev = pre;
            next = nex;
            item = n;
        }

    }

    private ListNode sentinel = new ListNode(null, null, null);//only one sentinel,and its item is 0
    private int size = 0;

    //symotion-prefix)symotion-prefix)constructor of empty list
    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.item = null;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        ListNode add_node = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.prev = add_node;
        sentinel.next = add_node;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        ListNode add_node = new ListNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = add_node;
        sentinel.prev = add_node;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        //maybe here have some problem
        ListNode moving = sentinel;
        while (moving.next != sentinel) {
            System.out.print(moving.next.item + " ");
            moving = moving.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0)
            return null;
        else {
            ListNode removed_node = sentinel.next;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return removed_node.item;
        }
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            ListNode removed_node = sentinel.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return removed_node.item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size)
            return null;
        else {
            ListNode moving_node = sentinel;
            while (index != 0) {
                moving_node = moving_node.next;
                index = index - 1;
            }
            return moving_node.next.item;
        }
    }

   public T getRecursive(int index) {
        ListNode helper_node = sentinel;
        if (index >= size || index < 0)
            return null;
        else
            return getRecursive_helper(index, helper_node);

    }

    private T getRecursive_helper(int index, ListNode helper_node) {
        if (index == 0)
            return helper_node.next.item;
        else
            return getRecursive_helper(index - 1, helper_node.next);
    }
}
