public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        first = last = 0;
    }

    private void resize() {
        if (size >= 8) {
            if (items.length == size) {
                T[] new_items = (T[]) new Object[items.length * 4];
                if (first < last) // i.e. first == 0,last == size-1
                {
                    //copy the items
                    System.arraycopy(items, first, new_items, first, items.length);
                } else {
                    System.arraycopy(items, 0, new_items, 0, last + 1);
                    System.arraycopy(items, first, new_items, items.length * 4 - (size - first), size - first);
                    first = items.length * 4 - (size - first);
                }
                items = new_items;
            } else if (size <= items.length / 4) {
                T[] new_items = (T[]) new Object[items.length / 4];
                if (first <= last) {
                    System.arraycopy(items, first, new_items, 0, size);
                    first = 0;
                    last = size - 1;
                } else {
                    System.arraycopy(items, 0, new_items, 0, last + 1);
                    System.arraycopy(items, first, new_items, last + 1, items.length - first);
                    first = last + 1;
                }
                items = new_items;
            }
        }
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) //i.e. the array is full
            resize();
        if (size != 0) {
            first = first - 1;
            if (first < 0)
                first += items.length;
            items[first] = item;
            size++;
        } else {
            items[first] = item;
            size++;
        }
    }
    @Override
    public void addLast(T item) {
        if (size == items.length)
            resize();
        if (size != 0) {
            last = last + 1;
            if (last > items.length - 1)
                last -= items.length;
            items[last] = item;
            size++;
        } else {
            items[last] = item;
            size++;
        }
    }
    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int moving_index = first;
        while (moving_index != last) {
            System.out.print(items[moving_index] + " ");
            if (moving_index == items.length) {
                moving_index = 0;
            } else moving_index++;
        }
        System.out.print(items[moving_index] + "");//print the last item
    }

    @Override
    public T removeFirst() {
        T remove_item = items[first];
        items[first] = null;
        if (size != 0) {
            if (size != 1) {
                if (first == items.length - 1) {
                    first = 0;
                } else
                    first++;
                size--;
            } else {
                size--;
            }
            resize();
            return remove_item;
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
        T remove_item = items[last];
        items[last] = null;
        if (size != 0) {
            if (size != 1) {
                if (last == 0) {
                    last = items.length - 1;
                } else
                    last--;
                size--;
            } else {
                size--;
            }
            resize();
            return remove_item;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        if (index + first <= items.length - 1) {
            return items[index + first];
        } else
            return items[index + first - items.length];
    }
}
