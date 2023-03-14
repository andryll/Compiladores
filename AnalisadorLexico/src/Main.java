
public class Main {

	public static void main (String[] args) {
		LeitorArquivo ldat = new LeitorArquivo(args[0]);
		int c;
		
		System.out.println("\nNome Arquivo: " + args[0] + "\n");
		
		while((c = ldat.LerProxCaractere()) != -1) {
			System.out.print((char) c);
		}//while
		
	}//main
	
}//Main
