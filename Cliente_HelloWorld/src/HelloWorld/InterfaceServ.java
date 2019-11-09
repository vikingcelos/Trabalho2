package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface do Servidor.
 *
 */
public interface InterfaceServ extends Remote {
    
    public void consultaVagas(String area, String salarioPretendido, InterfaceCli cliente) throws RemoteException;
  
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario, InterfaceCli cliente) throws RemoteException;
    
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException;
      
}
