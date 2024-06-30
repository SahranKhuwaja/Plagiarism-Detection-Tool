import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {
    public static List<String> stopWords =Arrays
            .asList("i","me","my","we","our","ours","you","your",
                    "yours","he","him","his","she",
                    "her","hers","they","them","their",
                    "theirs","that","am","is","are",
                    "was","were", "the","and","but");

    public static void main(String[] args) throws IOException {
        if(args.length>=2){
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            BufferedReader bf1 = new BufferedReader(new FileReader(file1));
            BufferedReader bf2 = new BufferedReader(new FileReader(file2));
            String data1, data2;
            String str1 = "";
            String str2 = "";
            String[] strA1;
            String[] strA2;
            while((data1=bf1.readLine())!=null){
                if(data1.length()>0){
                    str1 += data1;
                }
            }
            while((data2=bf2.readLine())!=null){
                if(data2.length()>0 ){
                    str2 += data2;
                }
            }
            strA1 = preprocess(str1);
            strA2 = preprocess(str2);
            System.out.println(Arrays.toString(strA1));
            System.out.println(Arrays.toString(strA2));
//            LCSw lcs;
//            int len;
//            if(strA1.length>strA2.length){
//                lcs = new LCSw(strA2, strA1);
//                len = strA2.length;
//            }else {
//                lcs = new LCSw(strA1, strA2);
//                len = strA1.length;
//            }
//            int out = lcs.calculateLCS();
//            System.out.println(out);
//            System.out.println("l " + strA1.length);
//            System.out.println("l " + strA2.length);
//            System.out.println((double) out/len);
            int len;
            LCSw lcs;
            Levenshtein lv;
            if(strA1.length>strA2.length){
                lcs = new LCSw(strA1,strA2);
                lv = new Levenshtein(strA2, strA1);
                len = strA1.length;
            }else {
                lcs = new LCSw(strA2,strA1);
                lv = new Levenshtein(strA1, strA2);
                len = strA2.length;
            }

            int out1 = lcs.calculateLCS();
            int out = lv.calculateLevenshteinDistance();
            System.out.println(out);
            System.out.println("l " + strA1.length);
            System.out.println("l " + strA2.length);
            System.out.println((double)out/len);
            System.out.println(1-((double) out/len));
        }
    }

    public static String[] preprocess(String input){
//        String a [] = input.toLowerCase()
//                //.trim()
//                //.replaceAll("\\s+", " ")
//                .replaceAll("[^a-zA-Z]", " ")
//                .trim()
//               // .replaceAll(" +", " ")
//                .split("\\s+");
       // Arrays.sort(a);
        ArrayList<String> filtered =
                Stream.of(input.toLowerCase().trim().replaceAll("[^a-zA-Z]", " ")
                                .trim().split("\\s+"))
                        //.distinct()
                        .sorted()
                        .collect(Collectors.toCollection(ArrayList<String>::new));
        //filtered.removeAll(stopWords);
       return filtered.toArray(String[]::new);
       // .trim();

    }
}
