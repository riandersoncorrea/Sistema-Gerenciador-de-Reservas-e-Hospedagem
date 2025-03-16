package reservaveis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import menu.MenuDeOpcoes;
import repositorio.RepositorioReservas;

public abstract class ServicosAdicionais implements Reservavel {
    
    private String descricao;
    private double preco;
    private static final Map<String, ItemServicosAdicionais> servicosMap =  new HashMap<>();

    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();

    
    //mapa estatico para iniciar uma vez só
    static {
        servicosMap.put("111", ItemServicosAdicionais.PASSEIO);
        servicosMap.put("222", ItemServicosAdicionais.LAVANDERIA);
        servicosMap.put("333", ItemServicosAdicionais.TRANSFER);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public void cancelarReserva(Reserva reserva, String motivo) {
       
        
    }

    //sobrecarga de metodo
    public boolean reservar(Reserva reserva, Calendar dataInicio, Calendar dataFim, String idServico) {

        //verifica se possui uma reserva ativa
        if((verificarDisponibilidade(idServico, reserva.getDataCheckIn(), reserva.getDataCheckOut()) 
        && (reserva.getStatusReserva() == StatusReserva.ATIVA))){
    
            ItemServicosAdicionais servicosAdicionais = servicosMap.get(idServico);
        

            if(servicosAdicionais!= null){
                reserva.setItemServicosAdicionais(servicosAdicionais);
                reserva.setIdServicosAdicionais(idServico);
                reserva.setDataInicioServico(dataInicio);
                reserva.setDataFimServico(dataFim);
                return true;  
            }
        }
        return false;
    }
    

    @Override
    public boolean verificarDisponibilidade(String idServico, Calendar dataCheckIn, Calendar dataCheckOut) {

        Reserva reservaExistente = repositorioReservas.buscarServicoAdicional  (idServico);

        
            if (reservaExistente != null) {

                Calendar reservaCheckIn = reservaExistente.getDataCheckIn();
                Calendar reservaCheckOut = reservaExistente.getDataCheckOut();

                if ((dataCheckIn.before(reservaCheckOut) && dataCheckOut.after(reservaCheckIn))){
                    return false;
                }
            }
        return true;
    }

    
}
