import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SampleProject {
    public static void main(String[] args) throws IOException {
//        for (String s: args) {
//            System.out.println(s);
//        }
        long start = System.currentTimeMillis();
        System.out.println((double) Files.size(Paths.get(args[0]))/1024);
        String s1 = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
        String s2 = new String(Files.readAllBytes(Paths.get(args[1])), StandardCharsets.UTF_8);



        s1 = s1.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s{2,}", " ").trim();
        s2 = s2.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s{2,}", " ").trim();

        //System.out.println(s1);
        int len_list = 0;
        List<String> lst = solve(s2,s1);
        for(int i =0 ;i<lst.size(); i++){
            len_list = len_list + lst.get(i).length();

            System.out.println(lst.get(i));
        }

        //float r1 = (float)len_list/s1.length();
        float r2 = (float)len_list/Math.min(s1.length(),s2.length());
        System.out.println(len_list);
        System.out.println(s1.length());
        System.out.println(s2.length());
//        System.out.println(r1);
        System.out.println(r2);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000F);
        //char[] ch1 = s1.toCharArray();
        //char[] ch2 = s2.toCharArray();
        //System.out.println((float)lcs(ch1,ch2,ch1.length,ch2.length)/(Math.min(ch1.length,ch2.length)));

    }

    public static List<String> solve(String s1, String s2) {
        List<String> res = new ArrayList<>();
        String[] arr1 = s1.split(" "), arr2 = s2.split(" ");
        int[][] dp = new int[arr1.length+1][arr2.length+1];
        for(int i=1;i<=arr1.length;i++) {
            for(int j=1;j<=arr2.length;j++) {
                if(arr1[i-1].equals(arr2[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        for(int i=dp.length - 1;i>=0;i--) {
            for(int j=dp[0].length-1;j>=0;j--) {
                StringBuilder sb = new StringBuilder();
                if(dp[i][j] >= 2) {
                    int ci = i, cj = j;
                    while(dp[ci][cj] > 0) {
                        dp[ci][cj] = 0;
                        sb.insert(0, arr1[ci-1] + " ");
                        ci--;cj--;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }

}