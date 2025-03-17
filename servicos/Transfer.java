package servicos;

import reservaveis.Reserva;
import reservaveis.ServicosAdicionais;

public class Transfer extends ServicosAdicionais{
    public Transfer (){
        super();
    }

    @Override
    public double calcularValorServicos(Reserva reserva) {
        double hora = Reserva.totalHora(reserva);
        setPreco(25.5);
        return getPreco() * hora;
    }

    
}
