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
public class EmpImpl extends UnicastRemoteObject implements InterfaceEmp {

    public EmpImpl () throws RemoteException {
        
    }
    
    @Override
    public void recebeNotificacao(String notificacao) throws RemoteException {
        
    }
    
}
