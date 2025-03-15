package reservaveis;

import java.util.Calendar;

public interface Reservavel {


    boolean verificarDisponibilidade(String idReservavel, Calendar dataCheckIn, Calendar dataCheckOut);

    void reservar(Reserva reserva);

    void cancelarReserva(Reserva reserva, String motivo);
}
