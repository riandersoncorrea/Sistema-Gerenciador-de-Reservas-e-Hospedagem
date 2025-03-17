package servicos;

import reservaveis.Reserva;
import reservaveis.ServicosAdicionais;

public class PasseiosTuristicos extends ServicosAdicionais {

   public PasseiosTuristicos (){
        super();
   }

    @Override
    public double calcularValorServicos(Reserva reserva) {
        double hora = Reserva.totalHora(reserva);
        setPreco(10.0);
        return getPreco() * hora;
    }

  

 


}
