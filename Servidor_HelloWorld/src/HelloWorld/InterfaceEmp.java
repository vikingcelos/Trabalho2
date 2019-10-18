package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface da Empresa.
 *
 */
public interface InterfaceEmp extends Remote{
    
    public void recebeNotificacao(String area) throws RemoteException;
    
}
