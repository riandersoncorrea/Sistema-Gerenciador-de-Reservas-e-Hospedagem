package hospedagens;

import reservaveis.Hospedagem;

public class Quarto extends Hospedagem {
    
    public Quarto (String idHospedagem, int capacidade) {
        super();
    }

    @Override
    public double calcularValorHospedagem(int dias) {
       // sugestao: podemos adicionar valores da diaria de acordo com o numero de hospedes ou deixar fixo criando uma constante pra facilitar a manutencao
       
       if (getCapacidade() == 2 ){
            setPrecoPorDiaria(300);
       } else if (getCapacidade() > 2) {
            setPrecoPorDiaria(550);
       }
        return getPrecoPorDiaria() * dias;
    }
    
    
    
}
