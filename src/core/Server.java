package core;

import javax.net.ssl.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		
		System.out.println("Stšder:");
		for (int i = 0; i < ssf.getSupportedCipherSuites().length; i++)
			System.out.println(ssf.getSupportedCipherSuites()[i]);
		
		SSLServerSocket ss = null;
		try {
			ss = (SSLServerSocket) ssf.createServerSocket(8888);
			String[] cipher = { "SSL_DH_anon_WITH_RC4_128_MD5" };
			ss.setEnabledCipherSuites(cipher);
			
			System.out.println("Vald:");
			for (int i = 0; i < ss.getEnabledCipherSuites().length; i++)
				System.out.println(ss.getEnabledCipherSuites()[i]);
			
			SSLSocket s = (SSLSocket) ss.accept();
			BufferedReader infil = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String rad = null;
			while ((rad = infil.readLine()) != null)
				System.out.println(rad);
			
			infil.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}