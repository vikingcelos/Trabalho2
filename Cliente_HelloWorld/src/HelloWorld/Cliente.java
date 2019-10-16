/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1654861
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        InterfaceServ referenciaServidor = null;
        
        
        try {
            Registry referenciaServicoNomes = LocateRegistry.getRegistry(2048);
            //lembrar de fazer o cast abaixo!!
            referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("servidor");
            InterfaceCli referenciaCli = new CliImpl(referenciaServidor);
            //lembrar desta excecao abaixo!!
          
            referenciaServidor.chamar("tomato potato", referenciaCli);
        } catch (RemoteException | NotBoundException ex) { System.out.println(ex) ; }
        
        
        
    }
    
}
