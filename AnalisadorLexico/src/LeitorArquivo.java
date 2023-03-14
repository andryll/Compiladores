import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class LeitorArquivo {
	public InputStream is;
	
	public LeitorArquivo(String name) {
		try {
			is = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//TryCatch
	}//LeitorArquivo
	
	public int LerProxCaractere() {
		int c = -1;
		try {
			c = is.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//TryCatch
		return(c);
	}//LerProxCaractere
	

	
}//class
