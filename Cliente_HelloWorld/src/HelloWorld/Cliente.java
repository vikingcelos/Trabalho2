package HelloWorld;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe main() do cliente
 * 
 */
public class Cliente {

    /**
     * Método main do Cliente. Aqui é onde fica o menu de opções e são chamados os métodos remotos
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException{
        
        //Variáveis
        InterfaceServ referenciaServidor = null;
        InterfaceCli referenciaCli = null;
        Boolean logado = true;
        Scanner scanner = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        String escolha = new String();
        String area = new String();
        
        //Conexão com o servidor
        try {
                Registry referenciaServicoNomes = LocateRegistry.getRegistry(2048);
                //lembrar de fazer o cast abaixo!!
                referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("servidor");
                referenciaCli = new CliImpl();
                //lembrar desta excecao abaixo!!
            } catch (RemoteException | NotBoundException ex) { System.out.println(ex) ; }
        
        System.out.println("Ola! Seja bem-vindo ao servico de vagas de emprego!");

        //Menu de selecoes
        while(logado) {
            System.out.println("\nPara utilizar o servico, selecione uma das opcoes abaixo:");
            System.out.println("Digite 1 para visualizar as vagas existentes");
            System.out.println("Digite 2 para cadastrar/alterar seu curriculo");
            System.out.println("Digite 3 para registrar interesse em uma area");
            escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    System.out.println("Digite a area de interesse para as vagas");
                    area = scanner.nextLine();
                    System.out.println("Digite o salario pretendido para visualizar as vagas correspondentes");
                    ArrayList<Cadastro> vagas = null;
                    referenciaServidor.consultaVagas(area, scanner.nextLine(), referenciaCli);
                    break;
                case "2":
                    System.out.println("Para cadastrar um curriculo, Primeiro digite o seu nome:");
                    cadastro.setNome(scanner.nextLine());
                    System.out.println("Agora digite o email para contato:");
                    cadastro.setContato(scanner.nextLine());
                    System.out.println("Agora digite a area de aplicacao");
                    cadastro.setArea(scanner.nextLine());
                    System.out.println("Agora digite a carga horaria pretendida");
                    cadastro.setCargaHoraria(scanner.nextLine());
                    System.out.println("Agora digite o salario pretendido (somente numeros)");
                    cadastro.setSalario(scanner.nextLine());
                    referenciaServidor.CadastraCurriculo(cadastro.getNome(), cadastro.getContato(), cadastro.getArea(), cadastro.getCargaHoraria(), cadastro.getSalario(), referenciaCli);
                    break;
                case "3":
                    System.out.println("Por favor, digite a area de interesse:");
                    referenciaServidor.registraInteresse(referenciaCli, scanner.nextLine());
                    break;
                default:
                    System.out.println("Comando invalido. Por favor digite novamente.\n");
                    break;
            }
        }
        
        
    }
    
}
