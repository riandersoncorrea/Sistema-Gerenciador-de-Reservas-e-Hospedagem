package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Funcionario;

public class RepositorioFuncionario implements Repositorio<Funcionario> {
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
    @Override
    public void adicionar(Funcionario funcionario){
        funcionarioBd.add(funcionario);
    }

    //percorre o array funcionarioBd e compara pelo id
    @Override
    public Funcionario buscar (String id){
        for (Funcionario funcionario : funcionarioBd){
            if (funcionario.getId().equals(id)){
                return funcionario;
            }
        }
        return null;
    }

    @Override
    public List<Funcionario> listar (){
        return new ArrayList<>(funcionarioBd);
    }
}
