package menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Exceptions.DataInvalidaException;
import Exceptions.ReservaNaoEncontradaException;
import Exceptions.ServicoNaoPermitidoException;
import administrativo.Cliente;
import hospedagens.Apartamento;
import relatorios.RelatorioReservas;
import repositorio.RepositorioCliente;
import repositorio.RepositorioReservas;
import reservaveis.Hospedagem;
import reservaveis.Reserva;
import reservaveis.ServicosAdicionais;
import servicos.PasseiosTuristicos; 

public class MenuDeOpcoes {
    
    //ao inves de criar uma nova instancia eu só recupero a já criada 
    RepositorioCliente repositorioCliente = RepositorioCliente.getInstance();
    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();
    Hospedagem hospedagem = new Apartamento();
    ServicosAdicionais servicosAdicionais = new PasseiosTuristicos();
    RelatorioReservas relatorios = new RelatorioReservas();
    

    public void iniciarMenu () {
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
                    int respostaSubopcaoCase4 = printReservaveis(Integer.parseInt(getString("opção: \n" + "[1] - Hospedagem \n" + "[2] - Serviços Adicionais\n" + "[0] - Voltar")));
                    //verifica se não foi escolhido a opcao voltar
                    if(respostaSubopcaoCase4 == 1){
                        //busca cpf no repositório
                        String cpfCliente = getString("Cpf Cliente (000.000.000 - 00): ");
                        Cliente temCadastro = repositorioCliente.buscar(cpfCliente);
                        
                        //verifica se o cliente possui cadastro
                        if (temCadastro != null) {
                            String idHosp;
                            do {
                                idHosp = getString("ID Hospedagem (001, 002 ou 003): ");
                                if (!idHosp.equals("001") && !idHosp.equals("002") && !idHosp.equals("003")) {
                                    System.out.println("ID de hospedagem inválido! Por favor, digite 001, 002 ou 033.");
                                } 
                                limparConsole();
                            } while (!idHosp.equals("001") && !idHosp.equals("002") && !idHosp.equals("003"));
                                
                            //tratamento de execessão do formato da data
                            try {
                                Reserva reserva = new Reserva(repositorioCliente.buscar(cpfCliente), idHosp ,converterParaCalendar((getString("Data e Hora de Check-in (dd/MM/yyyy): "))), converterParaCalendar((getString("Data e Hora de Check-out (dd/MM/yyyy HH:mm): "))));

                                //armazena o retorno do metodo reservar
                                boolean reservaConcluida = hospedagem.reservar(reserva);
                                if(reservaConcluida){
                                    System.out.println("Reserva realizada com sucesso.");
                                } else {
                                    System.out.println("Dia indisponivel"); 
                                }

                            } catch (DataInvalidaException e){
                                System.out.println(e.getMessage());
                            }
                        } else {
                            //só permite fazer a reserva se ele já tiver um cadastro
                            System.out.println("Cpf sem cadastro. Por favor, realize o cadastro do cliente antes.");
                        }
                        break;
                    } else if (respostaSubopcaoCase4 == 2){
                        String idReserva = getString("ID da Reserva: ");
                        Reserva reservaEncontrada;
                        try {
                            reservaEncontrada = repositorioReservas.buscar(idReserva);
                    
                            if(reservaEncontrada != null){
                                //só aceita os id's do exemplo
                                String idServico;
                                do {
                                    idServico = getString("ID Serviço (111, 222 ou 333): ");
                                    if (!idServico.equals("111") && !idServico.equals("222") && !idServico.equals("333")) {
                                        System.out.println("ID de serviço inválido! Por favor, digite 111, 222 ou 333.");
                                    }
                                    limparConsole();
                                } while (!idServico.equals("111") && !idServico.equals("222") && !idServico.equals("333"));

                                //tratamento das excessoes de data, reserva inexistente e impedimento de reservar servico add
                                try {
                                    boolean reservaConcluida = servicosAdicionais.reservar(reservaEncontrada, converterParaCalendar(getString("Data Incio (dd/MM/yyyy HH:mm): ")), converterParaCalendar(getString("Data Fim (dd/MM/yyyy HH:mm): ")), idServico);

                                    //adicionar serviço adicional na reserva
                                    if(reservaConcluida){
                                        System.out.println("Serviço Adicional adicionado com sucesso.");
                                    } 

                                } catch (DataInvalidaException e){
                                    System.out.println(e.getMessage());
                                } catch (ServicoNaoPermitidoException e){
                                    System.out.println(e.getMessage());
                                } catch (ReservaNaoEncontradaException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Serviço insdisponível o cliente não possui nenhuma reserva Ativa.");
                            }
                        } catch (ReservaNaoEncontradaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                
                case 5:

                    int respostaSubopcaoCase5 = printReservaveis(Integer.parseInt(getString("opção: \n" + "[1] - Hospedagem \n" + "[2] - Serviços Adicionais\n" + "[0] - Voltar")));

                    if(respostaSubopcaoCase5 == 1){

                        String idHosp;
                            do {
                                idHosp = getString("ID Hospedagem (001, 002 ou 003): ");
                                if (!idHosp.equals("001") && !idHosp.equals("002") && !idHosp.equals("003")) {
                                    System.out.println("ID de hospedagem inválido! Por favor, digite 001, 002 ou 033.");
                                } 
                                limparConsole();
                            } while (!idHosp.equals("001") && !idHosp.equals("002") && !idHosp.equals("003"));

                        try {
                            boolean disponibilidade = hospedagem.verificarDisponibilidade(idHosp, converterParaCalendar(getString("Data e Hora de Check-in (dd/MM/yyyy): ")),converterParaCalendar((getString("Data e Hora de Check-out (dd/MM/yyyy HH:mm): "))));

                            if(disponibilidade){
                                System.out.println("Data disponível para reserva.");
                            } else {
                                System.out.println("Data Indisponível para reserva.");
                            } 
                        } catch (DataInvalidaException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (respostaSubopcaoCase5 == 2){

                        String idServico;
                            do {
                                idServico = getString("ID Serviço (111, 222 ou 333): ");
                                if (!idServico.equals("111") && !idServico.equals("222") && !idServico.equals("333")) {
                                    System.out.println("ID de serviço inválido! Por favor, digite 111, 222 ou 333.");
                                }
                                limparConsole();
                            } while (!idServico.equals("111") && !idServico.equals("222") && !idServico.equals("333"));
                        //mostrar os servicos adicionais 
                        try{
                            boolean disponibilidade = servicosAdicionais.verificarDisponibilidade(idServico, converterParaCalendar(getString("Data e Hora de Check-in (dd/MM/yyyy): ")), converterParaCalendar(getString("Data e Hora de Check-out (dd/MM/yyyy HH:mm): ")));

                            if(disponibilidade){
                                System.out.println("Data disponível para reserva.");
                            } else {
                                System.out.println("Data Indisponível para reserva.");
                            } 

                        } catch (DataInvalidaException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;  

                case 6: 
                    int respostaSubopcaoCase6 = Integer.parseInt(getString("opção: \n" + 
                    "[1] - Hospedagem \n" + "[2] - Serviços Adicionais\n" + "[0] - Voltar"));

                    switch (respostaSubopcaoCase6) {
                        case 1:
                            //tratamento de exception caso o usuário informe uma reserva inválida
                            
                            String idReserva = getString("ID da Reserva: ");
                            try {
                                Reserva reservaEncontrada = repositorioReservas.buscar(idReserva);

                                if (reservaEncontrada != null){
                                    hospedagem.cancelarReserva(reservaEncontrada, getString("motivo do cancelamento: "));
                                }
                                System.out.println("Reserva cancelada com sucesso.");
                                
                        
                            }
                            catch (ReservaNaoEncontradaException e){
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2: 
                            String reserva = getString("ID da Reserva: ");
                            try {
                                Reserva reservaEncontrada = repositorioReservas.buscar(reserva);

                                if (reservaEncontrada != null){
                                    servicosAdicionais.cancelarReserva(reservaEncontrada, "motivo do cancelamento: ");
                                }
                                System.out.println("Serviço adicional cancelado com sucesso.");
                            } catch (ReservaNaoEncontradaException e){
                                System.out.println(e.getMessage());
                            }
                            break;
                            
                            
                        default:
                            break;
                    }
                case 8: 
                    String reserva = getString("ID da Reserva: ");
                    try {
                        Reserva reservaEncontrada = repositorioReservas.buscar(reserva);

                        if (reservaEncontrada != null){
                            reservaEncontrada.realizarCheckOut();
                            System.out.println(reservaEncontrada.toString()); 
                        }
                        System.out.println("Check-out realizado.");
                    } catch (ReservaNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    int respostaSubopcaoCase9 = Integer.parseInt(getString("opção: \n" + "[1] - Relatório de Reservas Ativas \n" + "[2] - Relatório de Cancelamentos\n" + "[3] - Histórico de Cliente"));

                    switch (respostaSubopcaoCase9) {
                        case 1:
                            relatorios.exibirRelatorioAtivas();
                            break;
                        case 2:
                            relatorios.exibirRelatorioCancelamentos();
                            break;
                        case 3:
                            String cpfCliente = getString("cpf Cliente: ");
                            relatorios.exibirHistoricoCliente(cpfCliente);
                            break;
                        default:
                            break;
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
   public int printReservaveis(int subopcao){
   
    switch (subopcao) {
        case 1: 
            System.out.println("==============Opções de Hospedagem==============\n" + 
                              "ID \t\t Hospedagem\n\n" +
                              "001\t\t Apartamento\n" +
                              "002\t\t Cabana\n" +
                              "003\t\t Quarto\n");
            return 1;
            
        case 2: 
            System.out.println("==============Opções de Serviços Adicionais==============\n" + 
                                "ID \t\t Serviço\n\n" +
                                "111\t\t Passeios Turísticos\n" +
                                "222\t\t Lavanderia\n" +
                                "333\t\t Transfer\n");
            
            return 2;
        case 0:
            System.out.println("Voltando...");
            return 0;
        default:
            return 0;
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
    public static Calendar converterParaCalendar(String data) throws DataInvalidaException{
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        //tratamento de exception do tipo: ParseException
        try {
            Date dataConvertida = formatar.parse(data);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataConvertida);

            //zera os milissegundos
            calendario.set(Calendar.MILLISECOND, 0);

            return calendario;

        } catch (ParseException e) {

            throw new DataInvalidaException("Formato de data ou Hora inválidos. Use dd/MM/yyyy HH:mm !");
        }
        
    }

}
