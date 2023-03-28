import java.util.ArrayList;

/*
 * Compiladores 2023/01
 * André Luís de Oliveira - 2270170
 * Eng. Comp. | UTFPR - AP
 */


public class Main {
	
	public static void main (String[] args) {
		//Declaração de Variáveis
		Token tok;	
		System.out.println("\nNome Arquivo: " + args[0] + "\n");
		Lexico lex = new Lexico(args[0]);
		ArrayList<Token> lista = new ArrayList<Token>();
		
		//Criando os tokens e colocando-os em uma ArrayList
		while((tok = lex.proximoToken()).padrao != TipoToken.NULL) {
			lista.add(tok);
		}//while		
			
		//Printando os tokens da ArrayList
		for(int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}//for
		
		System.out.println("<EOF>");
		
	}//main
	
}//class
