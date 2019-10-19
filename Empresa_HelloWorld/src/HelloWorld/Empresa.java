package HelloWorld;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe main da Empresa
 * 
 */
public class Empresa {

    /**
     * Método main da empresa. Aqui é onde está o menu de seleção e os métodos remotos são chamados
     */
    public static void main(String[] args) throws RemoteException{
        
        //Variáveis
        InterfaceServ referenciaServidor = null;
        InterfaceEmp referenciaEmp = null;
        Boolean logado = true;
        Scanner scanner = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        String escolha = new String();
        
        //Conexão com o servidor
        try {
                Registry referenciaServicoNomes = LocateRegistry.getRegistry(2048);
                //lembrar de fazer o cast abaixo!!
                referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("servidor");
                referenciaEmp = new EmpImpl();
                //lembrar desta excecao abaixo!!
            } catch (RemoteException | NotBoundException ex) { System.out.println(ex) ; }
        
        System.out.println("Ola! Seja bem-vindo ao servico de vagas de emprego!");

        //Menu de selecoes
        while(logado) {
            System.out.println("\nPara utilizar o servico, selecione uma das opcoes abaixo:");
            System.out.println("Digite 1 para visualizar os curriculos existentes");
            System.out.println("Digite 2 para cadastrar/alterar sua vaga de emprego");
            System.out.println("Digite 3 para registrar interesse em curriculos de uma area");
            escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    System.out.println("Digite a area de interesse para visualizar os curriculos correspondentes");
                    referenciaServidor.consultaCurriculos(scanner.nextLine(), referenciaEmp);
                    break;
                case "2":
                    System.out.println("Para cadastrar uma vaga, Primeiro digite o nome da vaga:");
                    cadastro.setNome(scanner.nextLine());
                    System.out.println("Agora digite o email para contato:");
                    cadastro.setContato(scanner.nextLine());
                    System.out.println("Agora digite a area da vaga");
                    cadastro.setArea(scanner.nextLine());
                    System.out.println("Agora digite a carga horaria desta vaga");
                    cadastro.setCargaHoraria(scanner.nextLine());
                    System.out.println("Agora digite o salario oferecido (somente numeros)");
                    cadastro.setSalario(scanner.nextLine());
                    referenciaServidor.CadastraVaga(cadastro.getNome(), cadastro.getContato(), cadastro.getArea(), cadastro.getCargaHoraria(), cadastro.getSalario(), referenciaEmp);
                    break;
                case "3":
                    System.out.println("Por favor, digite a area de interesse:");
                    referenciaServidor.registraInteresse(referenciaEmp, scanner.nextLine());
                    break;
                default:
                    System.out.println("Comando invalido. Por favor digite novamente.\n");
                    break;
            }
        }
        
        
    }
    
}
