package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe servente do Servidor
 *
 */
public class ServImpl extends UnicastRemoteObject implements InterfaceServ {
 
    // Hashmaps que guardam as listas
    private HashMap<String,ArrayList<Cadastro>> vagas;
    private HashMap<String,ArrayList<Cadastro>> curriculos;
    private HashMap<String,ArrayList<InterfaceEmp>> interesseEmp;
    private HashMap<String,ArrayList<InterfaceCli>> interesseCli;

    /**
     * Método construtor. Inicializa os hashmaps
     * @throws RemoteException 
     */
    public ServImpl () throws RemoteException{
        vagas = new HashMap<String,ArrayList<Cadastro>>();
        curriculos = new HashMap<String,ArrayList<Cadastro>>();
        interesseEmp = new HashMap<String,ArrayList<InterfaceEmp>>();
        interesseCli = new HashMap<String,ArrayList<InterfaceCli>>();
    }

    /**
     * Método de consulta de vagas que retorna um ArrayList de vagas relacionadas a area de entrada e que possuem 
     * um salario maior que o valor pretendido recebido na entrada.
     * 
     * @param area
     * @param salarioPretendido
     * @return lista de 'Cadastro' com vagas da area que tem salario acima do pretendido, ou Null caso nenhuma vaga esteja cadastrada nessa area
     * @throws RemoteException 
     */
    @Override
    public ArrayList consultaVagas(String area, String salarioPretendido) throws RemoteException {
        ArrayList<Cadastro> vagasFiltradas = new ArrayList<Cadastro>();
        if (vagas.containsKey(area)) {
            for(Cadastro aux: vagas.get(area)){
                if (Double.parseDouble(aux.getSalario()) >= Double.parseDouble(salarioPretendido)){
                    vagasFiltradas.add(aux);
                }
            }
            return(vagasFiltradas);
        }
        else {return null;}
    }

    /**
     * Método de consulta de currículos que retorna um ArrayList com os currículos da area
     * recebida como entrada
     * 
     * @param area
     * @return lista com curriculos da area recebida como entrada, ou Null caso não tenha nenhum curriculo cadastrado nesta area
     * @throws RemoteException 
     */
    @Override
    public ArrayList consultaCurriculos(String area) throws RemoteException {
        //ArrayList<Cadastro> curriculosFiltrados = new ArrayList<Cadastro>();
        if (curriculos.containsKey(area)) {
            //A seguir, atribuicao simples para copiar o array. Aqui utilizaremos fora, mas por enquanto eu botei direto o do HashMap. Podem haver implicacoes!!
            //curriculosFiltrados = vagas.get(area);
            return(vagas.get(area));
        }
        else {return null;} // return curriculosFiltrados
    }

    /**
     * Método que insere/altera uma vaga no HashMap de vagas.Instancia um objeto Cadastro e então o adiciona no HashMap.
     * Caso já exista um, altera o existente.
 Após este processo, envia uma notificação para os clientes interessados
 em vagas nesta área.
     * 
     * @param nome
     * @param contato
     * @param area
     * @param cargaHoraria
     * @param salario
     * @param empresa
     * @throws RemoteException 
     */
    @Override
    public void CadastraVaga(String nome, String contato, String area, String cargaHoraria, String salario, InterfaceEmp empresa) throws RemoteException {
        Boolean jaExiste = false;
        Cadastro novaVaga = new Cadastro(nome, contato, area, cargaHoraria, salario, empresa);
        if (vagas.containsKey(area)) {
            for(Cadastro aux : vagas.get(area)) {
                if ((aux.getNome().equalsIgnoreCase(nome)) && (aux.getReferenciaEmp().equals(empresa))) {
                    jaExiste = true;
                    vagas.get(area).remove(aux);
                    vagas.get(area).add(novaVaga);
                    empresa.recebeNotificacao("*Vaga alterada com sucesso!*");
                    break;
                }
            }
            if (jaExiste == false) {
                vagas.get(area).add(novaVaga);
                empresa.recebeNotificacao("*Vaga criada com sucesso!*");
            }
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            vagas.put(area, new ArrayList<Cadastro>());
            vagas.get(area).add(novaVaga);
            empresa.recebeNotificacao("*Vaga criada com sucesso!*");
        }
        checaInteressadosVaga(area);
    }

    /**
     * Método que insere/altera um curriculo no HashMap de curriculos.Instancia um objeto Cadastro e então o adiciona no HashMap.
     * Caso já exista um, altera o existente.
     * Após este processo, envia uma notificação para as empresas interessadas
     * em currículos nesta área.
     * 
     * @param nome
     * @param contato
     * @param area
     * @param cargaHoraria
     * @param salario
     * @param cliente
     * @throws RemoteException 
     */
    @Override
    public void CadastraCurriculo(String nome, String contato, String area, String cargaHoraria, String salario, InterfaceCli cliente) throws RemoteException {
        Boolean jaExiste = false;
        Cadastro novoCurriculo = new Cadastro(nome, contato, area, cargaHoraria, salario, cliente);
        if (curriculos.containsKey(area)) {
            for(Cadastro aux: curriculos.get(area)) {
                if (aux.getReferenciaCli().equals(cliente)) {
                    jaExiste = true;
                    curriculos.get(area).remove(aux);
                    curriculos.get(area).add(novoCurriculo);
                    cliente.recebeNotificacao("*Curriculo alterado com sucesso!*");
                    break;
                }
            }
            if (jaExiste == false) {
                curriculos.get(area).add(novoCurriculo);
                cliente.recebeNotificacao("*Curriculo criado com sucesso!*");
            }
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            curriculos.put(area, new ArrayList<Cadastro>());
            curriculos.get(area).add(novoCurriculo);
            cliente.recebeNotificacao("*Curriculo criado com sucesso!*");
        }
        checaInteressadosCurriculo(area);
    }

    /**
     * Método que insere o cliente em uma lista de notificacao
     * para novas vagas na area de interesse.
     * 
     * @param cliente a referencia do cliente
     * @param area a area de interesse
     * @throws RemoteException 
     */
    @Override
    public void registraInteresse(InterfaceCli cliente, String area) throws RemoteException {
        if (interesseCli.containsKey(area)) {
            if (!interesseCli.get(area).contains(cliente)) {
                interesseCli.get(area).add(cliente);
            }
            else {
                cliente.recebeNotificacao("*Voce ja esta cadastrado para receber notificacoes desta area*");
            }
        }
        else {
            interesseCli.put(area, new ArrayList<InterfaceCli>());
            interesseCli.get(area).add(cliente);
        }
    }

    /**
     * Método que insere a empresa em uma lista de notificacao
     * para novos curriculos na area de interesse.
     * 
     * @param empresa a referencia da empresa
     * @param area a area de interesse
     * @throws RemoteException 
     */
    @Override
    public void registraInteresse(InterfaceEmp empresa, String area) throws RemoteException {
        if (interesseEmp.containsKey(area)) {
            if (!interesseEmp.get(area).contains(empresa)) {
                interesseEmp.get(area).add(empresa);
            }
            else {
                empresa.recebeNotificacao("*Voce ja esta cadastrado para receber notificacoes desta area*");
            }
        }
        else {
            interesseEmp.put(area, new ArrayList<InterfaceEmp>());
            interesseEmp.get(area).add(empresa);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Aqui ficam os metodos nao chamaveis
    
    /**
     * Método auxiliar que verifica a lista de interessados em vagas da área
     * de entrada e chama o método de notificação deste interessado(cliente)
     * 
     * @param area
     * @throws RemoteException 
     */
    private void checaInteressadosVaga(String area) throws RemoteException {
        //ArrayList<InterfaceCli> listaInteressados = interesseCli.get(area);
        if (interesseCli.containsKey(area)) {
            for(InterfaceCli aux: interesseCli.get(area)) {
                aux.recebeNotificacao("*Uma nova vaga surgiu na area de " + area + " *");
            }
        }
    }
    
    /**
     * Método auxiliar que verifica a lista de interessados em currículos da área
     * de entrada e chama o método de notificação deste interessado(empresa)
     * 
     * @param area
     * @throws RemoteException 
     */
    private void checaInteressadosCurriculo(String area) throws RemoteException {
        //ArrayList<InterfaceEmp> listaInteressados = interesseEmp.get(area);
        if (interesseEmp.containsKey(area)) {
            for(InterfaceEmp aux: interesseEmp.get(area)) {
                aux.recebeNotificacao("*Um novo curriculo surgiu na area de " + area + " *");
            }
        }
    }
    
}
