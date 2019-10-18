package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface do Servidor.
 *
 */
public interface InterfaceServ extends Remote {
    
    public ArrayList consultaVagas(String area, String salarioPretendido) throws RemoteException;
    
    public ArrayList consultaCurriculos(String area) throws RemoteException;
    
    public void CadastraVaga(String nome, String contato, String area, String cargaHoraria, String salario, InterfaceEmp empresa) throws RemoteException;
    
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario, InterfaceCli cliente) throws RemoteException;
    
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException;
    
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException;
    
}
