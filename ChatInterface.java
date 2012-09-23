/**
 * Communication Systems, HI1032
 * Lab assignment 4A - Remote Method Invocation
 *
 * Simon Kers          skers@kth.se
 * David Wikmans       wikmans@kth.se
 *                                 KTH STH 2012
 */
import java.rmi.*;

interface ChatInterface extends Remote {
	
	public void sendMessage(Notifiable n, String s) throws RemoteException;
	public void sendCommand(Notifiable n, String s) throws RemoteException;

    /* Called by clients to register for server callbacks
     */
    public void registerForNotification(Notifiable n) throws RemoteException;
    public void deRegisterForNotification(Notifiable n) throws RemoteException;
}
