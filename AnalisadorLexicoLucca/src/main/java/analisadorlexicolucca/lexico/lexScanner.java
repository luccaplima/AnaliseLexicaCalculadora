package analisadorlexicolucca.lexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class lexScanner {
    
    private char[] conteudo;
    private int    estado;
    private int    pos;
    
    public lexScanner(String filename){
        try{
            String txtConteudo;
            txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            System.out.println(txtConteudo);
            conteudo = txtConteudo.toCharArray();
            pos = 0;
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
    }