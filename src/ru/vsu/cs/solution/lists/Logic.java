package ru.vsu.cs.solution.lists;


import static ru.vsu.cs.solution.lists.SortMethod.*;

public class Logic {

    public static int[] logic(int[] arr) throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        list.sort(QUICK);
        return list.convert();
    }

}
