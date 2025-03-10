package menu;

import java.util.Scanner;
import administrativo.Cliente;
import administrativo.Funcionario;
import repositorio.RepositorioCliente;
import repositorio.RepositorioFuncionario;

public class MenuDeOpcoes {
    
    //ao inves de criar uma nova instancia eu só recupero a já criada 
    RepositorioCliente repositorioCliente = RepositorioCliente.getInstance(); 
    RepositorioFuncionario repositorioFuncionario = RepositorioFuncionario.getInstance();
    

    public void iniciarMenu (){
        int opcao;
        
        //mostra o menu de opcoes no terminal
        printOpcoes();
        // solicita do usuario a opcao
        //Integer.parseInt() força transformar a string em um inteiro, estamos usando os cases do tipo int
        opcao = Integer.parseInt(getString("opcao")); 
        
        //enquanto o usuário n digitar 0 (Encerrar) o menu continuara aparecendo e solictando info
        while (opcao != 0) {
            
            switch (opcao) {
                case 1: 
                    Funcionario funcionario = new Funcionario(Integer.parseInt(getString("Id: ")), getString("Nome: "), getString("Cargo: "));
                    repositorioFuncionario.adicionarFuncionario(funcionario);
                    break;

                case 2: 
                    System.out.println(repositorioFuncionario.listaFuncionario()); 
                    break;
            
                default:
                    break;
            }
            printOpcoes();
            opcao = Integer.parseInt(getString("opcao"));
        }
        
    }

    //menu de opcoes
    public void printOpcoes(){
        System.out.println("\n[1] - Cadastrar Funcionário\n" +
                            "[2] - Listar Funcionários\n");
    }

    //método para coletar dados do usuário via teclado
    public String getString(String string) {
        Scanner texto = new Scanner(System.in);
        System.out.println("Entre com " + string);
        if (texto.hasNextLine()){
            return texto.nextLine();
        }

        String st = texto.next();
        System.out.println("Erro na leitura de dados");
        return "";
    }

    

}
