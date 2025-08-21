import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {
    public static void main(String[] args) {
//        practice();
//        collectorsPrac();
        collectorsExamples();
    }
    static void collectorsExamples() {
        // 1. Convert a list of integers to a set using streams
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 1);
        System.out.println(numbers.stream().collect(Collectors.toSet()));

        // 2. Given a list of names, join them into a single comma-separated string
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println(names.stream().collect(Collectors.joining(",")));

        // 3. Group a list of strings by their first character
        List<String> words = Arrays.asList("apple", "banana", "avocado", "blueberry");
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.charAt(0))));

        // 4. Count the frequency of each element in a list.
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        System.out.println(items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        // 5. Partition a list of integers into even and odd numbers.
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(numbers1.stream().collect(Collectors.partitioningBy(integer -> integer % 2 == 0)));


        // 6. Write a custom collector to compute the average length of strings in a list.
        List<String> words1 = Arrays.asList("hello", "world", "java", "streams");
        System.out.println(words1.stream().collect(Collectors.averagingDouble(String::length)));

        // 7. Group a list of employees by department, then by gender
        List<Employee> employees  =  Arrays.asList(
                new Employee("Alice", "HR", "Female"),
                new Employee("Bob", "IT", "Male"),
                new Employee("Charlie", "IT", "Male"),
                new Employee("Diana", "HR", "Female"),
                new Employee("Eve", "Finance", "Female"),
                new Employee("Frank", "Finance", "Male")
        );

        Map<String, List<Employee>> collect1 = employees.stream().collect(Collectors.groupingBy(x -> x.department));
        Map<String, Map<String, List<Employee>>> collect = employees.stream().collect(Collectors.groupingBy(x -> x.department, Collectors.groupingBy(x -> x.gender)));

        System.out.println(collect1);
        System.out.println(collect);

        // 8. Given a list of students with subject and marks, find the top scorer per subject.
        List<Student> students = Arrays.asList(
                new Student("Alice", "Math", 85),
                new Student("Bob", "Math", 92),
                new Student("Charlie", "Physics", 78),
                new Student("Diana", "Physics", 88),
                new Student("Eve", "Chemistry", 91),
                new Student("Frank", "Chemistry", 89)
        );

        Map<String, Student> collect2 = students.stream().collect(Collectors.groupingBy(x -> x.subject, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(x -> x.marks)), Optional::get)));
        System.out.println(collect2);

        System.out.println(students.stream().collect(Collectors.groupingBy(x -> x.subject, Collectors.averagingInt(x -> x.marks))));



    }










    static void collectorsPrac() {
        // collectors are utility class
        // provides a set of methods to create common  collectors

        // 1. collecting to list
        List<String> stringList = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println(stringList.stream().filter(x -> x.startsWith("A")).toList());

        // 2. collecting to sets
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7, 7, 7);
        System.out.println(numbers.stream().collect(Collectors.toSet()));

        // 3. Collecting specified collection
        ArrayDeque<String> collect = stringList.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));
        System.out.println(collect);

        // 4. joining strings : concat stream element
        String collect1 = stringList.stream().map(String::toUpperCase).collect(Collectors.joining("."));
        System.out.println(collect1);

        // 5. summarizing data
        // Generates statistical summary (count, sum, min, max , avg)
        List<Integer> integerList = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9);
        IntSummaryStatistics collect2 = integerList.stream().collect(Collectors.summarizingInt(x -> x));

        System.out.println("Count : "+collect2.getCount());
        System.out.println("Sum : "+collect2.getSum());
        System.out.println("Min : "+collect2.getMin());
        System.out.println("Max : "+collect2.getMax());
        System.out.println("Avg : "+collect2.getAverage());

        // calculate avg
        Double collect3 = integerList.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println("Avg : "+collect3);


        // 6. grouping elements
        List<String> words = Arrays.asList("hello", "world", "java", "stream", "collecting");
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.length())));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", "))));
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));
        HashMap<Integer, Long> treeMap = words.stream().collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.counting()));
        System.out.println(treeMap);

        // 7. Partitioning elements : partitions element into two group (true and false) based on partition

        System.out.println(words.stream().collect(Collectors.partitioningBy(x-> x.length() > 5)));

        // 8. Mapping and collecting
        System.out.println(words.stream().collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.groupingBy(x-> x.length()))));


    }

    static void practice() {
//        List<Integer> integers = List.of(1,2,6,7,4,2,6,8,0);
//        System.out.println(integers);
//
//        List<Integer> even = integers.stream().filter(e -> e %2==0).collect(Collectors.toList());
//        System.out.println(even);
//
//        List<String> names = Arrays.asList("alice", "Abob", "charlie");
//        List<String> result = names.stream().filter(e-> e.toLowerCase().startsWith("a")).collect(Collectors.toList());
//        System.out.println(result);

//        Predicate<Integer> integerPredicate = e -> e % 2 == 0;
//        System.out.println(integerPredicate.test(3));
//
//        Predicate<String> startWithA = e -> e.toUpperCase().startsWith("A");
//        Predicate<String> endWithA = e -> e.toUpperCase().endsWith("T");
//
//        Predicate<String> end = startWithA.and(endWithA);
//        Predicate<String> or = startWithA.or(endWithA);
//
//        System.out.println("start and end with : "+end.test("Applyt"));
//        System.out.println("start or end with : "+or.test("pply"));
//
//
//        Function<Integer, Integer> function =  e -> e * e;
//        System.out.println(function.apply(3));
//
//
//        Consumer<Integer> integerConsumer = (x) -> System.out.println(x);
//        integerConsumer.accept(10);
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 6, 7, 90, 3, 12, 65);
//        Consumer<List<Integer>> listConsumer = (e) ->
//        {
//            for(int i : e) {
//                System.out.println(i);
//            }
//        };
//        listConsumer.accept(list);
//
//        Supplier<String> stringSupplier= () -> "Hello";
//        System.out.println(stringSupplier.get());
//
//        List<String> list1 = Arrays.asList("abc", "def", "xyz");
//        list1.forEach(System.out::println);
//
//        list1.forEach(MobilePhone::new);

        // collect

//        List<Integer> list2 = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
//
//        // collect
//        List<Integer> collect = list2.stream().skip(1).collect(Collectors.toList());
//        System.out.println(collect);
//
//        //forEach
//        list2.stream().forEach(x -> System.out.println(x));
//
//        //reduce : combine element to produce a single element
//        Optional<Integer> reduce = list2.stream().reduce(Integer::sum);
//        System.out.println("reduce : " + reduce.get());
//
//        //count
//        //anyMatch, allMatch, noneMatch
//        boolean b = list2.stream().anyMatch(x -> x % 2 == 0);
//        System.out.println(b);
//        boolean b1 = list2.stream().allMatch(x -> x > 0);
//        System.out.println(b1);
//        boolean b2 = list2.stream().noneMatch(x -> x <= 0);
//        System.out.println(b2);
//
//        //findFirst, finAny
//        System.out.println(list2.stream().findFirst().get());
//        System.out.println(list2.stream().findAny().get());

        // Example

        List<String> stringList = Arrays.asList("Anna", "Bob", "Charlie", "David");
        System.out.println(stringList.stream().filter(x -> x.length() > 3).toList());

        // squaring and then sorting

        List<Integer> integerList = Arrays.asList(6, 1, 9, 3, 2, 4, 5, 7);
        System.out.println(integerList.stream().map(x -> x * x).sorted().toList());

        // summing values
        List<Integer> integers = Arrays.asList(6, 1, 9, 3, 2, 4, 5, 7);
        System.out.println(integers.stream().reduce(Integer::sum).get());

        // find occurrence of char
        String s = "Hello World";
        System.out.println(s.chars().filter(e -> e == 'H').count());

        // stateful and stateless


        //parallel stream : A type of stream that enables parallel processing of elements
        // Allowing multiple threads to process part of stream simultaneously
        // this is significant improve performance of large data sets
        // Workload is distributed across multiple threads

        long start = System.currentTimeMillis();
        List<Integer> largeList = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        List<Long> factorialList = largeList.stream().map(StreamAPI::factorial).toList();
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        factorialList = largeList.parallelStream().map(StreamAPI::factorial).toList();
        end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start) + " ms");

        //Cumulative sum
        // [1,2,3,4,5] --> [1, 3, 6, 10, 15]

        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5);
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> list = integers1.stream().map(sum::addAndGet).toList();
//        List<Integer> list = integers1.parallelStream().map(sum::addAndGet).sequential().toList();
        System.out.println(list);


        // flatMap : handle the stream of collection, lists or arrays where each element is itself a collection
        List<List<String>> listOfList = Arrays.asList(
                Arrays.asList("Banana", "Kiwi"),
                Arrays.asList("apple", "orange"),
                Arrays.asList("pear", "grape"));

        System.out.println(listOfList.stream().flatMap(Collection::stream).map(String::toUpperCase).toList());

        List<String> stringList1 = Arrays.asList("Hello World", "Java Stream are powerful", "flatmap is useful");
        System.out.println(stringList1.stream().flatMap(sentence -> Arrays.stream(sentence.split(" ")).map(String::toUpperCase)).toList());


        //stream is not reuse after terminal operation has been called



    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

class MobilePhone {
    String name;

    public MobilePhone(String name) {
        this.name = name;
    }
}

class Employee {
    String name;
    String department;
    String gender;
    public Employee(String name, String department, String gender) {
        this.name = name;
        this.department = department;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class Student {
    String name;
    String subject;
    int marks;

    public Student(String name, String subject, int marks) {
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", marks=" + marks +
                '}';
    }
}
