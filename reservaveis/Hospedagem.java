package reservaveis;
import java.util.Date;
import administrativo.Cliente;

public abstract class Hospedagem implements Reservavel {
    private int idHospedagem;
    private int capacidade;
    private double precoPorDiaria;
    private Reserva statusReserva;

    public Hospedagem(int idHospedagem, int capacidade) {
        this.idHospedagem = idHospedagem;
        this.capacidade = capacidade;
    }


    public int getIdHospedagem() {
        return idHospedagem;
    }


    public void setIdHospedagem(int idHospedagem) {
        this.idHospedagem = idHospedagem;
    }


    public int getCapacidade() {
        return capacidade;
    }


    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }


    public double getPrecoPorDiaria() {
        return precoPorDiaria;
    }

   

    public void setPrecoPorDiaria(double precoPorDiaria) {
        this.precoPorDiaria = precoPorDiaria;
    }

    // metodos hospedagem

    @Override
    public boolean verificarDisponibilidade(Date dataCheckIn, Date dataCheckOut) {
        // verificar no bd se esta disponivel 
        return false;
    }


    @Override
    public void reservar(Cliente cliente, Date dataCheckIn, Date dataCheckOut) {
        if (verificarDisponibilidade(dataCheckIn, dataCheckOut)){
            //... armazenar reserva no bd. Add reserva ao cliente/tipo reserva...
            
            
        }
        
    }

     @Override
    public void cancelarReserva(Reserva reserva) {
        // ...

    }
    
    public abstract double calcularValorHospedagem (int dias);
}
