// TextScan.java
// Opens text file supplied on the command line
// Usage:  java TextScan filename
// Counts all tokens found (a token is anything delimited by a space character)
// Displays each token found to the screen
// Code may be used in part for Project 5 with proper citing. 

//This was taken from CSCI 1933's Project 5 for the fall semester of 2019.
import java.util.Scanner;
import java.io.*;

public class TextScan {

    public static void main(String[] args) {
        HashMap<String,String> hashMap=new HashMap<String, String>();
        Scanner readFile = null;
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + args[0]);
        try {
            readFile = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " not found");
            System.exit(1);  
        }

        System.out.println("Connection to file: " + args[0] + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            hashMap.put(s,s);//adds the elements to the list.
            count++;
        }
        
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        //Test Cases I ran below.
        /*
        hashMap.display();
        System.out.println();
        System.out.println(hashMap.size());
        System.out.println();
        hashMap.replace("Into","TheHouse");
        hashMap.remove("were");
        hashMap.display();
        System.out.println();
        System.out.println(hashMap.size());

         */

    }  // main

}  // TextScan
