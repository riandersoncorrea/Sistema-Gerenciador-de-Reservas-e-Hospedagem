package reservaveis;

import java.util.Calendar;

import repositorio.RepositorioReservas;

public abstract class ServicosAdicionais implements Reservavel {
    
    private String descricao;
    private double preco;
    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public void cancelarReserva(Reserva reserva, String motivo) {
       
        
    }

    @Override
    public boolean reservar(Reserva reserva) {

        //verifica se possui uma reserva ativa
        if(reserva.getStatusReserva() == StatusReserva.ATIVA){
            if(reserva.getIdServicosAdicionais().equals("111")){
                reserva.setItemServicosAdicionais(ItemServicosAdicionais.PASSEIO);
            } else if (reserva.getIdServicosAdicionais().equals("222")) {
                reserva.setItemServicosAdicionais(ItemServicosAdicionais.LAVANDERIA);
            } else if (reserva.getIdServicosAdicionais().equals("333")){
                 reserva.setItemServicosAdicionais(ItemServicosAdicionais.TRANSFER);
            } else {
                return false;
            }
            return true;  
        }
        return false;
    }
    
    @Override
    public boolean verificarDisponibilidade(String idServico, Calendar dataCheckIn, Calendar dataCheckOut) {
        // TODO Auto-generated method stub
        return false;
    }

   
    
}
