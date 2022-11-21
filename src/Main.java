import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        String path1 = "/Users/dipeshasd/Desktop/FullOuterJoin/src/File1.txt";
        String path2 = "/Users/dipeshasd/Desktop/FullOuterJoin/src/File2.txt";

        BufferedReader br1 = new BufferedReader(new FileReader(path1));
        BufferedReader br2 = new BufferedReader(new FileReader(path2));

        String line1 = "";
        String line2 = "";

        String joinOn = "A,B";

        int[] joinKey = findKeys(joinOn);


        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        line1 = br1.readLine();
        line2 = br2.readLine();
        int resultLength = line1.split(",").length + line2.split(",").length;
        System.out.println(line1+","+line2);


        //Read the lines from the file and perform left table join


        while ((line1 = br1.readLine()) != null) {
            String[] arr1 = line1.split(",");
            while ((line2 = br2.readLine()) != null) {
                String[] arr2 = line2.split(",");
                if (join(arr1, arr2, joinKey) != null) {
                    result.add(join(arr1, arr2, joinKey));
                }
            }
            br2 = new BufferedReader(new FileReader(path2));
        }


        String line11 = "";
        String line22 = "";
        br1 = new BufferedReader(new FileReader(path1));
        br1.readLine();
        br2 = new BufferedReader(new FileReader(path2));
        br2.readLine();


        while ((line11 = br1.readLine()) != null) {
            boolean flag = false;
            //convert line11 to string
            String[] arr11 = line11.split(",");
            String compare1 = "";
            for (int i = 0; i < arr11.length; i++) {
                compare1 += arr11[i] + " ";
            }

            for (int i = 0; i < result.size(); i++) {
                if (check(result.get(i), compare1)) {
                    flag = true;
                }
            }
            if (!flag) {
                String temp = "";
                for (int i = 0; i < resultLength / 2; i++) {
                    temp += "- ";
                }
                result.add(new ArrayList<String>(Collections.singleton(compare1 + temp)));
            }


        }

        while ((line22 = br2.readLine()) != null) {
            boolean flag = false;
            //convert line11 to string
            String[] arr11 = line22.split(",");
            String compare1 = "";
            for (int i = 0; i < arr11.length; i++) {
                compare1 += arr11[i] + " ";
            }

            for (int i = 0; i < result.size(); i++) {

                if (check2(result.get(i), compare1)) {
                    flag = true;
                }
            }
            if (!flag) {
                String temp = "";
                for (int i = 0; i < resultLength / 2; i++) {
                    temp += "- ";
                }
                result.add(new ArrayList<String>(Collections.singleton( temp+compare1 )));
            }
        }


        print(result);
    }


    public static void print(ArrayList<ArrayList<String>> result) {
        for (ArrayList<String> strings : result) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
    public static ArrayList<String> join(String[] arr1, String[] arr2, int[]index){
        ArrayList<String> result=new ArrayList<String>(arr1.length+arr2.length);
        boolean flag=false;
        for(int i=0;i<index.length;i++){
            int currIndex=index[i];
            if(arr1[currIndex].equals(arr2[currIndex]) ){
                flag=true;
            }
            else {
                flag=false;
                break;
            }
        }
        if(flag){
            for(int i=0;i<arr1.length;i++){
                result.add(arr1[i]);
            }
            for(int i=0;i<arr2.length;i++){
                result.add(arr2[i]);
            }
            return result;
        }
        else {
            return null;
        }

    }

    //a function that takes an arraylist and a string and checks if the string is present in the arraylist
    public static boolean check(ArrayList<String> arr, String str){
        String line = "";
        for (int i = 0; i < arr.size()/2; i++) {
            line += arr.get(i)+" ";
        }
        if (line.equals(str)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean check2(ArrayList<String> arr, String str){
        String line = "";
        for (int i = arr.size()/2; i < arr.size(); i++) {
            line += arr.get(i)+" ";
        }
        if (line.equals(str)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int[] findKeys(String ip) throws IOException {
        String[] arr=ip.split(",");
        int[] result=new int[arr.length];
        BufferedReader br = new BufferedReader(new FileReader("/Users/dipeshasd/Desktop/FullOuterJoin/src/File1.txt"));
        String line = "";
        line = br.readLine();
        String[] arr1 = line.split(",");
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr1.length;j++){
                if(arr[i].equals(arr1[j])){
                    result[i]=j;
                }
            }
        }
        return result;

    }


}
