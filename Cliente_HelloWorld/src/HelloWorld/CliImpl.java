/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author a1654861
 */
public class CliImpl extends UnicastRemoteObject implements InterfaceCli {

    private InterfaceServ referenciaservidor;
    
    public CliImpl(InterfaceServ refserv) throws RemoteException {
        referenciaservidor = refserv;
        referenciaservidor.chamar("batata", this);
    }
    
    @Override
    public void recebeNotificacao (String notificacao) {
        System.out.println(notificacao);
    }
    
    
}
