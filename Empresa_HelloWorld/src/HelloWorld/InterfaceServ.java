/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author a1654861
 */
public interface InterfaceServ extends Remote {
    
    public void chamar(String qualquer, InterfaceCli cliente) throws RemoteException;
    
    public HashMap consultaVagas(String area, String salarioPretendido) throws RemoteException;
    
    public HashMap consultaCurriculos(String area) throws RemoteException;
    
    public void CadastraVaga(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException;
    
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException;
    
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException;
    
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException;
    
}
