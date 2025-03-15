package reservaveis;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import administrativo.Cliente;

public class Reserva {
    private Cliente cliente;
    private int id;
    private String idHospedagem;
    private String idServicosAdicionais;
    private ItemHospedagem itemReservado; // Enum para o tipo de hospedagem
    private ItemServicosAdicionais itemServicosAdicionais; //Enum para o tipo de servicos adicionais
    private Calendar dataCheckIn;
    private Calendar dataCheckOut;
    private StatusReserva status; //é uma variável do tipo StatusReserva(Enum) com as opções do status

    //construtor
    public Reserva(Cliente cliente, String idHospedagem, Calendar dataCheckIn, Calendar dataCheckOut) {
        this.cliente = cliente;
        this.id = id;
        this.idHospedagem = idHospedagem;
        this.itemReservado = ItemHospedagem.NENHUM;
        this.idServicosAdicionais = idServicosAdicionais;
        this.itemServicosAdicionais = ItemServicosAdicionais.NENHUM;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = StatusReserva.NENHUM;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemHospedagem getItemReservado() {
        return itemReservado;
    }

    public void setItemReservado(ItemHospedagem itemReservado) {
        this.itemReservado = itemReservado;
    }

    
    public String getIdServicosAdicionais() {
        return idServicosAdicionais;
    }

    public void setIdServicosAdicionais(String idServicosAdicionais) {
        this.idServicosAdicionais = idServicosAdicionais;
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

    
    public String getidHospedagem() {
        return idHospedagem;
    }

    public void setidHospedagem(String idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public ItemServicosAdicionais getItemServicosAdicionais() {
        return itemServicosAdicionais;
    }

    public void setItemServicosAdicionais(ItemServicosAdicionais itemServicosAdicionais) {
        this.itemServicosAdicionais = itemServicosAdicionais;
    }

    public void cancelarReserva (StatusReserva status){
        //altera o status da reserva para cancelada
        this.status = StatusReserva.CANCELADA;
        
    } 

    @Override
    public String toString() {
        SimpleDateFormat formatarDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Reservas {\n" +
            "  ID Reserva: " + id + ",\n" +
            "  Cliente: " + cliente + ",\n" +
            "  Tipo Hospedagem: " + idHospedagem + " - " + itemReservado + "\n" +
            "  Serviço Adicional: " + idServicosAdicionais + " - " + itemServicosAdicionais + "\n" +
            "  Data Check-in: " +  formatarDataHora.format(dataCheckIn.getTime()) + "\n" +
            "  Data Check-out: " + formatarDataHora.format(dataCheckOut.getTime()) + "\n" +
            "  Status Reserva: " + status + "\n" +
            "}";
    }
}
