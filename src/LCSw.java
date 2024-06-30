import java.util.*;

public class LCSw {

    private int[][] dynamicTable;
    private int[][] dynamicTable2;
    private String[] strA1;
    private String[] strA2;
    private int str1L = 0;
    private int str2L = 0;
    private String[] comStr;

    public LCSw(String[] strA1, String[] strA2) {
        this.strA1 = strA1;
        this.strA2 = strA2;
        this.str1L = strA1.length;
        this.str2L = strA2.length;
        this.initializeDynamicTable();
    }

    private void initializeDynamicTable() {
        dynamicTable = new int[str1L + 1][str2L + 1];
        dynamicTable2 = new int[str1L + 1][str2L + 1];
        for (int i = 0; i < str1L; i++) {
            dynamicTable[i][0] = 0;
            dynamicTable2[i][0] = 0;
        }
        for (int j = 0; j < str2L; j++) {
            dynamicTable[0][j] = 0;
            dynamicTable2[0][j] = 0;
        }
    }

    public int calculateLCS() {
//        for(int i=1;i<=str1L;i++){
//            for(int j=1;j<=str2L;j++){
//                dynamicTable[i][j] = Math.max(dynamicTable[i-1][j],dynamicTable[i][j-1]);
//                dynamicTable2[i][j] = Math.max(dynamicTable2[i-1][j],dynamicTable2[i][j-1]);
//                if(strA1[i-1].trim().equals(strA2[j-1].trim())){
//                    dynamicTable[i][j] = Math.max(dynamicTable[i][j],1+dynamicTable[i-1][j-1]);
//                    dynamicTable2[i][j] = dynamicTable2[i-1][j-1] + (strA1[i-1].length()^2);
//                }
//            }
//        }

        for (int i = 0; i <= str1L; i++) {
            for (int j = 0; j <= str2L; j++) {
                if (i == 0 || j == 0)
                    dynamicTable[i][j] = 0;
                else if (strA1[i - 1].equals(strA2[j - 1]))
                    dynamicTable[i][j] = dynamicTable[i - 1][j - 1] + 1;
                else
                    dynamicTable[i][j] = Math.max(dynamicTable[i - 1][j], dynamicTable[i][j - 1]);
            }
        }


//        System.out.println("P: " + dynamicTable2[str1L][str2L]);
//        int lcssLen = dynamicTable[str1L][str2L];
//        comStr = new String[lcssLen + 1];
//        comStr[lcssLen] = "\0";
//        int k = 1;
//        for(int i = str1L ; i > 0 && (k <= lcssLen); ){
//            for(int j = str2L ; j > 0 && (k <= lcssLen);){
//                if(dynamicTable[i][j] != 0){
//                    if(dynamicTable[i][j] == dynamicTable[i][j - 1]){
//                        j--;
//                    }
//                    else
//                    {
//                        if(dynamicTable[i ][j ] == dynamicTable[i - 1][j ]){
//                            i--;
//                        }
//                        else {
//                            comStr[lcssLen - k] = strA1[i-1];
//                            k++;
//                            i--;
//                            j--;
//                        }
//                    }
//                }
//                else
//                    break;
//            }
//        }
//
//        System.out.println(comStr.length);

//        for(int i=1;i<=str1L;i++){
//            for(int j=1;j<=str2L;j++){
//                dynamicTable[i][j] = 0;
//                if(strA1.charAt(i-1) == strA2.charAt(j-1)){
//                    dynamicTable[i][j] = 1+dynamicTable[i-1][j-1];
//                }
//            }
//        }
//        int count = 0;
//        ArrayList<String> arr = new ArrayList<>();
//        for(int i=dynamicTable.length - 1;i>=0;i--) {
//            for(int j=dynamicTable[0].length-1;j>=0;j--) {
//                StringBuilder sb = new StringBuilder();
//                if(dynamicTable[i][j] >= 7) {
//                    int ci = i, cj = j;
//                    while(dynamicTable[ci][cj] > 0) {
//                        dynamicTable[ci][cj] = 0;
//                        sb.insert(0, strA1.charAt(ci-1) + " ");
//                        ci--;cj--;
//                    }
//                    sb.deleteCharAt(sb.length() - 1);
//                    arr.add(sb.toString());
//                    count += sb.toString().replaceAll("[^a-zA-Z0-9]", "").length();
//                }
//            }
//        }
//        return count;

//        for(int i=1;i<=str1L;i++){
//            for(int j=1;j<=str2L;j++){
//                dynamicTable[i][j] = 0;
//                if(strA1[i-1].equals(strA2[j-1])){
//                    dynamicTable[i][j] = 1+dynamicTable[i-1][j-1];
//                }
//            }
//        }
//
//        int count = 0;
//        ArrayList<String> arr = new ArrayList<>();
//        for(int i=dynamicTable.length-1;i>=0;i--) {
//            for(int j=dynamicTable[0].length-1;j>=0;j--) {
//                StringBuilder sb = new StringBuilder();
//                if(dynamicTable[i][j] >= 4) {
//                    int ci = i, cj = j;
//                    while(dynamicTable[ci][cj] > 0) {
//                        dynamicTable[ci][cj] = 0;
//                        sb.insert(0, strA1[ci-1] + " ");
//                        ci--;cj--;
//                    }
//                    sb.deleteCharAt(sb.length() - 1);
//                    arr.add(sb.toString());
//                    count += sb.toString().split(" ").length;
//                }
//            }
//        }
//
//        System.out.println(arr.toString());
//        return count;



        return dynamicTable[str1L][str2L];


    }

    public static void main(String[] args){
        String s1 = "today is sunny and okay I feel yes so happy",
                s2 = "tomorrow is sunny and that makes today is sunny and okay me feel yes so happy too";
        LCSw lcs = new LCSw(s1.split(" "),s2.split(" "));
        System.out.println(lcs.calculateLCS());
    }

    }





