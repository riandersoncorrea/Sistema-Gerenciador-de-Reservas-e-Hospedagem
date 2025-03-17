package servicos;

import reservaveis.Reserva;
import reservaveis.ServicosAdicionais;

public class Lavanderia extends ServicosAdicionais{

    public Lavanderia (){
        super();
    }


    @Override
    public double calcularValorServicos(Reserva reserva) {
        double hora = Reserva.totalHora(reserva);
        setPreco(15.0);
        return getPreco() * hora;
    }
    
}
