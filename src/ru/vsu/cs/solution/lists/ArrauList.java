package ru.vsu.cs.solution.lists;

public class ArrauList<T extends Comparable<T>> implements List<T> {
    private MyListItem<T> head = null;
    private MyListItem<T> tail = null;
    private int size = 0;

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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
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

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void add(int index, T value) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            MyListItem<T> prev = getItem(index - 1);
            MyListItem<T> newNode = new MyListItem<>(value, prev.next);
            prev.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
            size++;
        }
    }

    @Override
    public void add(T value) throws Exception {
        addLast(value);
    }

    public void addFirst(T value) {
        head = new MyListItem<>(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    protected void addLast(T value) {
        MyListItem<T> newNode = new MyListItem<>(value, null);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void set(int index, T value) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Incorrect index");
        }
        MyListItem<T> node = getItem(index);
        node.value = value;
    }

    @Override
    public T get(int index) throws Exception {
        return getItem(index).value;
    }

    private MyListItem<T> getItem(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Incorrect index");
        }
        MyListItem<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    @Override
    public void removeFirst() throws Exception {
        checkEmpty();
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    @Override
    public void removeLast() throws Exception {
        checkEmpty();
        if (size == 1) {
            removeFirst();
        } else {
            MyListItem<T> prev = getItem(size - 2);
            prev.next = null;
            tail = prev;
            size--;
        }
    }

    @Override
    public void remove(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            MyListItem<T> prev = getItem(index - 1);
            MyListItem<T> toRemove = prev.next;
            prev.next = toRemove.next;
            if (toRemove.next == null) {
                tail = prev;
            }
            size--;
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
        }
        return -1;
    }

    @Override
    public void sort(SortMethod method) throws Exception {
        method.sort(this);

    }

    protected void insertionSort() throws Exception {
        for (int i = 1; i < size; i++) {
            T value = get(i);
            int j;
            for (j = i - 1; j >= 0 && myComparator(get(j), value) > 0; j--) {
                set(j + 1, get(j));
            }
            set(j + 1, value);
        }
    }

    private void quickSort(int start, int end) throws Exception {
        if (start < end) {
            int partitionIndex = partition(start, end);
            quickSort(start, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    protected void quickSort() throws Exception {
        quickSort(0, size - 1);
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

    @Override
    public boolean contains(T value) {
        MyListItem<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private class MyListItem<T> {
        T value;
        MyListItem<T> next;

        public MyListItem(T value, MyListItem<T> next) {
            this.value = value;
            this.next = next;
        }
    }


}
