package reservaveis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Exceptions.ReservaNaoEncontradaException;
import Exceptions.ServicoNaoPermitidoException;
import repositorio.RepositorioReservas;

public abstract class ServicosAdicionais implements Reservavel {
    private String motivoCancelamento;
    private double preco;
    private static final Map<String, ItemServicosAdicionais> servicosMap =  new HashMap<>();

    RepositorioReservas repositorioReservas = RepositorioReservas.getInstance();

    
    //mapa estatico para iniciar uma vez só
    static {
        servicosMap.put("111", ItemServicosAdicionais.PASSEIO);
        servicosMap.put("222", ItemServicosAdicionais.LAVANDERIA);
        servicosMap.put("333", ItemServicosAdicionais.TRANSFER);
    }

    
    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String descricao) {
        this.motivoCancelamento = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }



    @Override
    public void cancelarReserva(Reserva reserva, String motivo) {
        reserva.setStatusServicoAdicional(StatusReserva.CANCELADO);
        setMotivoCancelamento(motivo);
    }

    
    @Override
    public boolean reservar(Reserva reserva) {
        // método sobrescrito da interface (aqui não será usao), foi feito uma sobrecarga
        return false;
    }

    //sobrecarga de metodo
    public  boolean reservar(Reserva reserva, Calendar dataInicio, Calendar dataFim, String idServico) throws ServicoNaoPermitidoException, ReservaNaoEncontradaException{

        //verifica se possui uma reserva ativa
        if((verificarDisponibilidade(idServico, reserva.getDataCheckIn(), reserva.getDataCheckOut()) 
        && (reserva.getStatusReserva() == StatusReserva.ATIVO))){
    
            ItemServicosAdicionais servicosAdicionais = servicosMap.get(idServico);
        

            if(servicosAdicionais!= null ){
                if (reserva.getDataCheckIn().before(dataFim) && reserva.getDataCheckOut().after(dataInicio)){
                    reserva.setItemServicosAdicionais(servicosAdicionais);
                    reserva.setIdServicosAdicionais(idServico);
                    reserva.setDataInicioServico(dataInicio);
                    reserva.setDataFimServico(dataFim);
                    reserva.setStatusServicoAdicional(StatusReserva.ATIVO);
                    return true;  
                } else {
                    throw new ServicoNaoPermitidoException("O serviço adicional não pode ser reservado fora do período de check-in e check-out.");
                }
            } 
        } else {
            throw new ReservaNaoEncontradaException("O cliente não possui reserva de Hospedagem ativa.");
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

    public abstract double calcularValorServicos(Reserva reserva);

    
}
