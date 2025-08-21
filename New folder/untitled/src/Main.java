import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
//
//        List<Integer> evens = numbers.stream()
//                .filter(n -> n % 2 == 0)
//                .toList();
//
//        System.out.println(evens);  // [2, 4, 6]

//        List<String> names = Arrays.asList("alice", "bob", "charlie");
//        List<String> result = names.stream().map(String::toUpperCase).toList();
//        System.out.println(result);

        //Count how many names start with 'A'
//        List<String> names = Arrays.asList("Alice", "Bob", "Ankit", "David");
//        long result = names.stream().filter(i -> i.startsWith("A")).count();
//        System.out.println(result);

        //Get Unique Squares of Numbers [4, 9, 16]
//        List<Integer> nums = Arrays.asList(2, 3, 2, 4, 3);
//        List<Integer> result = nums.stream().map(i -> i *i).distinct().toList();
//        System.out.println(result);

//        List<String> cities = Arrays.asList("London", "Paris", "Berlin", "Amsterdam");
//        List<String> sorted = cities.stream().sorted().toList();
//        System.out.println(sorted);

//        List<Integer> nums = Arrays.asList(11, 2, 3, 4, 5);
//        int sum = nums.stream().reduce(0, Integer::sum);
//        System.out.println(sum);

//        List<String> cities = Arrays.asList("London", "Paris", "Berlin", "Amsterdam");
//        int sumOfTotalAlf = nums.stream().reduce(Integer::max).get();
//        System.out.println(sumOfTotalAlf);


        // Output:
        // 25: Bob, David
        // 30: Alice, Charlie

        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 30),
                new Person("David", 25)
        );

        List<Integer> ages = people.stream().map(i -> i.age).distinct().sorted().toList();
        System.out.println(ages); // [25, 30]

        Map<Integer, List<Person>> name = people.stream().collect(Collectors.groupingBy(i -> i.age));
        name.forEach((age, list) -> System.out.println(age + " : " + list.stream().map(i -> i.name).toList()));
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
