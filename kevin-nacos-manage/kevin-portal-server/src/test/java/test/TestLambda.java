package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestLambda {

    public static void main(String[] args) {
        String s1 = "2019-11-12 12:22:33";
        String s2 = "2019-11-16 15:22:33";
        String s3 = "2019-11-02 18:22:33";
        String s4 = "2019-09-02 18:22:33";
        String s5 = null;
        List<String> ss = new ArrayList<>();
        ss.add(s1);
        ss.add(s2);
        ss.add(s3);
        ss.add(s4);
        ss.add(s5);
        String min = ss.stream().filter(s -> s != null).min(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        }).get();

        System.out.println(min);
    }

}
