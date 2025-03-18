package reservaveis;

import java.util.Calendar;

import Exceptions.ReservaNaoEncontradaException;

public interface Reservavel {


    boolean verificarDisponibilidade(String idReservavel, Calendar dataCheckIn, Calendar dataCheckOut);

    boolean reservar(Reserva reserva);

    void cancelarReserva(Reserva reserva, String motivo) throws ReservaNaoEncontradaException;


}
