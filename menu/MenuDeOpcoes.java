package menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import administrativo.Cliente;
import hospedagens.Apartamento;
import repositorio.RepositorioCliente;
import repositorio.RepositorioReservas;
import reservaveis.Hospedagem;
import reservaveis.Reserva;


public class MenuDeOpcoes {
    
    //ao inves de criar uma nova instancia eu só recupero a já criada 
    RepositorioCliente repositorioCliente = RepositorioCliente.getInstance();
    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();
    Hospedagem hospedagem = new Apartamento();

    public void iniciarMenu (){
        int opcao;

        //mostra o menu de opcoes no terminal
        printOpcoes();
        // solicita do usuario a opcao
        //Integer.parseInt() força transformar a string em um inteiro, estamos usando os cases do tipo int
        opcao = Integer.parseInt(getString("opcao: ")); 
        limparConsole();
        
        //enquanto o usuário não digitar 0 (Encerrar) o menu continuará aparecendo e solictando info
        while (opcao != 0) {
            
            switch (opcao) {
                case 1: 
                    Cliente cliente = new Cliente(getString("Nome: "), getString("Cpf (000.000.000 - 00): "), getString("Telefone: (99 99999-9999)"));
                    repositorioCliente.adicionar(cliente);
                    System.out.println("Cadastro realizado com sucesso.");
                    break;

                case 2: 
                    System.out.println(repositorioCliente.listar()); 
                    break;
                
                case 4:
                    boolean respostaSubopcaoCase4 = printReservaveis(Integer.parseInt(getString("opção: \n" + "[1] - Hospedagem \n" + "[2] - Serviços Adicionais\n" + "[0] - Voltar")));
                    //verifica se não foi escolhido a opcao voltar
                    if(respostaSubopcaoCase4){
                        //busca cpf no repositório
                        String cpfCliente = getString("Cpf Cliente (000.000.000 - 00): ");
                        Cliente temCadastro = repositorioCliente.buscar(cpfCliente);
                        
                         //verifica se o cliente possui cadastro
                        if (temCadastro != null) {
                            Reserva reserva = new Reserva(repositorioCliente.buscar(cpfCliente), getString("ID Reservavel: "),converterParaCalendar((getString("Data e Hora de Check-in (dd/MM/yyyy): "))), converterParaCalendar((getString("Data e Hora de Check-out (dd/MM/yyyy HH:mm): "))));
                            hospedagem.reservar(reserva);
                            System.out.println("Reserva realizada com sucesso.");
                        } else {
                            //só permite fazer a reserva se ele já tiver um cadastro
                        
                            System.out.println("Cpf sem cadastro. Por favor, realize o cadastro do cliente antes.");
                        }
                    }
                    break;
                    
                case 5:
                    boolean respostaSubopcaoCase5 = printReservaveis(Integer.parseInt(getString("opção: \n" + "[1] - Hospedagem \n" + "[2] - Serviços Adicionais\n" + "[0] - Voltar")));

                    if(respostaSubopcaoCase5){
                        boolean disponibilidade = hospedagem.verificarDisponibilidade(getString("ID Hospedagem: "), converterParaCalendar(getString("Data e Hora de Check-in (dd/MM/yyyy): ")),converterParaCalendar((getString("Data e Hora de Check-out (dd/MM/yyyy HH:mm): "))));

                        if(disponibilidade){
                            System.out.println("Data disponível para reserva.");
                        } else {
                            System.out.println("Data Indisponível para reserva.");
                        }
                    }
                    break;

                default:
                    break;
            }
            printOpcoes();
            opcao = Integer.parseInt(getString("opcao: "));
            limparConsole();
            
        }
        
    }

    //menu de opcoes
    public void printOpcoes(){
        System.out.println("\n\n[1] - Cadastrar Cliente\n" +
                            "[2] - Listar Clientes\n" +
                            "[3] - Cadastro e gerenciamento de hospedagens e serviços adicionais\n" +
                            "[4] - Realização de reservas de hospedagem e serviços adicionais\n" +
                            "[5] - Consulta de disponibilidade de hospedagem e serviços\n" +
                            "[6] - Cancelamento de reservas\n" +
                            "[7] - Realização de check-in\n" +
                            "[8] - Realização de check-out e fechamento da conta\n" +
                            "[9] - Geração de relatórios\n" +
                            "[0] - Encerrar Atendimento\n\n" );
    }

   //imprime o menu de subopcoes com os IDs dos tipos de hospedagens e serviços adicionais, retorna boolean 
   public boolean printReservaveis(int subopcao){
    
    switch (subopcao) {
        case 1: 
            System.out.println("==============Opções de Hospedagem==============\n" + 
                              "ID \t\t Hospedagem\n\n" +
                              "001\t\t Apartamento\n" +
                              "002\t\t Cabana\n" +
                              "003\t\t Quarto\n");
            return true;
            
        case 2: 
            System.out.println("==============Opções de Serviços Adicionais==============\n" + 
                                "ID \t\t Serviço\n\n" +
                                "111\t\t Passeios Turísticos\n" +
                                "222\t\t Lavanderia\n" +
                                "333\t\t Transfer\n");
            return true;
        case 0:
            System.out.println("Voltando...");
            return false;
        default:
            return false;
    }
   }

    //método para coletar dados do usuário via teclado
    public String getString(String string) {
        Scanner texto = new Scanner(System.in);
        System.out.println("\nEntre com " + string);
        if (texto.hasNextLine()){
            return texto.nextLine();
        }

        String st = texto.next();
        System.out.println("Erro na leitura de dados");
        return "";
    }

    //limpar tela
    public final static void limparConsole() {
        try {
            final String os = System.getProperty("os.name");
        
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recebe uma string como parâmetro e converte em data e hora usando a classe Calendar
    public static Calendar converterParaCalendar(String data){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        //tratamento de exception do tipo: ParseException
        try {
            Date dataConvertida = formatar.parse(data);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataConvertida);
            return calendario;
        } catch (ParseException e) {
            System.out.println("Formato de data ou Hora inválidos. Use dd/MM/yyyy HH:mm !");
        }
        return null;
        
    }

}
