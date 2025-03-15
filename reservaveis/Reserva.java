package reservaveis;


import java.util.Calendar;

import administrativo.Cliente;

public class Reserva {
    private Cliente cliente;
    private String idReservavel;
    private ItemReservavel itemReservado; // Enum para o tipo de hospedagem
    private Calendar dataCheckIn;
    private Calendar dataCheckOut;
    private StatusReserva status; //é uma variável do tipo StatusReserva(Enum) com as opções do status

    //construtor
    public Reserva(Cliente cliente, String idReservavel, Calendar dataCheckIn, Calendar dataCheckOut) {
        this.cliente = cliente;
        this.idReservavel = idReservavel;
        this.itemReservado = ItemReservavel.QUARTO;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = StatusReserva.ATIVA;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemReservavel getItemReservado() {
        return itemReservado;
    }

    public void setItemReservado(ItemReservavel itemReservado) {
        this.itemReservado = itemReservado;
    }

    public Calendar getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Calendar dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public Calendar getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(Calendar dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public StatusReserva getStatusReserva() {
        return status;
    }

    public void setStatusReserva(StatusReserva status) {
        this.status = status;
    }

    
    public String getIdReservavel() {
        return idReservavel;
    }

    public void setIdReservavel(String idReservavel) {
        this.idReservavel = idReservavel;
    }

    public void cancelarReserva (StatusReserva status){
        //altera o status da reserva para cancelada
        this.status = StatusReserva.CANCELADA;
        
    }

    public void atualizarStatus(){
    
    }
    

    

}
