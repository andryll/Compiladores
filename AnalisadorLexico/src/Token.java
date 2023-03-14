
public class Token {

	public TipoToken padrao;
	public String lexema;
	
	public Token(TipoToken pad, String lex) {
		this.padrao = pad;
		this.lexema = lex;
	}//Consctructor

	@Override
	public String toString() {
		return "<" + padrao + ", " + lexema + ">";
	}
	
}//Class
