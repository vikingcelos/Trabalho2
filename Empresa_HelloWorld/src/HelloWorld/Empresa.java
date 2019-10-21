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
        
        System.out.println("***********************************************************************************");
        System.out.println("*              Olá! Seja bem-vindo ao serviço de vagas de emprego!                *");
        System.out.println("***********************************************************************************");
        //Menu de selecoes
        while(logado) {
            System.out.println("***********************************************************************************");
            System.out.println("Para utilizar o serviço, selecione uma das opcoes abaixo:");
            System.out.println("Digite 1 para visualizar os curriculos existentes");
            System.out.println("Digite 2 para cadastrar/alterar sua vaga de emprego");
            System.out.println("Digite 3 para registrar interesse em curriculos de uma area");
            escolha = scanner.nextLine();
            System.out.println("***********************************************************************************\n");
            switch (escolha) {
                case "1":
                    System.out.println("Digite a área de interesse para visualizar os currículos correspondentes: ");
                    referenciaServidor.consultaCurriculos(scanner.nextLine(), referenciaEmp);
                    break;
                case "2":
                    System.out.println("Para cadastrar uma vaga, primeiro digite o nome da vaga:");
                    cadastro.setNome(scanner.nextLine());
                    System.out.println("\nAgora digite o email para contato:");
                    cadastro.setContato(scanner.nextLine());
                    System.out.println("\nAgora digite a area da vaga:");
                    cadastro.setArea(scanner.nextLine());
                    System.out.println("\nAgora digite a carga horaria desta vaga:");
                    cadastro.setCargaHoraria(scanner.nextLine());
                    System.out.println("\nAgora digite o salário oferecido (somente numeros): \n");
                    cadastro.setSalario(scanner.nextLine());
                    referenciaServidor.CadastraVaga(cadastro.getNome(), cadastro.getContato(), cadastro.getArea(), cadastro.getCargaHoraria(), cadastro.getSalario(), referenciaEmp);
                    break;
                case "3":
                    System.out.println("Por favor, digite a área de interesse:");
                    referenciaServidor.registraInteresse(referenciaEmp, scanner.nextLine());
                    break;
                default:
                    System.out.println("Comando inválido. Por favor digite novamente.\n");
                    break;
            }
        }
        
        
    }
    
}
