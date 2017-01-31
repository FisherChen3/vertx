package test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static test1.StreamTest.Person.Gen.MALE;

/**
 * Created by Fisher on 1/27/2017.
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        boolean b1 = stringCollection.stream().allMatch(t->t.charAt(t.length()-1)=='1');
//        System.out.println(b1);
        boolean b2 = stringCollection.stream().allMatch(t->t.length()>=3);
//        System.out.println(b2);

        boolean b3 = stringCollection.stream().anyMatch(t->t.charAt(0)=='c');
//        System.out.println(b3);

        List<String> list = stringCollection.stream().collect(ArrayList<String>::new, ArrayList::add,ArrayList::addAll);
//        System.out.println(list.size());

        List<String> list1 = stringCollection.stream().collect(Collectors.toList());

        List<String> secondStream = new ArrayList<>();
        secondStream.add("abc");
        secondStream.add("aaa2");

        Stream<String> stream = Stream.concat(stringCollection.stream(),secondStream.stream());
//        List<String> list2 = stream.collect(Collectors.toList());
//        System.out.println(list2.size());

        // calling count also means "used"
//        System.out.println(stream.count());  // stream has been closed, cannot be reused

        Stream stream2 = stream.distinct();
//        System.out.println(stream2.count());

//        System.out.println(Stream.empty().count());

        Stream stream3 = stringCollection.stream().filter(t->t.length()>3);
//        System.out.println(stream3.count());

        Optional<String> o = stringCollection.stream().findAny();

        String list3 = o.get();
//        System.out.println(list3);

//        stringCollection.stream().map(t->t.concat("z")).forEach(System.out::println);

        String[] strArr1 = {"abc","dec","qwe"};
        String[] strArr2 = {"123","455","1234"};
        List<String[]> arrList = new ArrayList<>();
        arrList.add(strArr1);
        arrList.add(strArr2);

        // cannot use String::concat since concat should be used with BiFunction instead of Function
        arrList.stream().flatMap(t-> Arrays.stream(t)).map(String::toUpperCase).forEach(System.out::println);

        Stream.generate(new Random()::nextDouble).limit(10).forEach(System.out::println);

        Stream.iterate(1l,t->t+1).limit(10).forEach(System.out::println);

        boolean b = stringCollection.stream().noneMatch(t->t.length()>4);

        System.out.println(b);

        // of(T... values) means multiple arguments/parameters
        Stream.of(1,2,3,4,5).forEach(System.out::println);

        //This method exists mainly to support debugging,
        // where you want to see the elements as they flow past a certain point in a pipeline:
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Optional<String> o1 = stringCollection.stream().filter(t->t.length()>=4).reduce(String::concat);
        System.out.println(o1.get());

        String s = stringCollection.stream().filter(t->t.length()>=4).reduce("",String::concat);
        System.out.println(s);

        stringCollection.stream().skip(5).forEach(System.out::println);

        stringCollection.stream().sorted().forEach(System.out::println);

        stringCollection.stream().sorted((t1,t2)->t2.compareTo(t1)).forEach(System.out::println);


        Person p1 = new Person("123");
        List<Person> people = new ArrayList<>();
        people.add(p1);

        Person[] men = people.stream()
                .filter(p -> p.getGender() == MALE)
                .toArray(Person[]::new);
    }

    public static class Person {
        enum Gen{
            MALE,FEMALE;
        }
        String name;
        Gen gender;
        public Person(String name) {
            this.name = name;
        }
        public Gen getGender(){
            return this.gender;
        }
    }
}
