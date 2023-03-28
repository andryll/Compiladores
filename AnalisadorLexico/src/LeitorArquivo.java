import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LeitorArquivo {
	
	//Declarando vari�veis
	public InputStream is1;
	public BufferedInputStream is;
	public int line = 1;
	
	//Construtor
	public LeitorArquivo(String name) {
		try {			
			//Cria uma nova FileInputStream do arquivo desejado e uma BufferedInputStream do da Stream anterior
			is1 = new FileInputStream(name);
			is = new BufferedInputStream(is1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//TryCatch
		
	}//LeitorArquivo
	
	
	//Fun��o para ler pr�ximo caractere (n�o utilizada)
	public int LerProxCaractere() {
		int c = -1;
		try {
			c = is.read();		
		} catch (IOException e) {
			e.printStackTrace();
		}//TryCatch
		return(c);
	}//LerProxCaractere
	
	
	//Fun��o para ler a pr�xima palavra
	public String LerProxPalavra() {
		//Vari�vel de controle pra verificar contador de linha
		boolean firstN = true;
		int c = -1;
		//StringBuilder pra ir criando a palavra com os caracteres
		StringBuilder sb = new StringBuilder();
		
		//Verificando se � poss�vel utilizar o mark reset da BufferedInputStream
		if(!is.markSupported()) {
			System.out.println("Mark nao suportado");
			System.exit(0);
		}//if
		
		//Cirando um ponto de marca��o
		is.mark(1);
		
		try {
			//Lendo caractere
			c = is.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Verificando quebras de linha e incrementando contador de linhas
		if((char)c == '\n') {
			this.line++;
			firstN = false;
		}//if

		//Verificando se o caractere � #, se for, l� todos os caraceteres at� haver uma quebra de lina, pois
		//se trata de um coment�rio.
		if((char)c == '#') {
			while((char)c != '\n' && (char)c != '\r') {
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//Try Catch
			}//while
			
			//Ap�s a quebra de linha, incrementa o contador de linhas e l�-se mais um caractere.
			this.line++;
			try {
				c = is.read();
			} catch (IOException e) {
				e.printStackTrace();
			}//Try Catch
		}//if

		//Se o caractere for aspas, l� todos os caracteres e os adciona na string at� haver outra aspas.
		if ((char)c == '"') {
			sb.append((char)c);
			try {
				c = is.read();
			} catch (IOException e) {
				e.printStackTrace();
			}//Try Catch
			
			while((char)c != '"') {
				sb.append((char)c);
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//Try Catch
			}//while
			sb.append((char)c);
			
		//Se o caractere n�o for aspas, algumas verifica��es ser�o feitas
		}else if((char)c == '(' || (char)c == ')' || (char)c == ':' || (char)c == '>' || (char)c == '<'){
			//Se o caractere for (, ), :, >, ou <, adciona o caractere na string e faz-se mais verifica��es
			sb.append((char)c);
			
			if((char)c == ':' || (char)c == '>' || (char)c == '<') {
				//Se o caractere for :, > ou <, faz-se uma marca��o e verifica-se o pr�ximo
				is.mark(1);
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//try catch
				
				//Se o pr�ximo for '=', incrementa na string
				if ((char)c == '=') {
					sb.append((char)c);
				}else {
				//Sen�o, reseta para a marca��o anterior
					try {
						is.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}//try catch
				}//else
			}//if
			
		//Se n�o caiu na compara��o de simbolos anterior, l� se caracteres e adciona-os na string at� haver
		//um espa�o ou quebra de linha.
		}else {
			
			while(c != -1 && (char)c != ' ' && (char)c != '\r' && (char)c != '\n') {
				
				firstN = true;
				
				//Se no meio da palavra houver um : ou um ), reseta para a marca��o anterior e quebra o while.
				if((char)c == ':' || (char)c == ')') {
					try {
						is.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}//tryCatch
					break;
				}//if
				
				//Incrementa a string, faz uma nova marca��o e l� o pr�ximo caractere.
				sb.append((char)c);
				is.mark(1);
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//TryCatch
			}//while
			
		}//else
		
	
		//Se o caractere for uma quebra de linha, incrementa as linhas
		if((char)c == '\n' && firstN) {
			this.line++;
		}//if
		
		//Transforma a StringBuilder em uam string
		String str = sb.toString();
		
		//Se o c for -1 (fim de arquivo), retorna uma string escrito "NULL"
		if( c == -1) {
			return "NULL";
		}//if
		
		//Se n�o, retorna a string criada.
		return(str);
	}//LerProxPalavra	
	
}//LeitorArquivo
