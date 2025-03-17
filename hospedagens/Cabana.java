package hospedagens;

import reservaveis.Hospedagem;
import reservaveis.Reserva;

public class Cabana extends Hospedagem {

    public Cabana (String idHospedagem, int capacidade){
        super();
    }

    @Override
    public double calcularValorHospedagem(Reserva reserva) {
        long dias = Reserva.totalDias(reserva);
       
        setPrecoPorDiaria(350);
        return getPrecoPorDiaria() * dias;
    }
    
}
