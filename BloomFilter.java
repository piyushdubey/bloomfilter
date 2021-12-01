import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;

public class BloomFilter {
    // To limit the scope of the problem, keeping the max filter size to 1MB
    private BitSet filter; 
    private static final int DEFAULT_FILTER_SIZE = 8 * 1024 * 1024; // 1MB
    
    // TODO: [In order to improve the accuracy of the algorithm] Dynamically generate the number of hashes based on the size of the filter and expected number of inputs
    // i.e. sth like number of hash functions = filter/number_of_expected inputs considering filter size is 
    // much larger than the number of expected items to be processed
    private final int NUM_HASH_FUNCTIONS = 3; 

    public BloomFilter() {
        this.filter = new BitSet(DEFAULT_FILTER_SIZE);
    }
    
    public BloomFilter(int filterSize) {
        //TODO: Take filter size as input
    }

    // Note - Descoped the problem to take only String inputs
    public void add(String phrase) {
        if (phrase == null || phrase.trim().length() < 1) {
            return;
        }
        int hashCode = phrase.hashCode();
        List<Integer> bitIndices = getBitIndices(hashCode);

        for (int i : bitIndices) {
            filter.set(i);
        }
    }

    public boolean contains(String phrase) {
        if (phrase == null || phrase.trim().length() < 1) {
            return false; 
        }

        int hashCode = phrase.hashCode();

        List<Integer> bitIndices = getBitIndices(hashCode);

        for (int i : bitIndices) {
            if (!filter.get(i)) {
                return false;
            }
        }
        return true;
    }

    // Reset the filter
    public void resetFilter() {
        filter.clear();
    }

    // Generate bitIndices to be set in the filter
    private static List<Integer> getBitIndices(int stringHash) {
        List<Integer> bitIndices = new ArrayList<>();
        bitIndices.add((int) Math.floorMod(stringHash, DEFAULT_FILTER_SIZE));
        bitIndices.add((int) Math.floorMod(stringHash, DEFAULT_FILTER_SIZE/2));
        bitIndices.add((int) Math.floorMod(stringHash, DEFAULT_FILTER_SIZE/3));

        return bitIndices;
    }
}