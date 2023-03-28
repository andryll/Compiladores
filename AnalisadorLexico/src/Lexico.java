
public class Lexico {
	
	//Declarando objeto
	LeitorArquivo ldat;

	//Construtor
	public Lexico(String filename) {
		ldat = new LeitorArquivo(filename);
	}//Constructor

	//Fun��o para ler o arquivo e classificar os tokens
	public Token proximoToken() {
		String str;
		
		//Loop que sempre chama a fun��o de ler palavra do leitos de arquivo
		while((str = ldat.LerProxPalavra()) != "\n" && str != "\r" && str != " ") {
			
			//Verificando se a string est� vazia
			if(!str.isEmpty()) {
			
				//Switch com v�rios casos poss�veis, retornando o Token de cada um desses tipos
				switch(str) {
				//Palavras-chave
				case "DEC":
					return(new Token(TipoToken.PCDec, "DEC"));
					
				case "PROG":
					return(new Token(TipoToken.PCProg, "PROG"));
					
				case "INT":
					return(new Token(TipoToken.PCInt, "INT"));
				
				case "LER":
					return(new Token(TipoToken.PCLer, "LER"));
				
				case "REAL":
					return(new Token(TipoToken.PCReal, "REAL"));
				
				case "IMPRIMIR":
					return(new Token(TipoToken.PCImprimir, "IMPRIMIR"));
				
				case "SE":
					return(new Token(TipoToken.PCSe, "SE"));
				
				case "ENTAO":
					return(new Token(TipoToken.PCEntao, "ENTAO"));
				
				case "ENQTO":
					return(new Token(TipoToken.PCEnqto, "ENQTO"));
				
				case "INI":
					return(new Token(TipoToken.PCIni, "INI"));
				
				case "FIM":
					return(new Token(TipoToken.PCFim, "FIM"));
					
					
				// Operadores Aritm�ticos
				case "-":
					return(new Token(TipoToken.OpAritSub, "-"));
				
				case "+":
					return(new Token(TipoToken.OpAritSoma, "+"));
					
				case "/":
					return(new Token(TipoToken.OpAritDiv, "/"));
				
				case "*":
					return(new Token(TipoToken.OpAritMult, "*"));
					
				//Operadores L�gicos
				case "<":
					return(new Token(TipoToken.OpRelMenor, "<"));
				
				case "<=":
					return(new Token(TipoToken.OpRelMenorIgual, "<="));
					
				case ">":
					return(new Token(TipoToken.OpRelMaior, ">"));
				
				case ">=":
					return(new Token(TipoToken.OpRelMaiorIgual, ">="));
					
				case "==":
					return(new Token(TipoToken.OpRelIgual, "=="));
				
				case "!=":
					return(new Token(TipoToken.OpRelDif, "!="));
						
				//Operadores Booleanos
				case "E":
					return(new Token(TipoToken.OpBoolE, "E"));
				
				case "OU":
					return(new Token(TipoToken.OpBoolOu, "OU"));	
					
				//Outros
				case ":":
					return(new Token(TipoToken.Delim, ":"));
				
				case ":=":
					return(new Token(TipoToken.Atrib, ":="));					
	
				case "(":
					return(new Token(TipoToken.AbrePar, "("));
				
				case ")":
					return(new Token(TipoToken.FechaPar, ")"));	
					
					
				//Caso n�o caia em nenhum dos casos anteriores, ser�o feitas algumas verifica��es.
				default:
					
					//Verifica se a string � um n�mero com a fun��o isNumeric
					if(isNumeric(str)) {
						//Se for numero, verifica se tem um "." na string com a fun��o contains
						if(str.contains(".")) {
							//Se tiver ".", � real
							return (new Token(TipoToken.NumReal, str));
						}else {
							//Sen�o, � inteiro
							return (new Token(TipoToken.NumInt, str));
						}//else
						
					}else {
						//Se n�o for n�mero, verifica se tem aspas.
						if(str.contains("\"")){
							//Se tiver, � cadeia
							return (new Token(TipoToken.Cadeia, str));
							
							//Sen�o, verifica se a primeira letra � min�scula
						}else if(Character.isLowerCase(str.charAt(0))){
							//se for, � vari�vel
							return (new Token(TipoToken.Var, str));
						}//else
						
						//Se na string estiver escrito NULL, retorna o NULL
						if(str == "NULL") {
							return (new Token(TipoToken.NULL, str));
						}//if
						
						//Se n�o caiu em nenhum dos casos anteriores, ent�o n�o pertence e a linha � atribuida junto.
						return(new Token(TipoToken.NaoPertence, str, ldat.line));
						
					}//else
	
				}//switch
				
			}//if
			
		}//while

		return (new Token(TipoToken.NULL, str));
		
	}//ProximoToken
	
	
	//Fun��o para verificar se uma string � um n�mero
	private static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}//if
		
		//Tenta transformar uma string em double.
		try {
			double d = Double.parseDouble(str);
		}catch (NumberFormatException nfe){
			//Se falhar, retorna false
			return false;
		}//try catch
		//Se conseguir, retorna true
		return true;
	}//isNumeric
	
}//class
