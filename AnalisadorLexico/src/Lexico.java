
public class Lexico {
	
	LeitorArquivo ldat;

	public Lexico(String filename) {
		ldat = new LeitorArquivo(filename);
	}//Constructor

	public Token proximoToken() {
		int c;
		
		while((c = ldat.LerProxCaractere()) != -1) {
			
			char character = (char) c;
			
			switch(character) {
			case '-':
				return(new Token(TipoToken.OpAritSub, "-"));
			
			case '+':
				return(new Token(TipoToken.OpAritSoma, "+"));
				
			case '/':
				return(new Token(TipoToken.OpAritDiv, "/"));
			
			case '*':
				return(new Token(TipoToken.OpAritMult, "*"));
				
			case ':':
				if((character = (char) ldat.LerProxCaractere()) == '=') {
					return(new Token(TipoToken.Atrib, ":="));
				}else {
					return(new Token(TipoToken.Delim, ":"));
				}//else
				
			
			
			
			}//switch
			
		}//while
			
		return null;
	}//ProximoToken
	
	
}//class
