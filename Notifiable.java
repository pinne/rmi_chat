/**
 * Communication Systems, HI1032
 * Lab assignment 4A - Remote Method Invocation
 *
 * Simon Kers          skers@kth.se
 * David Wikmans       wikmans@kth.se
 *                                 KTH STH 2012
 */
import java.rmi.*;

public interface Notifiable extends Remote {
    public void notifyMessage(String m) throws RemoteException;
	public String getNick() throws RemoteException;
	public void setNick(String n) throws RemoteException;
}

