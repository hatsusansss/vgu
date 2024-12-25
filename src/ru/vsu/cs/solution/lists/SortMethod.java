package ru.vsu.cs.solution.lists;

public enum SortMethod {
    INSERTION {
        @Override
        public void sort(LinkedList list) throws Exception {
            list.insertionSort();
        }

        @Override
        public void sort(CircularLinkedList list) throws Exception {
            list.insertionSort();
        }

        @Override
        public void sort(ArrauList list) throws Exception {
            list.insertionSort();
        }
    },
    QUICK {
        @Override
        public void sort(LinkedList list) throws Exception {
            list.quickSort();
        }

        @Override
        public void sort(CircularLinkedList list) throws Exception {
            list.quickSort();
        }

        @Override
        public void sort(ArrauList list) throws Exception {
            list.quickSort();
        }
    };

    public abstract void sort(LinkedList list) throws Exception;

    public abstract void sort(CircularLinkedList tCircularLinkedList)throws Exception;

    public abstract void sort(ArrauList myList)throws Exception;
}