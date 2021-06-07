package analisadorlexicolucca.main;

import analisadorlexicolucca.lexico.Token;
import analisadorlexicolucca.lexico.lexScanner;

public class MainClass {
    public static void main(String[] args) {
        lexScanner sc = new lexScanner("input.txt");
        Token token =null;
        do{
            token = sc.nextToken();
            if(token != null){
                System.out.println(token);
            }
        } while(token != null);
        
    }

}
