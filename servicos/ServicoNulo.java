package servicos;

import reservaveis.Reserva;
import reservaveis.ServicosAdicionais;

public class ServicoNulo extends ServicosAdicionais{

    public ServicoNulo (){
        super();
    }

    
    @Override
    public double calcularValorServicos(Reserva reserva) {
        return 0.0;
    }

    
}
