package reservaveis;


import java.util.Date;

import administrativo.Cliente;

public class Reserva {
    private Cliente cliente;
    private Reservavel itemReservado;
    private Date dataCheckIn;
    private Date dataCheckOut;
    private String statusReserva;

    //construtor
    public Reserva(Cliente cliente, Reservavel itemReservado, Date dataCheckIn, Date dataCheckOut) {
        this.cliente = cliente;
        this.itemReservado = itemReservado;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.statusReserva = "ativa";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reservavel getItemReservado() {
        return itemReservado;
    }

    public void setItemReservado(Reservavel itemReservado) {
        this.itemReservado = itemReservado;
    }

    public Date getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Date dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public Date getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(Date dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }

    public void cancelarReserva (String statusReserva){
        //duvida: aqui só terá essa string alterando o status? e no cancelar reserva lá dos reservaveis que "exclui" a reserva do repositorio?
        setStatusReserva("cancelada");
    }
    

    

}
