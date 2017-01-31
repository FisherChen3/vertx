package test1;

import java.util.Optional;

/**
 * Created by Fisher on 1/27/2017.
 */
public class Test1 {

    public static void main(String[] args) {

        Optional<String> o1 = Optional.of("123");
        Optional<Integer> o2 = o1.map(Integer::valueOf);
//        System.out.println(o2.get()==123);

        Optional<String> o3 = Optional.ofNullable(null);

//        System.out.println(o3.isPresent());

        Optional<String> o4 = Optional.ofNullable("123");

//        System.out.println(o4.isPresent());

//        System.out.println(o4.orElse("456"));
//        System.out.println(o3.orElse("789"));

        String s = o3.orElseGet(String::new);
//        System.out.println(s);
//        System.out.println(s+"123");

        String ss = o3.orElseGet(()->"987");  // lambda expression without args
        System.out.println(ss);



    }
}
