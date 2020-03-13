import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.*;

public class MaxHeapTest {

    // Manually sets the heap's array and size to the given array
    // This is so I can test heaps against my own input
    private void setHeapToArray(MaxHeap heap, Integer[] array) {
        heap.data = Arrays.copyOf(array, array.length);
        heap.size = heap.data.length;
    }

    @Test
    public void isValid() {
        Integer[][] testCases = {
                {},
                {2},
                {5, 4, 3, 2, 1, 2, 1, 1},
                {100, 19, 36, 17, 3, 25, 1, 2, 7},
                {19, 100, 36, 17, 3, 25, 1, 2, 7},
                {5, 6, 7, 1, 2, 3},
                {100, 36, 19, 17, 25, 3, 1, 2, 7}
        };

        boolean[] expectedVales = {true, true, true, true, false, false, true};

        for (int i = 0; i < testCases.length; i++) {
            MaxHeap heap = new MaxHeap(20);
            setHeapToArray(heap, testCases[i]);
            assertEquals(heap.isValid(), expectedVales[i]);
        }
    }

    @Test
    public void equals() {
        Integer[][] casesForHeap1 = {
                {},
                {2},
                {3, 2},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 3, 2, 2, 1},
                {100, 19, 36, 17, 3, 25, 1, 2, 7},
                {},
                {3, 2, 1}
        };

        Integer[][] casesForHeap2 = {
                {},
                {2},
                {3, 2},
                {5, 3, 4, 2, 1},
                {5, 3, 4, 2, 2, 3, 2, 1},
                {100, 36, 19, 17, 25, 3, 1, 2, 7},
                {2},
                {3, 2, 2, 1}
        };

        boolean[] expectedValues = {true, true, true, true, true, true, false, false};

        for (int i = 0; i < casesForHeap1.length; i++) {
            MaxHeap heap1 = new MaxHeap(20);
            MaxHeap heap2 = new MaxHeap(20);
            setHeapToArray(heap1, casesForHeap1[i]);
            setHeapToArray(heap2, casesForHeap2[i]);
            assertEquals(heap1.equals(heap2), expectedValues[i]);
        }
    }

    @Test
    public void add() {
        Integer[][] testCases = {
                {},
                {1},
                {3, 2, 1},
                {5, 3, 2, 2, 1, 1, 1},
                {100, 19, 36, 17, 3, 25, 1, 2, 7}
        };

        for (Integer[] caseArr : testCases) {
            MaxHeap expectedHeap = new MaxHeap(10);
            MaxHeap testHeap = new MaxHeap(10);

            setHeapToArray(expectedHeap, caseArr);
            // Shuffle to make sure add works correctly
            // with different insertion orders
            shuffleArray(caseArr);
            for (Integer i : caseArr)
                assertTrue(testHeap.add(i));

            assertEquals(expectedHeap, testHeap);
        }
    }

    // Pulled from StackOverFlow
    // https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private void shuffleArray(Integer[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Test
    public void popAndGet(){
        Integer[][] testCases = {
                {},
                {1},
                {3, 2, 1},
                {5, 3, 2, 2, 1, 1, 1},
                {100, 36, 25, 19, 17, 7, 3, 2, 1},
                {27, 24, 20, 19, 15, 12, 10, 8, 4, 3}
        };

        for (Integer[] caseArr : testCases) {
            MaxHeap testHeap = new MaxHeap(10);
            Integer[] expectedArray = Arrays.copyOf(caseArr, caseArr.length);

            shuffleArray(caseArr);
            testHeap.MaxHeapNLogN(caseArr);

            for (Integer i: expectedArray) {
                assertEquals(i, testHeap.get());
                assertEquals(i, testHeap.pop());
            }
        }
    }

    @Test
    public void maxHeapN(){
        Integer[][] testCases = {
                {},
                {1},
                {3, 2, 1},
                {5, 3, 2, 2, 1, 1, 1},
                {100, 19, 36, 17, 3, 25, 1, 2, 7},
                {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17}
        };

        for (Integer[] testCase: testCases) {
            MaxHeap heap = new MaxHeap(10);
            shuffleArray(testCase);
            heap.MaxHeapN(testCase);
            assertTrue(heap.isValid());
        }
    }
}