package ru.vsu.cs.solution.lists;

/**
 * @param <T>
 * @author Алексеев Никита
 */
public interface List<T> {
    boolean isEmpty();

    int size();

    T getFirst() throws Exception;

    T getLast() throws Exception;

    void clear();

    void add(int index, T value) throws Exception;

    void add(T value) throws Exception;

    void set(int index, T value) throws Exception;

    T get(int index) throws Exception;

    void removeFirst() throws Exception;

    void removeLast() throws Exception;

    void remove(int index) throws Exception;

    int indexOf(T value);

    void sort(SortMethod method) throws Exception;

    boolean contains(T value);
}