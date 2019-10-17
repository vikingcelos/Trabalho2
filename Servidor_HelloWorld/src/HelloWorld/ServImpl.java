/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 *
 * @author a1654861
 */
public class ServImpl extends UnicastRemoteObject implements InterfaceServ {
 
    private HashMap<String,ArrayList<Cadastro>> vagas;
    private HashMap<String,ArrayList<Cadastro>> curriculos;
    private HashMap<String,ArrayList<InterfaceEmp>> interesseEmp;
    private HashMap<String,ArrayList<InterfaceCli>> interesseCli;

    public ServImpl () throws RemoteException{
        vagas = new HashMap<String,ArrayList<Cadastro>>();
        curriculos = new HashMap<String,ArrayList<Cadastro>>();
    }

    @Override
    public ArrayList consultaVagas(String area, String salarioPretendido) throws RemoteException {
        ArrayList<Cadastro> vagasDaArea;
        ArrayList<Cadastro> vagasFiltradas = new ArrayList<Cadastro>();
        if (vagas.containsKey(area)) {
            //A seguir, atribuicao simples para copiar o array. Como nao utilizamos fora daqui, nao tem problema
            vagasDaArea = vagas.get(area);
            for(Cadastro aux: vagasDaArea){
                if (aux.getSalario() > Double.parseDouble(salarioPretendido)){
                    vagasFiltradas.add(aux);
                }
            }
            return(vagasFiltradas);
        }
        else {return null;}
    }

    @Override
    public ArrayList consultaCurriculos(String area) throws RemoteException {
        //ArrayList<Cadastro> curriculosFiltrados = new ArrayList<Cadastro>();
        if (curriculos.containsKey(area)) {
            //A seguir, atribuicao simples para copiar o array. Aqui utilizaremos fora, mas por enquanto eu botei direto o do HashMap
            //curriculosFiltrados = vagas.get(area);
            return(vagas.get(area));
        }
        else {return null;}
    }

    @Override
    public void CadastraVaga(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException {
        Cadastro novaVaga = new Cadastro(nome, contato, area, cargaHoraria, salario);
        if (vagas.containsKey(area)) {
            vagas.get(area).add(novaVaga);
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            vagas.put(area, new ArrayList<Cadastro>());
            vagas.get(area).add(novaVaga);
        }
        //notifica alguem
    }

    @Override
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException {
        Cadastro novoCurriculo = new Cadastro(nome, contato, area, cargaHoraria, salario);
        if (curriculos.containsKey(area)) {
            curriculos.get(area).add(novoCurriculo);
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            curriculos.put(area, new ArrayList<Cadastro>());
            curriculos.get(area).add(novoCurriculo);
        }
        //notifica alguem
    }

    @Override
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException {
        if (interesseCli.containsKey(area)) {
            interesseCli.get(area).add(cliente);
        }
        else {
            interesseCli.put(area, new ArrayList<InterfaceCli>());
            
        }
    }

    @Override
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException {
        
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Aqui ficam os metodos nao chamaveis
    
}
