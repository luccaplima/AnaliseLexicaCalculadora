package analisadorlexicolucca.lexico;

public class Token {
    public static final String TK_PR =  "palavra reservada";
    public static final String TK_PONTUACAO =  "ptç";
    public static final String TK_VARIAVEL =  "end.mem.fict";
    public static final String TK_NUMBER =  "cte";
    public static final String TK_OPERADOR = "operador";
    public static final String TK_OPERADORATR = "atribuiçao";
    
    
    private String type;
    private String text;
    
    public Token(String type, String text){
        super();
        this.type = type;
        this.text = text;
    }
    
    public Token(){
        super();
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
   
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    @Override
    public String toString(){
        return "Token [type=" + type + ", text=" + text + "]";
    }
}
