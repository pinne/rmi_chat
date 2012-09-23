import java.rmi.*;

public interface Notifiable extends Remote {
	
    public void notifyMessage(String m) throws RemoteException;
}
