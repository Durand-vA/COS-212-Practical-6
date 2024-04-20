public class Main {
    public static int Suites_Run = 0;
    public static int Suites_Passed = 0;

    public static int Tests_Run = 0;
    public static int Tests_Passed = 0;

    static StringBuilder suites = new StringBuilder();

    public static void end_tests() {
        if (Suites_Passed == Suites_Run) {
            System.out.println("All Suites were passed");
        } else {
            System.out.println("Some Suites Failed : " + Suites_Passed + "/" + Suites_Run + '\n' + suites.toString());
        }
    }

    public static void start_suite(String name) {
        Suites_Run++;
        System.out.print(name + "\n==========================================\n");
        suites.append(name).append(": ");
    }

    public static void end_suite() {
        if (Tests_Run == Tests_Passed) {
            Suites_Passed++;
            System.out.print("All tests have passed\n");
            suites.append("Passed\n");
        } else {
            System.out.print("Some Tests Failed : " + Tests_Passed + "/" + Tests_Run + "\n\n");
            suites.append("Failed\n");
        }
        System.out.println("==========================================\n");
        Tests_Run = 0;
        Tests_Passed = 0;
    }

    public static boolean assertEquals(String test, String got, String expected) {
        Tests_Run++;
        if (got.equals(expected)) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static boolean assertEquals(String test, boolean got, boolean expected) {
        Tests_Run++;
        if (got == expected) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static boolean assertEquals(String test, int got, int expected) {
        Tests_Run++;
        if (got == expected) {
            Tests_Passed++;
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Passed\n");
            return true;
        } else {
            System.out.print("Test " + Tests_Run + ": \"" + test + "\" Failed\n" + "You got : \n" + got + "\nYou expected : \n" + expected + "\n");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        start_suite("Constructors");

        MaxHeap<Integer> maxHeap1 = new MaxHeap<>();
        MinHeap<Integer> minHeap2 = new MinHeap<>();
        MaxHeap<Integer> maxHeap2 = new MaxHeap<>(new Integer[]{5, 1, 2, 3, 1});
        MinHeap<Integer> minHeap1 = new MinHeap<>(new Integer[]{5, 1, 2, 3, 1});

        assertEquals("Empty constructor", maxHeap1.toString(), "");
        assertEquals("{5,1,2,3,1} Max Heap", maxHeap2.toString(), "|   ┌── 2\n" +
                "└── 5\n" +
                "    |   ┌── 1\n" +
                "    └── 3\n" +
                "        └── 1\n");
        assertEquals("{5,1,2,3,1} Min Heap", minHeap1.toString(), "|   ┌── 2\n" +
                "└── 1\n" +
                "    |   ┌── 5\n" +
                "    └── 1\n" +
                "        └── 3\n");

        MaxHeap<Integer> maxHeap3 = new MaxHeap<>(new Integer[]{4,6,3,7,5,4});
        assertEquals("{4,6,3,7,5,4} Max heap", maxHeap3.toString(), "|   ┌── 4\n" +
                "|   │   └── 3\n" +
                "└── 7\n" +
                "    |   ┌── 5\n" +
                "    └── 6\n" +
                "        └── 4\n");
        MinHeap<Integer> minHeap3 = new MinHeap<>(new Integer[]{4,6,3,7,5,4});
        assertEquals("{4,6,3,7,5,4} Min heap", minHeap3.toString(), "|   ┌── 4\n" +
                "|   │   └── 4\n" +
                "└── 3\n" +
                "    |   ┌── 6\n" +
                "    └── 5\n" +
                "        └── 7\n");

        end_suite();

        //===========================================================================

        start_suite("Push");

        minHeap1.push(13);
        minHeap1.push(54);
        minHeap1.push(675);
        minHeap1.push(2);
        minHeap1.push(35);
        minHeap1.push(6);
        minHeap1.push(8);
        minHeap1.push(46);
        minHeap1.push(33);
        minHeap1.push(5);
        minHeap1.push(7);
        minHeap1.push(8);
        minHeap1.push(554);
        minHeap1.push(32);

        assertEquals("Min heap push", minHeap1.toString(), "|           ┌── 33\n" +
                "|       ┌── 5\n" +
                "|       │   └── 54\n" +
                "|   ┌── 2\n" +
                "|   │   |   ┌── 46\n" +
                "|   │   └── 8\n" +
                "|   │       └── 13\n" +
                "└── 1\n" +
                "    |       ┌── 6\n" +
                "    |   ┌── 5\n" +
                "    |   │   └── 35\n" +
                "    └── 1\n" +
                "        |       ┌── 32\n" +
                "        |   ┌── 3\n" +
                "        |   │   └── 554\n" +
                "        └── 2\n" +
                "            |   ┌── 8\n" +
                "            └── 7\n" +
                "                └── 675\n");

        maxHeap2.push(13);
        maxHeap2.push(54);
        maxHeap2.push(675);
        maxHeap2.push(2);
        maxHeap2.push(35);
        maxHeap2.push(6);
        maxHeap2.push(8);
        maxHeap2.push(46);
        maxHeap2.push(33);
        maxHeap2.push(5);
        maxHeap2.push(7);
        maxHeap2.push(8);
        maxHeap2.push(554);
        maxHeap2.push(32);

        assertEquals("Max heap push", maxHeap2.toString(), "|           ┌── 5\n" +
                "|       ┌── 33\n" +
                "|       │   └── 5\n" +
                "|   ┌── 46\n" +
                "|   │   |   ┌── 8\n" +
                "|   │   └── 13\n" +
                "|   │       └── 2\n" +
                "└── 675\n" +
                "    |       ┌── 6\n" +
                "    |   ┌── 35\n" +
                "    |   │   └── 1\n" +
                "    └── 554\n" +
                "        |       ┌── 8\n" +
                "        |   ┌── 32\n" +
                "        |   │   └── 2\n" +
                "        └── 54\n" +
                "            |   ┌── 3\n" +
                "            └── 7\n" +
                "                └── 1\n");

        end_suite();

        start_suite("Pop");

        maxHeap2.pop();
        maxHeap2.pop();
        maxHeap2.pop();
        maxHeap2.pop();
        maxHeap2.pop();

        assertEquals("Max heap pop 5 times", maxHeap2.toString(), "|       ┌── 5\n" +
                "|       │   └── 3\n" +
                "|   ┌── 13\n" +
                "|   │   |   ┌── 5\n" +
                "|   │   └── 8\n" +
                "|   │       └── 2\n" +
                "└── 33\n" +
                "    |       ┌── 2\n" +
                "    |   ┌── 6\n" +
                "    |   │   └── 1\n" +
                "    └── 32\n" +
                "        |   ┌── 1\n" +
                "        └── 8\n" +
                "            └── 7\n");

        minHeap1.pop();
        minHeap1.pop();
        minHeap1.pop();
        minHeap1.pop();
        minHeap1.pop();

        assertEquals("Min heap pop 5 times", minHeap1.toString(), "|       ┌── 33\n" +
                "|       │   └── 54\n" +
                "|   ┌── 5\n" +
                "|   │   |   ┌── 46\n" +
                "|   │   └── 8\n" +
                "|   │       └── 13\n" +
                "└── 5\n" +
                "    |       ┌── 675\n" +
                "    |   ┌── 35\n" +
                "    |   │   └── 554\n" +
                "    └── 6\n" +
                "        |   ┌── 32\n" +
                "        └── 7\n" +
                "            └── 8\n");

        maxHeap2.peek();


        end_suite();

        end_tests();

    }
}
