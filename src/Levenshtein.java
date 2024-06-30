public class Levenshtein {

    private int[][] dynamicTable;
    private String[] strA1;
    private String[] strA2;
    private int str1L = 0;
    private int str2L = 0;
    private char[] comStr;

    public Levenshtein(String[] strA1, String[] strA2) {
        this.strA1 = strA1;
        this.strA2 = strA2;
        this.str1L = strA1.length;
        this.str2L = strA2.length;
        this.initializeDynamicTable();
    }

    private void initializeDynamicTable() {
        dynamicTable = new int[str1L + 1][str2L + 1];
        for (int i = 0; i < str1L; i++) {
            dynamicTable[i][0] = i;
        }
        for (int j = 0; j < str2L; j++) {
            dynamicTable[0][j] = j;
        }
    }

    public int calculateLevenshteinDistance() {
        for(int i=1;i<=str1L;i++){
            for(int j=1;j<=str2L;j++){
                if(strA1[i-1].equals(strA2[j-1])){
                    dynamicTable[i][j] = Math.min(dynamicTable[i-1][j] + 1, dynamicTable[i][j-1] + 1);
                    dynamicTable[i][j] = Math.min(dynamicTable[i][j], dynamicTable[i-1][j-1]);
                }
                else {
                    dynamicTable[i][j] = Math.min(dynamicTable[i-1][j] + 1, dynamicTable[i][j-1] + 1);
                    dynamicTable[i][j] = Math.min(dynamicTable[i][j], dynamicTable[i-1][j-1] + 1);

                }
            }
        }

        return dynamicTable[str1L][str2L];

    }




}
