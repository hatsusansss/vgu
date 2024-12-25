package ru.vsu.cs.solution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.solution.lists.LinkedList;
import ru.vsu.cs.solution.lists.SortMethod;

import static org.junit.jupiter.api.Assertions.*;

public class MyListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testAdd() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }

    @Test
    void testAddFirst() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{3, 2, 1}, list.convert());
    }

    @Test
    void testAdd1() throws Exception {
        list.add(0, 1);
        list.add(1, 2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 3, 2}, list.convert());
    }


    @Test
    void testAdd2() throws Exception {
        list.add(1);
        list.add(2);
        list.add(2, 3);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }


    @Test
    void testAddInMiddle() throws Exception {
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }

    @Test
    void testRemoveFirst() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeFirst();
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{2, 3}, list.convert());
    }

    @Test
    void testRemoveLast() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeLast();
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{1, 2}, list.convert());
    }

    @Test
    void testRemoveAtIndex() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertEquals(2, list.size());
        assertArrayEquals(new int[]{1, 3}, list.convert());
    }


    @Test
    void testGet() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.get(1).intValue());
    }

    @Test
    void testIndexOf() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }


    @Test
    void testInsertionSort() throws Exception {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(SortMethod.INSERTION);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }

    @Test
    void testQuickSort() throws Exception {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(SortMethod.QUICK);
        assertEquals(3, list.size());
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }

    @Test
    void testConvert() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        assertArrayEquals(new int[]{1, 2, 3}, list.convert());
    }

    @Test
    void testClear() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0, list.size());
        assertArrayEquals(new int[]{}, list.convert());
    }

    @Test
    void testSize() throws Exception {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() throws Exception {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }
}
