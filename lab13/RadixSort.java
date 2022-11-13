import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxLen = 0;
        for (String ascii : asciis) {
        maxLen = ascii.length() >= maxLen ? ascii.length() : maxLen;
        }
        String[] res = Arrays.copyOf(asciis,asciis.length);
        for (int i = maxLen - 1; i >= 0; i--) {
            sortHelperLSD(res,i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int R = 256;
        int[] counts = new int[R+1];
        // counts[256] stores the count of empty char String
        for (String ascii : asciis) {
            int asciiNum =  numedCharAt(ascii,index);
            counts[asciiNum] ++;
        }

        int[] starts = new int[R+1];
        int pos = 0;
        for (int i = 0 ;i < counts.length ;i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String ascii : asciis) {
            int position = starts[numedCharAt(ascii,index)];
            sorted[position] = ascii;
            starts[numedCharAt(ascii,index)] ++;
        }

        for (int i = 0;i < asciis.length;i++) {
            asciis[i] = sorted[i];
        }

        return;

    }
    private static int numedCharAt(String ascii,int index) {
        if (index >= ascii.length()) {
            return  0;
        } else {
            return (int) ascii.charAt(index)+1;
        }
    }



    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }


    public static void main(String[] args) {
        String[] test1 = {"ab","sdaa","faca","d","ac","abc"};
        String[] res1 = sort(test1);
        for (String str: res1) {
            System.out.print(str+" ");
        }


    }
}
