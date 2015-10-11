package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JustAnotherServer {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("Usage: java JustAnotherServer <port number>");
			System.exit(1);
		}
		System.out.println("Server started. Listening on Port 8010");
		int portNumber = Integer.parseInt(args[0]);
		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			System.out.println("Client connected on port " + portNumber + ". Servicing requests.");
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if(inputLine.equalsIgnoreCase("exit app")){
					System.out.println("Exiting app");
					System.exit(1);
				}
				System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
				out.println(inputLine);
			}
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
		}
	}
}
