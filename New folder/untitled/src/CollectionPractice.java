import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

public class CollectionPractice {
    public static void main(String[] args) {
        listInterface();
        practice();
    }

    static void listInterface() {

//        List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(2);
//        list.add(6);
//        list.add(1);
//        list.add(9);
//        Collections.reverse(list);
//        Collections.sort(list);
//        System.out.println(list);
//        System.out.println(list.subList(2, 5));

//        List<Integer> list1 =  Arrays.asList(1, 2, 3, 4, 5);
        // error
//        list1.remove(2);
//        System.out.println(list1);

//        List<Integer> one = list1.subList(1, 4);
//        one.set(0,99);
//        System.out.println(list1);
//        System.out.println(one);

//        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//        List<Integer> subList = list2.subList(1, 4);
//
//        subList.set(0, 99);
//        list2.add(100);
//        System.out.println(list2);
//        System.out.println(subList);

//        List<Integer> list = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9));
//        list.remove(1); // 5,7,8,9
//        list.remove(2); // 5,7,9
//        System.out.println(list);

//        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
//        for (String s : list) {
//            if (s.equals("B")) {
//                list.remove(s);
//            }
//        }
//
//        List<Integer> list = new LinkedList<>(List.of(2, 3, 5, 8, 10, 13));
//        ListIterator<Integer> it = list.listIterator();
//
//        while (it.hasNext()) {
//            Integer val = it.next();
//            if (val % 5 == 0 ) {
//                it.remove();
//                continue;
//            }
//            if (val%2==0) {
//                it.set(-val);
//                it.add(0);
//            }
//        }
//        System.out.println(list);

//        Queue<Integer> q = new LinkedList<>();
//        int k = 3;
//
//        q.add(10);
//        q.add(20);
//        q.add(30);
//        q.add(40);
//        q.add(50);
//
//        int temp=0;
//
//        Deque<Integer> stack = new ArrayDeque<>();
//        for (Integer nums : q ) {
//            if (temp != k) {
//                temp++;
//                stack.add(nums);
//            }
//        }
//
//
//        System.out.println(stack);
//        System.out.println(q);

//
//        Queue<String> queue = new LinkedList<>();
//        queue.add("Alice");
//        queue.add("Bob");
//        queue.add("Charlie");
//        queue.add("David");
//
//        while(!queue.isEmpty()) {
//            String person = queue.poll();
//            System.out.println("Serving : "+person);
//        }

//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        queue.add(5);
//        int k = 3;
//
//        Stack<Integer> stack = new Stack<>();
//
//        for (int i = 0; i < k; i++) {
//            stack.push(queue.remove());
//        }
//
//        while(!stack.isEmpty()) {
//            queue.add(stack.pop());
//        }
//
//        for (int i = 0; i < queue.size() - k ; i++) {
//            queue.add(queue.remove());
//        }
//
//        System.out.println(queue);


//        Deque<Integer> queue = new ArrayDeque<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        queue.add(5);
//        int k = 2;
//        k = k % queue.size();
//
//        for (int i = 0; i < k; i++) {
//            queue.addFirst(queue.removeLast());
//        }
//        System.out.println(queue);
    }

    static void practice() {
        // creating collection
        // 1) type safe and 2) un type safe

        ArrayList<String> str = new ArrayList<>();
        str.add("A");
        str.add("B");
        str.add("C");
        str.add("D");
        System.out.println(str);
//        str.add(0, "AA");
        str.set(0, "AA");
        System.out.println(str);

        System.out.println("---------------------------------------------------------------------");
        System.out.println();

        HashSet<Double> nums = new HashSet<>();
        nums.add(12.12);
        nums.add(88.121261);
        nums.add(23.56);
        nums.add(1.234);
        System.out.println(nums);

        TreeSet<Double> treeSet = new TreeSet<>();
        treeSet.addAll(nums);
        System.out.println("sorted : "+treeSet);

        System.out.println("---------------------------------------------------------------------");
        System.out.println();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("AB");
        arrayList.add("ABC");
        arrayList.add("ABCD");
        for(String st : arrayList) {
            System.out.print(st + "\t\t"+st.length());
            StringBuffer stringBuffer= new StringBuffer(st);
            System.out.println(stringBuffer.reverse());

        }
//        System.out.println(arrayList);

        System.out.println("---------------------------------------------------------------------");
        System.out.println();


//        Iterator<String> iterator = arrayList.iterator();
//        while(iterator.hasNext()) {
//            String st = iterator.next();
//            System.out.println(st);
//        }

//        ListIterator<String> stringListIterator= arrayList.listIterator(arrayList.size());
//        while (stringListIterator.hasPrevious()) {
//            String st = stringListIterator.previous();
//            System.out.println(st);
//        }



    }
}
