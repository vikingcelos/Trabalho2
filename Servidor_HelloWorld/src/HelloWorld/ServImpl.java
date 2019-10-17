package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 *
 *
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
                if (Double.parseDouble(aux.getSalario()) > Double.parseDouble(salarioPretendido)){
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
        checaInteressadosVaga(area);
    }

    @Override
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario) throws RemoteException {
        Cadastro novoCurriculo = new Cadastro(nome, contato, area, cargaHoraria, salario);
        // SE EU JA CADASTREI O MEU CURRICULO, DESTA VEZ EU IREI ALTERÁ-LO. PORTANTO SERIA ALGO COMO REMOVER O EXISTENTE E BOTAR UM NOVO?
        // TEMOS QUE OLHAR ISSO AQUI, PEDRO
        if (curriculos.containsKey(area)) {
            curriculos.get(area).add(novoCurriculo);
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            curriculos.put(area, new ArrayList<Cadastro>());
            curriculos.get(area).add(novoCurriculo);
        }
        //notifica a empresa da existencia de um novo curriculo na area
    }

    @Override
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException {
        // VERIFICAR SE O CLIENTE JA ESTA REGISTRADO NESTA AREA. (neste caso so nao acntece nada_)
        if (interesseCli.containsKey(area)) {
            interesseCli.get(area).add(cliente);
        }
        else {
            interesseCli.put(area, new ArrayList<InterfaceCli>());
            
        }
    }

    @Override
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException {
        //VERIFICAR SE A EMPRESA JA ESTA REGISTRADA NESSA AREA (neste caso so n acontece nada)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Aqui ficam os metodos nao chamaveis
    
    private void checaInteressadosVaga(String area) throws RemoteException {
        ArrayList<InterfaceCli> listaInteressados = interesseCli.get(area);
        for(InterfaceCli aux: listaInteressados) {
            aux.recebeNotificacao(area);
        }
    }
    
    private void checaInteressadosCurriculo(String area) throws RemoteException {
        ArrayList<InterfaceEmp> listaInteressados = interesseEmp.get(area);
        for(InterfaceEmp aux: listaInteressados) {
            aux.recebeNotificacao(area);
        }
    }
    
}
