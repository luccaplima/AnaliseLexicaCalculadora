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
    
    public Token nextToken(){
        char currentChar;
        String termo = "";
        if (isEOF()){
            return null;
        }
        estado = 0;
        while(true){
            Token token = new Token();
            currentChar = nextChar();
            
            switch(estado){
                case 0:
                    if(isM(currentChar)){
                        estado = 1;
                    }
                    else if(isOpenKey(currentChar)){
                        estado = 5;
                    }
                    else if(isChar(currentChar)){
                        estado = 6;
                        termo += currentChar;
                    }
                    else if(isOperatorAtr(currentChar)){
                        estado = 8;
                    }
                    else if(isNum(currentChar)){
                        termo += currentChar;
                        estado = 9;
                    }
                    else if(isOperator(currentChar)){
                        termo += currentChar;
                        estado = 10;
                    }
                    else if(isNum(currentChar)){
                        estado = 11;
                    }
                    else if(isCloseKey(currentChar)){
                        estado = 12;
                    }
                    else {
                        throw new RuntimeException("Simbolo não conhecido para o estagio!");
                    }
                    break;
                case 1:
                    if(isA(currentChar)){
                        estado=2;
                    }
                    else {
                        throw new RuntimeException("a expected!");
                    }
                    break;
                case 2:
                    if(isI(currentChar)){
                        estado = 3;
                    }
                    else {
                        throw new RuntimeException("i expected!");
                    }
                    break;
                case 3:
                    if(isN(currentChar)){
                        estado = 4;
                    }
                    else {
                        throw new RuntimeException("n expected!");
                    }
                    break;
                case 4:
                    back();
                    token.setType(Token.TK_PR);
                    token.setText("main");
                    return token;
                case 5:
                    back();
                    token.setType(Token.TK_PONTUACAO);
                    token.setText("[ { ]");
                    return token;
                case 6:
                    if(isNum(currentChar) || isChar(currentChar)){
                        estado = 6;
                        termo += currentChar;
                    }
                    else if (isSpace(currentChar)){
                        estado = 7;
                    }
                    else if(isOperatorAtr(currentChar)){
                        back();
                        estado = 7;
                    }
                    else{
                        throw new RuntimeException("Simbolo inesperado!");
                    }
                    break;
                case 7:
                    back();
                    token.setType(Token.TK_VARIAVEL);
                    token.setText(termo);
                    return token;
                case 8:
                    back();
                    token.setType(Token.TK_OPERADORATR);
                    token.setText("[ = ]");
                    return token;
                case 9:
                    back();
                    token.setType(Token.TK_NUMBER);
                    token.setText(termo);
                    return token;
                case 10:
                    back();
                    token.setType(Token.TK_OPERADOR);
                    token.setText(termo);
                    return token;
                case 11:
                    back();
                    token.setType(Token.TK_NUMBER);
                    return token;
                case 12:
                    token.setType(Token.TK_PONTUACAO);
                    token.setText("[ } ]");
                    return token;
            }
        }
    }

    private void back(){
        pos--;
    }
    
    private boolean isM(char c){
        return c == 'm';
    }
    
    private boolean isA(char c){
        return c == 'a';
    }
    
    private boolean isI(char c){
        return c == 'i';
    }
    
    private boolean isN(char c){
        return c == 'n';
    }
    
    private boolean isOpenKey(char c){
        return (c == '{');
    }
            
    private boolean isCloseKey(char c){
    return c == '}';
    }  
    
    private boolean isNum(char c){
        return c >= '0' && c <= '9';
    } 
    
    private boolean isChar(char c){
        return c >= 'a' && c <= 'z';
    }  
            
    private boolean isOperatorAtr(char c){
        return c == '=';
    }
    
    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    private boolean isSpace(char c){
        return c == ' ' || c== '\t' || c == '\n' || c == '\r';
    }
    
    private char nextChar(){
        return conteudo[pos++];
    }
    
    private boolean isEOF(){
        return pos == conteudo.length;
    }
   
}
