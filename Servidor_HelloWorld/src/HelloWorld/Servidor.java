package HelloWorld;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

/**
 * Classe principal do Servidor (main)
 *
 */
public class Servidor {

    /**
     * Método main. Aqui é onde está localizado a criação do serviço de nomes
     * e a inicialização da classe servente.
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
