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
 * 
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        //Variáveis
        InterfaceServ referenciaServidor = null;
        Boolean logado = true;
        
        //Conexão com o servidor
        try {
                Registry referenciaServicoNomes = LocateRegistry.getRegistry(2048);
                //lembrar de fazer o cast abaixo!!
                referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("servidor");
                InterfaceCli referenciaCli = new CliImpl();
                //lembrar desta excecao abaixo!!
            } catch (RemoteException | NotBoundException ex) { System.out.println(ex) ; }
        
        System.out.println("Ola! Seja bem-vindo ao servico de vagas!");

        //Menu de selecoes
        while(logado) {
            System.out.println("Para utilizar o servico, selecione uma das opcoes abaixo:");
            System.out.println("Digite 1 para visualizar as vagas existentes");
            System.out.println("Digite 2 para cadastrar/alterar seu curriculo");
            System.out.println("Digite 3 para registrar interesse em uma area");
            System.out.println("Digite 4 para sair?");
            //fazer um scanner e entao pegar os inputs referentes às escolhas acima.
            //Aí colocar os ifs para chamar os métodos remotos.
            //Por enquanto nao existe nada pra sair ou "deslogar"
        }
        
        
    }
    
}
