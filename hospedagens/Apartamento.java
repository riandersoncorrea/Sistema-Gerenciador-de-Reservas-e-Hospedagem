package hospedagens;


import reservaveis.Hospedagem;
import reservaveis.Reserva;



public class Apartamento extends Hospedagem {
    
    public Apartamento (){
        super();
    }

    @Override
    public double calcularValorHospedagem(Reserva reserva) {
        long dias = Reserva.totalDias(reserva);
      
        setPrecoPorDiaria(800);
        return getPrecoPorDiaria() * dias;
    }

    
}
