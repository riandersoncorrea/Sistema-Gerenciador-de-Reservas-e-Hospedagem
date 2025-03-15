package hospedagens;

import reservaveis.Hospedagem;

public class Cabana extends Hospedagem {

    public Cabana (String idHospedagem, int capacidade){
        super();
    }

    @Override
    public double calcularValorHospedagem(int dias) {
        // retorna o calulo. Podemos implementar alguma condicao para o calculo...
        setPrecoPorDiaria(350);
        return getPrecoPorDiaria() * dias;
    }
    
}
