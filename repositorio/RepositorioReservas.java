package repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        
        for (Reserva reserva : reservasBd){
            if (reserva.getId() == Integer.parseInt(id)){
                return reserva; 
            } 
        }
        return null;
    }

    public Reserva buscarHospedagem(String idHospedagem){
        for (Reserva reserva : reservasBd){
            if (reserva.getidHospedagem().equals(idHospedagem)){
                return reserva; 
            } 
        }
        return null;
    }

    public Reserva buscarServicoAdicional(String idServicoAdicionais) {
        for (Reserva reserva : reservasBd){
            //compara mesmo se um dos lados for null 
            if (Objects.equals(reserva.getIdServicosAdicionais(),(idServicoAdicionais))){ 
                return reserva; 
            } 
        }
       return null;
    }

    @Override
    public List<Reserva> listar() {
        return new ArrayList<>(reservasBd);
    }


    


}
