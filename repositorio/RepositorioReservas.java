package repositorio;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ReservaNaoEncontradaException;
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
    public Reserva buscar(String id) throws ReservaNaoEncontradaException {
        
        for (Reserva reserva : reservasBd){
            if (reserva.getId() == Integer.parseInt(id)){
                return reserva; 
            } 
        }
        
        throw new ReservaNaoEncontradaException("Reserva não encontrada.");
    }

    @Override
    public List<Reserva> listar() {
        return new ArrayList<>(reservasBd);
    }

    


}
