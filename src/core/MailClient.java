package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class MailClient {
	public MailClient() throws UnknownHostException, IOException {
		run1();
	}
	
	private void run1() throws UnknownHostException, IOException {
		System.setProperty("javax.net.ssl.keyStore", System.getProperty("user.home") + File.separator + ".keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "rootroot");
		SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
		Socket socket = sslSocketFactory.createSocket("mail1.nada.kth.se", 995);

		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

		out.println("GET / HTTP/1.0");
		out.println();
		out.flush();

		if (out.checkError())
			System.out.println("SSLSocketClient:  java.io.PrintWriter error");

		/* read response */
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);

		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new MailClient();
	}

}
