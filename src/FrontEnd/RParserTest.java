package src.FrontEnd;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RParserTest {
    public static RParser parser = new RParser();
    @Test
    public void test1(){
        Queue<Character> result = parser.parse("a|b",false);
        String str = "ab|";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test
    public void test2(){
        Queue<Character> result = parser.parse("ab",false);
        String str = "ab.";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test
    public void test3(){
        Queue<Character> result = parser.parse("(ab)*cd",false);
        String str = "ab.*c.d.";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test
    public void test4(){
        Queue<Character> result = parser.parse("ab|cd",false);
        String str = "ab.cd.|";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test
    public void test5(){
        Queue<Character> result = parser.parse("ab| +cd",false);
        String str = "ab. +c.d.|";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test
    public void test6(){
        Queue<Character> result = parser.parse("(f|s)ly",false);
        String str = "fs|l.y.";
        LinkedList<Character> compare = new LinkedList<>(str.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        assertEquals(compare, result);
    }

    @Test(expected = RuntimeException.class)
    public void test7(){
        Queue<Character> result = parser.parse("(f|s))ly",false);
    }

    @Test(expected = RuntimeException.class)
    public void test8(){
        Queue<Character> result = parser.parse(")(f|s)ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test9(){
        Queue<Character> result = parser.parse("*(f|s)ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test10(){
        Queue<Character> result = parser.parse("+(f|s)ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test11(){
        Queue<Character> result = parser.parse("(f|*s)ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test12(){
        Queue<Character> result = parser.parse("((f|*s)ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test13(){
        Queue<Character> result = parser.parse("(f|*s)(ly",false);
    }
    @Test(expected = RuntimeException.class)
    public void test14(){
        Queue<Character> result = parser.parse("(f|*s)ly|",false);
    }
    @Test(expected = RuntimeException.class)
    public void test15(){
        Queue<Character> result = parser.parse("|",false);
    }
    @Test(expected = RuntimeException.class)
    public void test16(){
        Queue<Character> result = parser.parse("a||b",false);
    }
}
