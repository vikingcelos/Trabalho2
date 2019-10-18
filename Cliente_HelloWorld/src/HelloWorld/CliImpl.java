package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 *
 */
public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
    
    /**
     * 
     * @throws RemoteException 
     */
    public CliImpl() throws RemoteException {
        
    }
    
    /**
     * 
     * @param area 
     */
    @Override
    public void recebeNotificacao (String area) {
        System.out.println("\nUma nova vaga na área de " + area + " acabou de ser aberta!");
    }
    
    
}
