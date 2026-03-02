public class OpenHash {

    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
 private Entry[] table;
    private int m;  // table size
    private int n;  // number of stored elements

    public OpenHash(int m) {
        this.m = m;
        this.table = new Entry[m];
        this.n = 0;
    }
   
    // (a) Scatter / hash function
    public int hash(String key) {
        long hash = 0;
        int prime = 31;

        for (int i = 0; i < key.length(); i++) {
            hash = (hash * prime + key.charAt(i)) % m;
        }

        return (int) hash;
    }

    // (b) Insert method
    public void insert(String key, String value) {
        if (n >= m) {
            throw new RuntimeException("Hash table overflow");
        }

        int index = hash(key);

        // Linear probing
        while (table[index] != null) {
            // If key already exists → update value
            if (table[index].key.equals(key)) {
                table[index].value = value;
                return;
            }
            index = (index + 1) % m;
            if (index == startIndex) break; // Stop if looped back
        }
            return null; // not found
    }
      // Optional: for debugging
    public void printTable() {
        for (int i = 0; i < m; i++) {
            if (table[i] != null) {
                System.out.println(i + ": (" + table[i].key + ", " + table[i].value + ")");
            } else {
                System.out.println(i + ": null");
            }
        }
    }

    // Simple test
    public static void main(String[] args) {
        OpenHash hashTable = new OpenHash(10);

        hashTable.insert("apple", "red");
        hashTable.insert("banana", "yellow");
        hashTable.insert("grape", "purple");
        hashTable.insert("apple", "green"); // update

        System.out.println("Lookup apple: " + hashTable.lookup("apple"));
        System.out.println("Lookup banana: " + hashTable.lookup("banana"));
        System.out.println("Lookup orange: " + hashTable.lookup("orange")); // null

        System.out.println("\nFull table:");
        hashTable.printTable();
    }
}
