package teste;

import Exceptions.DataInvalidaException;
import administrativo.Cliente;
import administrativo.Funcionario;
import hospedagens.Apartamento;
import menu.MenuDeOpcoes;
import reservaveis.Hospedagem;
import reservaveis.Reserva;

public class Teste {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
    
    Hospedagem hospedagem = new Apartamento();

    MenuDeOpcoes menu = new MenuDeOpcoes();
    Funcionario funcionario = new Funcionario("123", "José", "admin");
    Cliente cliente = new Cliente("João", "123.456.789-11", "11 98876-0809");
    funcionario.cadastrarCliente(cliente);
    Reserva reserva;
    try {
        reserva = new Reserva(cliente, "001", menu.converterParaCalendar("18/03/2025 14:00"), menu.converterParaCalendar("20/03/2025 12:00"));
        hospedagem.reservar(reserva);
    } catch (DataInvalidaException e) {
        e.printStackTrace();
    }
    
    
    menu.iniciarMenu();

    }
}

