package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface do Cliente.
 *
 */
public interface InterfaceCli extends Remote {
    
    public void recebeNotificacao(String area) throws RemoteException;
    
}
