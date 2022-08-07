package test_search;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestSet {
    
    @Test
    public void testHashSet() {
        Set<String> set = new HashSet<>() {{
            add("cat");
            add("dog");
            add("animal");
            add("man");
            add("person");
            add("person1");
            add("person2");
            add("person3");
        }};
        for (String s : set) {
            System.out.println(s);
        }
    }

}
