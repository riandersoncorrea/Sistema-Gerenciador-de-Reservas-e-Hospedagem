package reservaveis;
import java.util.Calendar;

import repositorio.RepositorioCliente;
import repositorio.RepositorioReservas;


public abstract class Hospedagem implements Reservavel {
    private static int id = 1000; 
    private int capacidade;
    private double precoPorDiaria;
    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();
    RepositorioCliente repositorioClientes = RepositorioCliente.getInstance();
    


    public int getCapacidade() {
        return capacidade;
    }


    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }


    public double getPrecoPorDiaria() {
        return precoPorDiaria;
    }

   

    public void setPrecoPorDiaria(double precoPorDiaria) {
        this.precoPorDiaria = precoPorDiaria;
    }

    // metodos hospedagem

    @Override
    public boolean verificarDisponibilidade(String idHospedagem, Calendar dataCheckIn, Calendar dataCheckOut) {

        //cria-se uma variável para armazenar a busca no repositório da reserva existente com id fornecido
        Reserva reservaExistente = repositorioReservas.buscarHospedagem(idHospedagem);

            
        //  verifica se há uma reserva no repositorio com o id 
        if (reservaExistente != null){
            //obtem as datas de checkin e checkout e armazenas nas variáveis
            Calendar reservaCheckIn = reservaExistente.getDataCheckIn();
            Calendar reservaCheckOut = reservaExistente.getDataCheckOut();
            
            //verificação para não sobrepor as datas
            if ((dataCheckIn.before(reservaCheckOut) && dataCheckOut.after(reservaCheckIn))){
                return false;
            }
        } 
        //retorna que a hospedagem está disponível
        return true;
    }

    //(DUVIDA) vai no checkin a logica desse metodo? e aqui só ficaria a chamada do checkin? ou o contrário?
    
    @Override
    public boolean reservar(Reserva reserva) {
      
     if (verificarDisponibilidade(reserva.getidHospedagem(), reserva.getDataCheckIn(), reserva.getDataCheckOut()) 
         && !(reserva.getStatusReserva().equals(StatusReserva.ATIVA))){
           
            
            //verifica o id informado na reserva e insere o tipo de reservavel no BD reserva.
            if(reserva.getidHospedagem().equals("001")){
            reserva.setItemReservado(ItemHospedagem.APARTAMENTO);
            } else if (reserva.getidHospedagem().equals("002")) {
                reserva.setItemReservado(ItemHospedagem.CABANA);
            } else if (reserva.getidHospedagem().equals("003")){
                reserva.setItemReservado(ItemHospedagem.QUARTO);
            }

             
            reserva.setId(id++); //incrementa um no id da reserva
            reserva.setStatusReserva(StatusReserva.ATIVA);
            repositorioReservas.adicionar(reserva); //armazena a reserva no repositorio
            return true;
    
        } else {
            return false;
        }
    }   

    @Override
    public void cancelarReserva(Reserva reserva, String motivo) {
        // ...

    }

   
    
    // os dias posso subtrair ou contar pelo checkin e checkout
    public abstract double calcularValorHospedagem (int dias);
}
