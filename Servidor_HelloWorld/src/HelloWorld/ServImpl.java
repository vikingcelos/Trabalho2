/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author a1654861
 */
public class ServImpl extends UnicastRemoteObject implements InterfaceServ {
 
    public ServImpl () throws RemoteException{
        
    }

    @Override
    public void chamar(String qualquer, InterfaceCli cliente) throws RemoteException {
        
    }

    @Override
    public HashMap consultaVagas(String area, String salarioPretendido) throws RemoteException {
        HashMap a = null;
        return a;
    }

    @Override
    public HashMap consultaCurriculos(String area) throws RemoteException {
        HashMap a = null;
        return a;
    }

    @Override
    public void CadastraVaga(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException {
        
    }

    @Override
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException {
        
    }

    @Override
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException {
        
    }

    @Override
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException {
        
    }
    
}
