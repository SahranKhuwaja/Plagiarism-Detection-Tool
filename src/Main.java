import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length>=2){
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            BufferedReader bf1 = new BufferedReader(new FileReader(file1));
            BufferedReader bf2 = new BufferedReader(new FileReader(file2));
            String data1, data2;
            String str1 = "";
            String str2 = "";
            String strA1;
            String strA2;
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
            int len;
            strA1 = preprocess(str1);
            strA2 = preprocess(str2);
            System.out.println(strA1);
            System.out.println(strA2);

            LCS lcs;
            if(strA1.length()>strA2.length()){
                lcs = new LCS(strA1,strA2);
                len = strA2.length();
            }else {
                lcs = new LCS(strA2,strA1);
                len = strA1.length();
            }
            int out = lcs.calculateLCS();

            System.out.println(out);
            System.out.println("l " + strA1.length());
            System.out.println("l " + strA2.length());
            System.out.println((double)out/strA2.length());


        }
    }

    public static String preprocess(String input){
        return input.toLowerCase()
                .trim()
               // .replaceAll(" ", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .replaceAll("\\s+","")
               // .replaceAll(" +", "")
                .trim();



    }
}
