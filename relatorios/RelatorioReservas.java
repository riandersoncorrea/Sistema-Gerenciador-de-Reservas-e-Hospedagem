package relatorios;

import java.util.ArrayList;
import java.util.List;
import reservaveis.Reserva;
import reservaveis.StatusReserva;
import repositorio.RepositorioReservas;

public class RelatorioReservas {

    private RepositorioReservas repositorioReservas;

    public RelatorioReservas() {
        this.repositorioReservas = RepositorioReservas.getInstance();
    }

    // Relatório de reservas ativas
    public List<Reserva> gerarRelatorioAtivas() {
        List<Reserva> reservasAtivas = new ArrayList<>();
        
        for (Reserva reserva : repositorioReservas.listar()) {
            if (reserva.getStatusReserva() != StatusReserva.CANCELADO 
            && reserva.getStatusReserva() != StatusReserva.FINALIZADO) {
                reservasAtivas.add(reserva);
            }
        }
        
        return reservasAtivas;
    }

    public void exibirRelatorioAtivas() {
        List<Reserva> reservasAtivas = gerarRelatorioAtivas();
        
        if (reservasAtivas.isEmpty()) {
            System.out.println("Não há reservas ativas.");
        } else {
            for (Reserva reserva : reservasAtivas) {
                System.out.println(reserva);
            }
        }
    }

    // Relatório de cancelamentos
    public void exibirRelatorioCancelamentos() {
        int totalCancelamentos = 0;
        List<String> motivosCancelamento = new ArrayList<>();
        
        for (Reserva reserva : repositorioReservas.listar()) {
            if (reserva.getStatusReserva() == StatusReserva.CANCELADO) {
                totalCancelamentos++;
                motivosCancelamento.add(reserva.getMotivosCancelamento()); // Supondo que exista um método para obter o motivo de cancelamento
            }
        }

        System.out.println("Total de Cancelamentos: " + totalCancelamentos);
        if (!motivosCancelamento.isEmpty()) {
            System.out.println("Motivos de Cancelamento:");
            for (String motivo : motivosCancelamento) {
                System.out.println("- " + motivo);
            }
        }
    }

    // Relatório de histórico de um cliente
    public void exibirHistoricoCliente(String cpf) {
        List<Reserva> reservasCliente = new ArrayList<>();
        
        for (Reserva reserva : repositorioReservas.listar()) {
            if (reserva.getCliente().getCpf().equals(cpf)) {  // busca pelo cpf
                reservasCliente.add(reserva);
            }
        }

        if (reservasCliente.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para o cliente " + cpf);
        } else {
            System.out.println("Histórico de Reservas do Cliente " + cpf + ":");
            for (Reserva reserva : reservasCliente) {
                System.out.println(reserva);
            }
        }
    }
}
