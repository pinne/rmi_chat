import java.rmi.*;

interface ChatInterface extends Remote {
	
	public void sendMessage(String s) throws RemoteException;

    /* Called by clients to register for server callbacks
     */
    public void registerForNotification(Notifiable n) throws RemoteException;
    public void deRegisterForNotification(Notifiable n) throws RemoteException;
}
