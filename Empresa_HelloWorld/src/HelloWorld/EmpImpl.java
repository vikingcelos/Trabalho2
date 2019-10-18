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
        //cara... tem uma parada aqui que é assim:
        //podemos usar o Emp/Cli Impl pra chamar os metodos remotos..
        //Ou fazer isso dentro do main()... Eu acho que tanto faz a maneira que sera feita..
        //O que muda é que o menuzinho vem aqui pra dentro, eu acho? talvez n
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
