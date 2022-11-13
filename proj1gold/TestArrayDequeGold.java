import  static org.junit.Assert.*;
import  org.junit.Test;
import  java.util.ArrayList;
import  java.util.List;

public class TestArrayDequeGold {

    @Test
    public void  test1() {
        StudentArrayDeque<Integer> studentDeque= new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> corDeque= new ArrayDequeSolution<Integer>();
        for (int i = 0;i<=50;i++) {
            int  element = StdRandom.uniform(100);
            if (element < 25) {
                studentDeque.addLast(element);
                corDeque.addLast(element);
            } else {
                studentDeque.addFirst(element);
                corDeque.addFirst(element);
            }
        }
        // something wrong with studentDeque removeLast method
        for (int i = 0 ; i< corDeque.size() ; i++) {
            assertEquals(corDeque.removeLast(),studentDeque.removeLast());
        }
    }
        }

