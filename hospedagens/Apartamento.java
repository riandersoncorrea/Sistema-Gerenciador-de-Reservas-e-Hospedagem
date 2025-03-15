package hospedagens;

import reservaveis.Hospedagem;

public class Apartamento extends Hospedagem {
    
    public Apartamento (){
        super();
    }

    @Override
    public double calcularValorHospedagem(int dias) {
        // retorna o cálculo. Podemos implementar alguma condição para o cálculo...
        setPrecoPorDiaria(800);
        return getPrecoPorDiaria() * dias;
    }
}
