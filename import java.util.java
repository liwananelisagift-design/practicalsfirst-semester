import java.util.*;
import java.text.DecimalFormat;

public class timeMethods {

    public static int N = 100000;   // Size of array

    public static void main(String args[]) {

        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double time;

        double runTimeLinear = 0;
        double runTimeLinear2 = 0;

        double runTimeBinary = 0;
        double runTimeBinary2 = 0;

        int n = N;
        int repetitions = 30;

        // Generate random array
        int[] arr = generateArray(n);
        int key = arr[n - 1];  // Worst case for linear search

        // Sorted copy for binary search
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        for (int repetition = 0; repetition < repetitions; repetition++) {

            // ----- Time Linear Search -----
            start = System.nanoTime();
            linearSearch(arr, key);
            finish = System.nanoTime();

            time = (finish - start) / 1_000_000.0; // ms
            runTimeLinear += time;
            runTimeLinear2 += time * time;

            // ----- Time Binary Search -----
            start = System.nanoTime();
            binarySearch(sortedArr, key);
            finish = System.nanoTime();

            time = (finish - start) / 1_000_000.0; // ms
            runTimeBinary += time;
            runTimeBinary2 += time * time;
        }

        // Averages
        double avgLinear = runTimeLinear / repetitions;
        double avgBinary = runTimeBinary / repetitions;

        // Standard deviations
        double stdLinear = Math.sqrt(
                (runTimeLinear2 - repetitions * avgLinear * avgLinear)
                        / (repetitions - 1));

        double stdBinary = Math.sqrt(
                (runTimeBinary2 - repetitions * avgBinary * avgBinary)
                        / (repetitions - 1));

        System.out.println("\nStatistics");
        System.out.println("________________________________________");

        System.out.println("Linear Search:");
        System.out.println("Average time = " + fiveD.format(avgLinear)
                + " ms ± " + fourD.format(stdLinear));

        System.out.println("\nBinary Search:");
        System.out.println("Average time = " + fiveD.format(avgBinary)
                + " ms ± " + fourD.format(stdBinary));

        System.out.println("\nn = " + n);
        System.out.println("Repetitions = " + repetitions);
        System.out.println("________________________________________");
    }

    // -------- Linear Search --------
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    // -------- Binary Search --------
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    // -------- Generate Random Array --------
    public static int[] generateArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n * 10);
        }

        return arr;
    
    import java.util.*;

public class HashDataSetup {

    public static final int N = 1 << 20;      // 2^20 = 1,048,576
    public static final int USED = 950_000;  // First 950,000 used

    static class KeyValuePair {
        int key;
        String value;

        KeyValuePair(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        // Step 1: Generate keys 0 to N-1
        Integer[] keys = new Integer[N];
        for (int i = 0; i < N; i++) {
            keys[i] = i;
        }

        // Step 2: Shuffle keys
        List<Integer> keyList = Arrays.asList(keys);
        Collections.shuffle(keyList);

        // Step 3: Assign String values "1" to "N"
        KeyValuePair[] data = new KeyValuePair[N];

        for (int i = 0; i < N; i++) {
            String value = String.valueOf(i + 1); // numbering 1..N
            data[i] = new KeyValuePair(keys[i], value);
        }

        // Step 4: Use only first 950,000 pairs
        KeyValuePair[] experimentData = Arrays.copyOfRange(data, 0, USED);

        System.out.println("Total key-value pairs created: " + N);
        System.out.println("Pairs used for experiment: " + experimentData.length);
        System.out.println("Pairs left unused: " + (N - USED));
   import java.util.LinkedList;

public class openHash {

    // Inner class for key-value pair
    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] table;
    private int m;   // table size

    // Constructor
    public openHash(int m) {
        this.m = m;
        table = new LinkedList[m];

        for (int i = 0; i < m; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // --------------------------------------------------
    // (a) Scatter / Hash function
    // --------------------------------------------------
    public int hash(String key) {

        int h = 0;
        int base = 31;   // good polynomial base

        for (int i = 0; i < key.length(); i++) {
            h = base * h + key.charAt(i);
        }

        h = h & 0x7fffffff;  // make positive

        return h % m;   // index in [0 .. m-1]
    }

    // --------------------------------------------------
    // (b) Insert method
    // --------------------------------------------------
    public void insert(String key, String value) {

        int index = hash(key);

        // Check if key already exists → update
        for (Entry e : table[index]) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }

        // Otherwise add new entry
        table[index].add(new Entry(key, value));
    }

    // --------------------------------------------------
    // (c) Lookup method
    // --------------------------------------------------
    public String lookup(String key) {

        int index = hash(key);

        for (Entry e : table[index]) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }

        return null;  // not found
    
    
    
    
    
    }
    
}
}
   
   
    }
}

    
    }
}