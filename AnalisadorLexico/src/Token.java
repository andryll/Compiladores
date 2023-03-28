
public class Token {

	//Declarando vari�veis
	public TipoToken padrao;
	public String lexema;
	public int line;
	
	//Inicializando algumas vari�veis no construtor
	public Token(TipoToken pad, String lex) {
		this.padrao = pad;
		this.lexema = lex;
	}//Consctructor
	
	//Construtor com 3 vari�veis
	public Token(TipoToken pad, String lex, int line) {
		this.padrao = pad;
		this.lexema = lex;
		this.line = line;
	}//Consctructor

	@Override
	public String toString() {
		//Se o tipo token for NaoPertence, mostra mensagem de erro e a linha.
		if(this.padrao == TipoToken.NaoPertence) {
			return "Linha" + line + ": \"" + lexema + "\" n�o pertence a linguagem GYH.";
		//Sen�o, mostra o Token
		}else {
			return "< " + padrao + ", \"" + lexema + "\" >";
		}//else
	}//toString
		
}//Token