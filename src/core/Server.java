package core;

import javax.net.ssl.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		System.setProperty("javax.net.ssl.keyStore", System.getProperty("user.home") + File.separator + ".keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "rootroot");
		SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket ss = (SSLServerSocket) factory.createServerSocket(8888); // Create the ServerSocket

		try {
			System.out.println("Vald:");
			for (int i = 0; i < ss.getEnabledCipherSuites().length; i++)
				System.out.println(ss.getEnabledCipherSuites()[i]);

			SSLSocket s = (SSLSocket) ss.accept();

			System.out.println("client connected!");
			
			BufferedWriter outfile =new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			outfile.write("Hello World");
			outfile.flush();
			outfile.close();
			s.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}