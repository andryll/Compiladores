import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LeitorArquivo {
	
	//Declarando variáveis
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
	
	
	//Função para ler próximo caractere (não utilizada)
	public int LerProxCaractere() {
		int c = -1;
		try {
			c = is.read();		
		} catch (IOException e) {
			e.printStackTrace();
		}//TryCatch
		return(c);
	}//LerProxCaractere
	
	
	//Função para ler a próxima palavra
	public String LerProxPalavra() {
		//Variável de controle pra verificar contador de linha
		boolean firstN = true;
		int c = -1;
		//StringBuilder pra ir criando a palavra com os caracteres
		StringBuilder sb = new StringBuilder();
		
		//Verificando se é possível utilizar o mark reset da BufferedInputStream
		if(!is.markSupported()) {
			System.out.println("Mark nao suportado");
			System.exit(0);
		}//if
		
		//Cirando um ponto de marcação
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

		//Verificando se o caractere é #, se for, lê todos os caraceteres até haver uma quebra de lina, pois
		//se trata de um comentário.
		if((char)c == '#') {
			while((char)c != '\n' && (char)c != '\r') {
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//Try Catch
			}//while
			
			//Após a quebra de linha, incrementa o contador de linhas e lê-se mais um caractere.
			this.line++;
			try {
				c = is.read();
			} catch (IOException e) {
				e.printStackTrace();
			}//Try Catch
		}//if

		//Se o caractere for aspas, lê todos os caracteres e os adciona na string até haver outra aspas.
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
			
		//Se o caractere não for aspas, algumas verificações serão feitas
		}else if((char)c == '(' || (char)c == ')' || (char)c == ':' || (char)c == '>' || (char)c == '<'){
			//Se o caractere for (, ), :, >, ou <, adciona o caractere na string e faz-se mais verificações
			sb.append((char)c);
			
			if((char)c == ':' || (char)c == '>' || (char)c == '<') {
				//Se o caractere for :, > ou <, faz-se uma marcação e verifica-se o próximo
				is.mark(1);
				try {
					c = is.read();
				} catch (IOException e) {
					e.printStackTrace();
				}//try catch
				
				//Se o próximo for '=', incrementa na string
				if ((char)c == '=') {
					sb.append((char)c);
				}else {
				//Senão, reseta para a marcação anterior
					try {
						is.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}//try catch
				}//else
			}//if
			
		//Se não caiu na comparação de simbolos anterior, lê se caracteres e adciona-os na string até haver
		//um espaço ou quebra de linha.
		}else {
			
			while(c != -1 && (char)c != ' ' && (char)c != '\r' && (char)c != '\n') {
				
				firstN = true;
				
				//Se no meio da palavra houver um : ou um ), reseta para a marcação anterior e quebra o while.
				if((char)c == ':' || (char)c == ')') {
					try {
						is.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}//tryCatch
					break;
				}//if
				
				//Incrementa a string, faz uma nova marcação e lê o próximo caractere.
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
		
		//Se não, retorna a string criada.
		return(str);
	}//LerProxPalavra	
	
}//LeitorArquivo
