package reservaveis;

import java.util.Date;

import administrativo.Cliente;
import reservaveis.Reserva;

public interface Reservavel {


    boolean verificarDisponibilidade(Date dataCheckIn, Date dataCheckOut);

    void reservar(Cliente cliente, Date dataCheckIn, Date dataCheckOut);

    void cancelarReserva(Reserva reserva);
}
