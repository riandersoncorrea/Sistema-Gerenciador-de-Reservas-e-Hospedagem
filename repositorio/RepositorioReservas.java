package repositorio;

import java.util.ArrayList;
import java.util.List;

import reservaveis.Reserva;

public class RepositorioReservas implements Repositorio<Reserva>{
    private List<Reserva> reservasBd;
    private static RepositorioReservas instancia;

    public RepositorioReservas (){
        this.reservasBd = new ArrayList<>();
    }

    //usando Singleton para criar apenas uma unica instancia global
    public static RepositorioReservas getInstance(){
        if (instancia == null){
            instancia = new RepositorioReservas();
        } 
        return instancia;
    }

    @Override
    public void adicionar(Reserva reserva) {
        reservasBd.add(reserva);
        
    }

    @Override
    public Reserva buscar(String id) {
        // TODO Auto-generated method stub
        for (Reserva reserva : reservasBd){
            //if (){
               return reserva; 
           // }
            
        }
        return null;
    }

    @Override
    public List<Reserva> listar() {
        // TODO Auto-generated method stub
        return new ArrayList<>(reservasBd);
    }

    


}
