package hospedagens;


import reservaveis.Hospedagem;
import reservaveis.Reserva;

public class Quarto extends Hospedagem {
    
    public Quarto (String idHospedagem, int capacidade) {
        super();
    }

    @Override
    public double calcularValorHospedagem(Reserva reserva) {
       //temos que ver em relacao a capcidade
       long dias = Reserva.totalDias(reserva);

        setPrecoPorDiaria(550);
        return getPrecoPorDiaria() * dias;
    }
    
    
    
}
