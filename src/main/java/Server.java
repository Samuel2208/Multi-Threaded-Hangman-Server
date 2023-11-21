import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	private Integer portNum;
	
	
	Server(Consumer<Serializable> call, Integer port){
	
		callback = call;
		server = new TheServer();
		portNum = port;
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(portNum);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{

			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			HangmanLogic logic;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;

			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}

			public boolean isNumeric(String str) {
				return str.matches("-?\\d+(\\.\\d+)?");  // Handles positive and negative integers/decimals
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
//				updateClients("new client on server: client #"+count);
					
				 while(true) {
					    try {
//							Integer categoryNum = in.readInt();
//							Character letterGuess = in.readChar();
							String data = in.readObject().toString();

							callback.accept("client: " + count + " sent: " + data);

//							//Logic
							if(isNumeric(data)){//category
								callback.accept("Data received was: " + data);
								try {
									int dataInt = Integer.parseInt(data);
									logic = new HangmanLogic(dataInt);
									callback.accept("Secret Word is: " + logic.getSecretWord());
									out.writeObject(logic.getSecretWord().length());
								} catch (NumberFormatException e) {
									callback.accept("Error: Cannot convert the string to int.");
								}

							}else {
								ArrayList<Character> correctLetters = logic.isLetterInWord(data.charAt(0));
								out.writeObject(correctLetters);
								callback.accept("Server: " + portNum+ " sent: " + correctLetters);


							}

//					    	updateClients("client #"+count+" said: "+letterGuess);
					    	
						}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


	
	

	
