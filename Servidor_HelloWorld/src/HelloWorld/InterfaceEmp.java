package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 *
 */
public interface InterfaceEmp extends Remote{
    
    public void recebeNotificacao(String area) throws RemoteException;
    
}
