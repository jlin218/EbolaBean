package model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Server started");
		ServerSocket serverSocket = new ServerSocket(9999);
		Socket socket = serverSocket.accept();
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		Object obj = input.readObject();
		input.close();
		
		System.out.println(obj);
		
	}
}
