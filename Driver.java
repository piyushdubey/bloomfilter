import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        String apple = "apple";
        String banana = "banana";
        String caterpillar = "caterpillar";
        String empty = new String();

        BloomFilter bf = new BloomFilter();

        System.out.println("Contains " + apple + " before adding: " + bf.contains(apple));
        bf.add(apple);
        System.out.println("Contains " + apple + " after adding: " + bf.contains(apple));

        System.out.println("Contains " + banana + " before adding: " + bf.contains(banana));
        bf.add(banana);
        System.out.println("Contains " + banana + " after adding: " + bf.contains(banana));

        System.out.println("Contains " + caterpillar + " before adding: " + bf.contains(caterpillar));
        bf.add(caterpillar);
        System.out.println("Contains " + caterpillar + " after adding: " + bf.contains(caterpillar));


        // Test against a set of 500 random words
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            StringBuilder maybePresent = new StringBuilder();

            String word;
            while ((word = br.readLine()) != null) {
                if (bf.contains(word)) {
                    maybePresent.append(word).append("\n");
                }
            }
            System.out.println("Following words maybe present : " + maybePresent.toString());
        } catch(IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
    }
}
