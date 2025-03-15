package reservaveis;

import java.util.Date;

import administrativo.Cliente;

public abstract class ServicosAdicionais implements Reservavel {
    private String descricao;
    private double preco;

    @Override
    public void cancelarReserva(Reserva reserva) {
        // cancelar
        
    }

    @Override
    public void reservar(Cliente cliente, Date dataCheckIn, Date dataCheckOut) {
        // verificar se o cliente tem pelo menos uma reserva de hospedagem ativa
        
    }

    @Override
    public boolean verificarDisponibilidade(int idReservavel, Date dataCheckIn, Date dataCheckOut) {
        // verificar no bd se esta disponivel 
        return false;
    }
    
}
