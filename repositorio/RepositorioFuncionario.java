package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Funcionario;

public class RepositorioFuncionario {
    private List<Funcionario> funcionarioBd;
    private static RepositorioFuncionario instancia; //atributo estatico para garantir o armazenamento de apenas uma instancia

    //construtor do repositorio
    public RepositorioFuncionario (){
        this.funcionarioBd = new ArrayList<>();
    }

    //usando Singleton para criar apenas uma unica instancia global
    public static RepositorioFuncionario getInstance(){
        // verifica se ja foi instaciado, senao cria uma única
        if (instancia == null){
            instancia = new RepositorioFuncionario();
        }
        return instancia; //retorna a única instancia criada
    }

    //metodos
    //recebe um obj do tipo funcionario e adiciona no ArrayList de repositorio dos funcionarios
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
