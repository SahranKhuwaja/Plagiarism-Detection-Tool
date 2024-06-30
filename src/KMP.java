import java.util.Arrays;

//public class KMP {
//
//    private String doc1;
//    private String doc2;
//    private int doc1L = 0;
//    private int doc2L = 0;
//    private int[] patternPrepossed;
//
//    public KMP(String doc1, String doc2) {
//        this.doc1 = doc1;
//        this.doc2 = doc2;
//        this.doc1L = doc1.length();
//        this.doc2L = doc2.length();
//        this.patternPreprocessing();
//    }
//
//    private void patternPreprocessing() {
//        patternPrepossed = new int[doc2L + 1];
//        int i = 0;
//        int j = 1;
//        while (j < doc2L) {
//            if (doc2.charAt(i) == doc2.charAt(j)) {
//                i++;
//                patternPrepossed[j] = i;
//                j++;
//            } else if (i == 0) {
//                patternPrepossed[j] = 0;
//                j++;
//            } else {
//                i = patternPrepossed[i - 1];
//            }
//        }
//    }
//
//    public int KMP_Seacrh() {
//        int i = 0;
//        int j = 0;
//        int maxLen = 0;
//        while (i < doc2L && j < doc1L) {
//
//                if (doc2.charAt(i) == doc1.charAt(j)) {
//                    System.out.println(doc2.charAt(i));
//                    System.out.println(doc1.charAt(i));
//                    i+=1;
//                    j+=1;
//                    maxLen += i+1;
//                } else {
//                    if (i != 0) {
//                        i = patternPrepossed[i - 1];
//                    } else {
//                        j++;
//                    }
//                }
//        }
//        return maxLen;
//    }
//
//
//}

public class KMP {
    /**
     * Pre processes the pattern array based on proper prefixes and proper
     * suffixes at every position of the array
     *
     * @param ptrn
     *            word that is to be searched in the search string
     * @return partial match table which indicates
     */
    public int searchTimeCount= 0;
    public int preprocessingTimeCount= 0, timeCount=0;


    public int[] preProcessPattern(char[] ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length;
        int[] b = new int[ptrnLen + 1];

        b[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                j = b[j];
                preprocessingTimeCount++;
            }
            i++;
            j++;
            b[i] = j;

        }
        // print pettern, partial match table and index
        /*System.out.println("printing pattern, partial match table, and its index");
        System.out.print(" ");*/
        for (char c : ptrn) {
            //System.out.print(c + "   ");
        }
        // System.out.println(" ");
        for (int tmp : b) {
            //System.out.print(tmp + "   ");
        }
        // System.out.print("\n ");
        for (int l = 0; l < ptrn.length; l++) {
            //System.out.print(l + "   ");
        }
        //  System.out.println();
        return b;
    }

    /**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    public int searchSubString(String text, String ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        String patern = ptrn;
        String txt = text;
        char[] patrn = patern.toCharArray();
        char[] t = txt.toCharArray();

        int ptrnLen = patrn.length;
        int txtLen = t.length;
        int matchCounter;
        int maxSubstringLength=0;

        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(patrn);

        while (i < txtLen) {
            while (j >= 0 && t[i] != patrn[j]) {
               /* System.out.println("Mismatch happened, between text char "
                        + t[i] + " and pattern char " + patrn[j]
                        + ", \nhence jumping the value of " + "j from " + j
                        + " to " + b[j] + " at text index i at " + i
                        + " based on partial match table");*/
                j = b[j];

                searchTimeCount++;
            }
            i++;
            j++;
            maxSubstringLength = Math.max(maxSubstringLength, j);
            //System.out.println("The value of J is "+ j);

            // a match is found
            if (j == ptrnLen) {
                /*System.out.println("FOUND SUBSTRING AT i " + i + " and index:"
                        + (i - ptrnLen));
                System.out.println("Setting j from " + j + " to " + b[j]);*/
                j = b[j];
            }
        }
        //System.out.println("The Length of Max Substring Length is " + maxSubstringLength);
        return maxSubstringLength;
    }
    public int getTimeCount(){
        if(searchTimeCount>preprocessingTimeCount)
        { timeCount = searchTimeCount;
        }
        else
        {
            timeCount = preprocessingTimeCount;
        }
        return timeCount;
    }
    // only for test purposes
    public static void main(String[] args) {
        KMP stm = new KMP();
        // pattern
        String patern,text;
        text ="Sachin";
        patern = "Sach";
        stm.searchSubString(text, patern);
    }
}


