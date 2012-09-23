import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;

public class Server extends UnicastRemoteObject implements ChatInterface
{
	/* The list of regisered clients */
    private ArrayList<Notifiable> clientList = null;

    public Server() throws RemoteException {
    	super();
		clientList = new ArrayList<Notifiable>();
    }

	synchronized public void sendMessage(String s) throws RemoteException {
		// Replace <client> with actual nick from Notifiable.
		s = "<client> " + s;
		for (Notifiable n : clientList) {
			n.notifyMessage(s);
		}
	}

	/* Called by clients to register for server callbacks
     */
    synchronized public void registerForNotification(Notifiable n) throws RemoteException {
		clientList.add(n);
    }
    
    synchronized public void deRegisterForNotification(Notifiable n) throws RemoteException {
		clientList.remove(n);     	
    }

    public static void main(String [] args) {
        try {
            Server server = new Server();
            Naming.rebind("rmi_chat", server);
            System.out.println("Chat server running...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
