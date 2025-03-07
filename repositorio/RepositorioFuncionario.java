package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Funcionario;

public class RepositorioFuncionario {
    private List<Funcionario> funcionarioBd;

    //construtor do repositorio
    public RepositorioFuncionario (){
        this.funcionarioBd = new ArrayList<>();
    }

    //metodos
    public void adicionarFuncionario(Funcionario funcionario){
        funcionarioBd.add(funcionario);
    }

    //percorre o array funcionarioBd e compara pelo id
    public Funcionario buscarFuncionario(int id){
        for (Funcionario funcionario : funcionarioBd){
            if (funcionario.getId() == id){
                return funcionario;
            }
        }
        return null;
    }

    public List<Funcionario> listaFuncionario(){
        return new ArrayList<>(funcionarioBd);
    }
}
