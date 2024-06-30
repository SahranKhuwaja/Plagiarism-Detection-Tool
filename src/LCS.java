import java.util.*;

public class LCS {

    private int[][] dynamicTable;
    private String strA1;
    private String strA2;
    private int str1L = 0;
    private int str2L = 0;
    private char[] comStr;

    public LCS(String strA1, String strA2) {
        this.strA1 = strA1;
        this.strA2 = strA2;
        this.str1L = strA1.length();
        this.str2L = strA2.length();
        this.initializeDynamicTable();
    }

    private void initializeDynamicTable() {
        dynamicTable = new int[str1L + 1][str2L + 1];
        for (int i = 0; i < str1L; i++) {
            dynamicTable[i][0] = 0;
        }
        for (int j = 0; j < str2L; j++) {
            dynamicTable[0][j] = 0;
        }
    }

    public int calculateLCS() {
        for(int i=1;i<=str1L;i++){
            for(int j=1;j<=str2L;j++){
                dynamicTable[i][j] = Math.max(dynamicTable[i-1][j],dynamicTable[i][j-1]);
                if(strA1.charAt(i-1) == strA2.charAt(j-1)){
                    dynamicTable[i][j] = Math.max(dynamicTable[i][j],1+dynamicTable[i-1][j-1]);
                }
            }
        }

//        for (int i = 0; i <= str1L; i++) {
//            for (int j = 0; j <= str2L; j++) {
//                if (i == 0 || j == 0)
//                    dynamicTable[i][j] = 0;
//                else if (strA1.charAt(i - 1) == strA2.charAt(j - 1))
//                    dynamicTable[i][j] = dynamicTable[i - 1][j - 1] + 1;
//                else
//                    dynamicTable[i][j] = Math.max(dynamicTable[i - 1][j], dynamicTable[i][j - 1]);
//            }
//        }
//
//        int index = dynamicTable[str1L][str2L];
//        int temp = index;
//
//        char[] lcs = new char[index + 1];
//        lcs[index] = '\0';
//        int count = 0;
//        int i = str1L, j = str2L;
//        while (i > 0 && j > 0) {
//            if (strA1.charAt(i - 1) == strA2.charAt(j - 1)) {
//                count +=1;
//                lcs[index - 1] = strA1.charAt(i - 1);
//
//                i--;
//                j--;
//                index--;
//            }
//
//            else if (dynamicTable[i - 1][j] > dynamicTable[i][j - 1])
//                i--;
//            else
//                j--;
//        }
//        System.out.println(count);
////        for(int i=1;i<=str1L;i++){
////            for(int j=1;j<=str2L;j++){
////                dynamicTable[i][j] = 0;
////                if(strA1.charAt(i-1) == strA2.charAt(j-1)){
////                    dynamicTable[i][j] = 1+dynamicTable[i-1][j-1];
////                }
////            }
////        }
////
////        int count = 0;
////        ArrayList<String> arr = new ArrayList<>();
////        for(int i=dynamicTable.length-1;i>=0;i--) {
////            for(int j=dynamicTable[0].length-1;j>=0;j--) {
////                StringBuilder sb = new StringBuilder();
////                if(dynamicTable[i][j] >= 4) {
////                    int ci = i, cj = j;
////                    while(dynamicTable[ci][cj] > 0) {
////                        dynamicTable[ci][cj] = 0;
////                        sb.insert(0, strA1.charAt(ci-1) + " ");
////                        ci--;cj--;
////                    }
////                    sb.deleteCharAt(sb.length() - 1);
////                    arr.add(sb.toString());
////                    count += sb.toString().replaceAll("[^a-zA-Z0-9]", "").length();
////                }
////            }
////        }
////        System.out.println(arr.toString().replaceAll(",",""));
////        //return count;

        return dynamicTable[str1L][str2L];



    }

}
