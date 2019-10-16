/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1654861
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Registry referenciaServicoNomes = LocateRegistry.createRegistry(2048);
            InterfaceServ implServ = new ServImpl();
            referenciaServicoNomes.bind("servidor", implServ);
        } catch (AlreadyBoundException | RemoteException ex)
        { System.out.println(ex); }
        
    }
    
}
