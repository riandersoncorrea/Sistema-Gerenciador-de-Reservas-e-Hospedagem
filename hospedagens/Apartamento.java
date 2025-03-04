package hospedagens;

import reservaveis.Hospedagem;

public class Apartamento extends Hospedagem {

    public Apartamento (int idHospedagem, int capacidade){
        super(idHospedagem, capacidade);
    }

    @Override
    public double calcularValorHospedagem(int dias) {
        // retorna o calulo. Podemos implementar alguma condicao para o calculo...
        setPrecoPorDiaria(800);
        return getPrecoPorDiaria() * dias;
    }
}
