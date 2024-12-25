package ru.vsu.cs.solution.lists;

/**
 * @author Алексеев Никита
 * @param <T> generic type
 */
public class CircularLinkedList<T extends Comparable<T>> implements List<T> {
    /**
     * Circular doubly linked list
     * @value {@code head} head of the list
     * @value {@code tail} tail of the list
     * @value {@code count} number of elements in the list
     */
    private MyListItem<T> head = null;
    private MyListItem<T> tail = null;
    private int count = 0;

    private static <T extends Comparable<T>> int myComparator(T a, T b) {
        return a.compareTo(b);
    }

    private void checkEmpty() throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    public int[] convert() {
        return convertCircularLinkedListToArray();
    }

    /**
     * Convert list to array
     * @return {@code array} new array with elements
     */
    private int[] convertCircularLinkedListToArray() {
        if (head == null) return new int[]{};

        MyListItem<T> current = head;
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = (Integer) current.value; // Assuming T can be cast to Integer
            current = current.next;
        }
        return array;
    }

    @Override
    public T getFirst() throws Exception {
        checkEmpty();
        return head.value;
    }

    @Override
    public T getLast() throws Exception {
        checkEmpty();
        return tail.value;
    }

    private void setFirst(T value) {
        head.value = value;
    }

    private void setLast(T value) {
        tail.value = value;
    }

    private void addLast(T value) {
        MyListItem<T> newNode = new MyListItem<>(value, head, tail);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = head;
        head.prev = tail;
        count++;
    }


    public void clear() {
        head = tail = null;
        count = 0;
    }

    private MyListItem<T> getItem(int index) throws Exception {
        if (index < 0 || index >= size()) {
            throw new Exception("Incorrect index");
        }
        MyListItem<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    @Override
    public void add(int index, T value) throws Exception {
        if (index < 0 || index > count) {
            throw new Exception("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            MyListItem<T> prev = getItem(index - 1);
            MyListItem<T> newNode = new MyListItem<>(value, prev.next, prev);
            prev.next.prev = newNode;
            prev.next = newNode;
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            } else {
                tail = newNode;
            }
            count++;
        }
    }

    private void addFirst(T value) {
        MyListItem<T> newNode = new MyListItem<>(value, head, tail);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            tail.next = head;
            head.prev = tail;
        }
        count++;
    }

    @Override
    public void add(T value) throws Exception {
        addLast(value);
    }

    @Override
    public void set(int index, T value) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Incorrect index");
        }
        MyListItem<T> node = getItem(index);
        node.value = value;
    }

    @Override
    public T get(int index) throws Exception {
        return getItem(index).value;
    }

    public void removeFirst() {
        if (head == null) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        count--;
    }

    @Override
    public void removeLast() throws Exception {
        checkEmpty();
        if (head == tail) {
            removeFirst();
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        count--;
    }

    @Override
    public void remove(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            MyListItem<T> prev = getItem(index - 1);
            MyListItem<T> toRemove = prev.next;
            prev.next = toRemove.next;
            toRemove.next.prev = prev;
            if (toRemove == tail) {
                tail = prev;
            }
            count--;
        }
    }

    @Override
    public int indexOf(T value) {
        MyListItem<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            index++;
            current = current.next;
            if (current == head) {
                break;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        MyListItem<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
            if (current == head) {
                break;
            }
        }
        return false;
    }

    @Override
    public void sort(SortMethod method) throws Exception {
        method.sort(this);
    }


    protected void insertionSort() throws Exception {
        for (int i = 1; i < count; i++) {
            T value = get(i);
            int j;
            for (j = i - 1; j >= 0 && myComparator(get(j), value) > 0; j--) {
                set(j + 1, get(j));
            }
            set(j + 1, value);
        }
    }

    protected void quickSort() throws Exception {
        quickSort(0, count - 1);
    }

    private void quickSort(int start, int end) throws Exception {
        if (start < end) {
            int partitionIndex = partition(start, end);
            quickSort(start, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int start, int end) throws Exception {
        T pivot = get(end);
        int partitionIndex = start;

        for (int i = start; i < end; i++) {
            if (myComparator(get(i), pivot) <= 0) {
                T temp = get(partitionIndex);
                set(partitionIndex, get(i));
                set(i, temp);
                partitionIndex++;
            }
        }

        T temp = get(partitionIndex);
        set(partitionIndex, pivot);
        set(end, temp);
        return partitionIndex;
    }

    private class MyListItem<T> {
        public T value;
        public MyListItem<T> next;
        public MyListItem<T> prev;

        public MyListItem(T value, MyListItem<T> next, MyListItem<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public MyListItem(T value) {
            this(value, null, null);
        }
    }
}
