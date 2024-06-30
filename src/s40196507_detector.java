import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;



public class s40196507_detector {

    public static void main(String[] args) throws IOException {
        if (args.length >= 2) {

            int plagiarism = detectPlagiarism(args);

            System.out.println(plagiarism);

        } else {
            System.out.println("2 Arguments are required!");
        }

    }

    public static int detectPlagiarism(String[] args) throws IOException {

        int plagiarism = 0;

        String doc1Path = args[0];
        String doc2Path = args[1];

        String doc1 = readFile(doc1Path);
        String doc2 = readFile(doc2Path);

        doc1 = preprocess(doc1);
        doc2 = preprocess(doc2);


        CosineSimilarity cos = new CosineSimilarity(doc1, doc2);
        double similarity = cos.calculateCosineSimilarity();
        double similarityPercentage = similarity * 100;

        if (similarityPercentage >= 50) {
            plagiarism = 1;
        } else {
            double doc1Size = sizeOfFileInKB(doc1Path);
            double doc2Size = sizeOfFileInKB(doc2Path);
            double totalSize = doc1Size + doc2Size;

            if (totalSize <= 30) {

                CommonSequence cs = new CommonSequence(doc1, doc2);
                int output = cs.calculateCommonSequence();
                similarityPercentage = (double) output / doc2.length();
                similarityPercentage = similarityPercentage * 100;
                if(similarityPercentage>=50){
                    plagiarism = 1;
                }

            } else {
                if(similarityPercentage>=40){
                    plagiarism = 1;
                }
            }
        }
        return plagiarism;
    }

    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    public static double sizeOfFileInKB(String path) throws IOException {
        return Files.size(Paths.get(path)) / 1024;
    }

    public static String preprocess(String input) {
        return input
                .toLowerCase()
                .trim()
                .replaceAll("[^a-zA-Z0-9]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static class CosineSimilarity {

        private String[] docA1;
        private String[] docA2;
        private int docA1L = 0;
        private int docA2L = 0;
        private HashMap<String, Frequency> frequencies = new HashMap<>();
        private ArrayList<String> uniqueWords = new ArrayList<>();
        private Frequency fq;
        private double xy = 0;
        private double x = 0;
        private double y = 0;

        public CosineSimilarity(String doc1, String doc2) {
            this.docA1 = doc1.split("\\s+");
            this.docA2 = doc2.split("\\s+");
            this.docA1L = docA1.length;
            this.docA2L = docA2.length;
        }

        public double calculateCosineSimilarity() {

            this.computeFrequencies();

            for (int idx = 0; idx < uniqueWords.size(); idx++) {
                fq = frequencies.get(uniqueWords.get(idx));
                double fq1 = fq.getFq1();
                double fq2 = fq.getFq2();
                xy = xy + fq1 * fq2;
                x = x + Math.pow(fq1, 2);
                y = y + Math.pow(fq2, 2);
            }
            x = Math.sqrt(x);
            y = Math.sqrt(y);

            double similarity = ((xy) / (x * y));

            return similarity;
        }

        private void computeFrequencies() {
            for (int idx = 0; idx < docA1L - 1; idx++) {
                String term = docA1[idx] + docA1[idx + 1];
                if (!term.isEmpty()) {
                    if (frequencies.containsKey(term)) {
                        fq = frequencies.get(term);
                        fq.setFq1(fq.getFq1() + 1);
                        frequencies.put(term, fq);
                    } else {
                        uniqueWords.add(term);
                        fq = new Frequency(1, 0);
                        frequencies.put(term, fq);
                    }
                }
            }

            for (int idx = 0; idx < docA2L - 1; idx++) {
                String term = docA2[idx] + docA2[idx + 1];
                if (!term.isEmpty()) {
                    if (frequencies.containsKey(term)) {
                        fq = frequencies.get(term);
                        fq.setFq2(fq.getFq2() + 1);
                        frequencies.put(term, fq);
                    } else {
                        fq = new Frequency(0, 1);
                        frequencies.put(term, fq);
                        uniqueWords.add(term);
                    }
                }
            }
        }

    }


    public static class CommonSequence {

        private int[][] dynamicTable;
        private String[] docA1;
        private String[] docA2;
        private int docA1L = 0;
        private int docA2L = 0;

        public CommonSequence(String doc1, String doc2) {
            this.docA1 = doc1.split(" ");
            this.docA2 = doc2.split(" ");
            this.docA1L = docA1.length;
            this.docA2L = docA2.length;
            dynamicTable = new int[docA1L + 1][docA2L + 1];
        }

        public int calculateCommonSequence() {

            for (int row = 1; row <= docA1L; row++) {
                for (int col = 1; col <= docA2L; col++) {
                    dynamicTable[row][col] = 0;
                    if (docA1[row - 1].equals(docA2[col - 1])) {
                        dynamicTable[row][col] = 1 + dynamicTable[row - 1][col - 1];
                    }
                }
            }
            int count = calculateCount();
            return count;
        }

        private int calculateCount() {

            StringBuilder stb;
            int count = 0;

            for (int row = dynamicTable.length - 1; row >= 0; row--) {
                for (int col = dynamicTable[0].length - 1; col >= 0; col--) {
                    stb = new StringBuilder();
                    if (dynamicTable[row][col] >= 2) {
                        int r = row, c = col;
                        while (dynamicTable[r][c] > 0) {
                            dynamicTable[r][c] = 0;
                            stb.insert(0, docA1[r - 1] + " ");
                            r--;
                            c--;
                        }
                        stb.deleteCharAt(stb.length() - 1);
                        count = count + stb.toString().length();
                    }
                }
            }

            return count;
        }

    }

    public static class Frequency {

        private int fq1;
        private int fq2;

        private Frequency(int fq1, int fq2) {
            this.fq1 = fq1;
            this.fq2 = fq2;
        }

        public int getFq1() {
            return fq1;
        }

        public int getFq2() {
            return fq2;
        }

        public void setFq1(int fq1) {
            this.fq1 = fq1;
        }

        public void setFq2(int fq2) {
            this.fq2 = fq2;
        }

    }
}




