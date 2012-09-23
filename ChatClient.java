/**
 * Communication Systems, HI1032
 * Lab assignment 4A - Remote Method Invocation
 *
 * Simon Kers          skers@kth.se
 * David Wikmans       wikmans@kth.se
 *                                 KTH STH 2012
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements Notifiable
{
	private ChatInterface chatInterface; // Reference to remote server object
	public String nick = "brittmarie";
	
	public ChatClient(ChatInterface chatInterface) throws RemoteException {
		super();
		this.chatInterface = chatInterface;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String n) {
		this.nick = n;
	}

    public void notifyMessage(String m) throws RemoteException {
		System.out.println(m);
	}

	public static void main(String [] args) {
	   if(args.length < 1) {
	       System.out.println(
	       		"usage: java ChatClient <server_host>");
	       System.exit(0);
	   }
	   
	   try {
	   	   String url = "rmi://" + args[0] + "/rmi_chat";
	       ChatInterface chatInterface = 
	       					(ChatInterface) Naming.lookup(url);
	       ChatClient client = new ChatClient(chatInterface);
	       
	       /* Register for callbacks at the chatInterface server. 
	        */
	       chatInterface.registerForNotification(client);
	       
	       client.runClient();
	    }
	    catch (NotBoundException nbe) {
	    	System.out.println(nbe.toString());
	        System.out.println("rmi_chat is not available");
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	}

	private void runClient() throws RemoteException {
		Scanner scan = new Scanner(System.in);
		char ans;
		String line = "";
		System.out.println("/q to quit");

		while (true) {
			line = scan.nextLine();
			if (line.startsWith("/q")) {
				//chatInterface.sendCommand(line);
				break;
			} else if (line.startsWith("/")) {
				chatInterface.sendCommand(this, line);
			} else {
				chatInterface.sendMessage(this, line);
			}
		}

		System.out.println("Exiting...");
		// Important: Deregister for notifiation from server!
		chatInterface.deRegisterForNotification(this);
		// Terminate the thread associated with rmi calls
		System.exit(0);
	}
}
