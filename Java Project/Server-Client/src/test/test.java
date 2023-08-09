package test;

import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String word = in.nextLine();
        Message msg = new Message(word);
        System.out.println(msg.getEx());
    }
}
