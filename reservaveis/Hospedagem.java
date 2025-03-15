package reservaveis;
import java.util.Calendar;
import repositorio.RepositorioReservas;

public abstract class Hospedagem implements Reservavel {
    private String idHospedagem;
    private int capacidade;
    private double precoPorDiaria;
    
    
    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();


    public String getIdHospedagem() {
        return idHospedagem;
    }


    public void setIdHospedagem(String idHospedagem) {
        this.idHospedagem = idHospedagem;
    }


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
        Reserva reservaExistente = repositorioReservas.buscar(idHospedagem);
        
        //  verifica se há uma reserva no repositorio com o id 
        if (reservaExistente != null){
            //obtem as datas de checkin e checkout e armazenas nas variáveis
            Calendar reservaCheckIn = reservaExistente.getDataCheckIn();
            Calendar reservaCheckOut = reservaExistente.getDataCheckOut();
            
            //verificação para não sobrepor as datas
            if ((dataCheckIn.before(reservaCheckOut) && dataCheckIn.after(reservaCheckIn)) ||
                (dataCheckOut.after(reservaCheckIn) && dataCheckOut.before(reservaCheckOut))){
                return false;
            }
        }
        //retorna que a hospedagem está disponível
        return true;
    }

    //(DUVIDA) vai no checkin a logica desse metodo? e aqui só ficaria a chamada do checkin? ou o contrário?
    @Override
    public void reservar(Reserva reserva) {

        if (verificarDisponibilidade(reserva.getIdReservavel(), reserva.getDataCheckIn(), reserva.getDataCheckOut()) 
                && reserva.getStatusReserva().equals(StatusReserva.ATIVA)){
            //verifica o id informado na reserva e insere o tipo de reservavel no BD reserva.
            if(reserva.getIdReservavel().equals("001")){
               reserva.setItemReservado(ItemReservavel.APARTAMENTO);
            } else if (reserva.getIdReservavel().equals("002")) {
                reserva.setItemReservado(ItemReservavel.CABANA);
            } else if (reserva.getIdReservavel().equals("003")){
                reserva.setItemReservado(ItemReservavel.QUARTO);
            }
            repositorioReservas.adicionar(reserva); //armazena a reserva no repositorio
        } else {
            System.out.println("Dia indisponivel"); // essa linha creio que não pode aqui
        }
        
    }

     @Override
    public void cancelarReserva(Reserva reserva) {
        // ...

    }
    
    // os dias posso subtrair ou contar pelo checkin e checkout
    public abstract double calcularValorHospedagem (int dias);
}
