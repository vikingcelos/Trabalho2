package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 *
 */
public class EmpImpl extends UnicastRemoteObject implements InterfaceEmp {

    /**
     * 
     * @throws RemoteException 
     */
    public EmpImpl () throws RemoteException {
        
    }
    
    /**
     * 
     * @param area
     * @throws RemoteException 
     */
    @Override
    public void recebeNotificacao(String notificacao) throws RemoteException {
        System.out.println(notificacao);
    }
    
}
