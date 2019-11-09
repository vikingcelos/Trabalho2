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
     * Método de consulta de vagas que printa as vagas relacionadas à area de entrada e que possuem 
     * um salario maior que o valor pretendido recebido na entrada.
     * 
     * @param area
     * @param salarioPretendido
     * @param cliente
     * @throws RemoteException 
     */
    @Override
    public void consultaVagas(String area, String salarioPretendido, InterfaceCli cliente) throws RemoteException {
        Boolean haVagas = false;
        if (vagas.containsKey(area)) {
            for(Cadastro aux: vagas.get(area)){
                if (Double.parseDouble(aux.getSalario()) >= Double.parseDouble(salarioPretendido)){
                    cliente.recebeNotificacao("***********************************************************************************");
                    cliente.recebeNotificacao("\nVaga de: " +aux.getNome()+ "\nContato: " +aux.getContato()+ "\nCarga Horaria requerida: "+ aux.getCargaHoraria()+ "\nSalario oferecido: " +aux.getSalario());
                    cliente.recebeNotificacao("***********************************************************************************\n");
                    haVagas = true;
                }
            }
            if (!haVagas) {
                cliente.recebeNotificacao("Nao há vagas com este salario na area escolhida!");
            }
        }
        else {cliente.recebeNotificacao("Nao há vagas na área escolhida!");}
    }

    /**
     * Método de consulta de currículos que printa os currículos da area
     * recebida como entrada
     * 
     * @param area
     * @param empresa
     * @throws RemoteException 
     */
    @Override
    public void consultaCurriculos(String area, InterfaceEmp empresa) throws RemoteException {
        if (curriculos.containsKey(area)) {
            for(Cadastro aux: curriculos.get(area)){
                empresa.recebeNotificacao("***********************************************************************************");
                empresa.recebeNotificacao("\nCurriculo de: " +aux.getNome()+ "\nContato: " +aux.getContato()+ "\nCarga Horaria disponivel: "+ aux.getCargaHoraria()+ "\nSalario pretendido: " +aux.getSalario());
                empresa.recebeNotificacao("***********************************************************************************\n");
            }
        }
        else {empresa.recebeNotificacao("Nao ha curriculos na area escolhida!");}
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
                // Ja enviei vaga com esse nome, ou seja, quero alterar
                if ((aux.getNome().equalsIgnoreCase(nome)) && (aux.getReferenciaEmp().equals(empresa))) {
                    jaExiste = true;
                    vagas.get(area).remove(aux);
                    vagas.get(area).add(novaVaga);
                    empresa.recebeNotificacao("*Vaga alterada com sucesso!*");
                    break;
                }
            }
            //Se não estou alterando, então é vaga nova
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
                // Ja enviei curriculo nessa area, ou seja, quero alterar
                if (aux.getReferenciaCli().equals(cliente)) {
                    jaExiste = true;
                    curriculos.get(area).remove(aux);
                    curriculos.get(area).add(novoCurriculo);
                    cliente.recebeNotificacao("***********************************************************************************\n");
                    cliente.recebeNotificacao("*                         Curriculo alterado com sucesso!                         *");
                    cliente.recebeNotificacao("***********************************************************************************\n");
                    break;
                }
            }
            if (jaExiste == false) {
                curriculos.get(area).add(novoCurriculo);
                cliente.recebeNotificacao("***********************************************************************************");
                cliente.recebeNotificacao("*                          Currículo criado com sucesso!                          *");
                cliente.recebeNotificacao("***********************************************************************************\n");
            }
        }
        else { //caso nao tem nenhuma vaga nessa area ainda
            curriculos.put(area, new ArrayList<Cadastro>());
            curriculos.get(area).add(novoCurriculo);
            cliente.recebeNotificacao("***********************************************************************************");
            cliente.recebeNotificacao("*                          Currículo criado com sucesso!                          *");
            cliente.recebeNotificacao("***********************************************************************************\n");
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
        if (interesseCli.containsKey(area)) {
            for(InterfaceCli aux: interesseCli.get(area)) {
                aux.recebeNotificacao("\n*Uma nova vaga surgiu na area de " + area + " *");
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
        if (interesseEmp.containsKey(area)) {
            for(InterfaceEmp aux: interesseEmp.get(area)) {
                aux.recebeNotificacao("\n*Um novo curriculo surgiu na area de " + area + " *");
            }
        }
    }
    
}
