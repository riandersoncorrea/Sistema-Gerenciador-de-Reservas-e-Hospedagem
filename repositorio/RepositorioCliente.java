package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Cliente;

public class RepositorioCliente implements Repositorio<Cliente> {
    private List<Cliente> clientesBD; //cria uma lista para armazenar os clientes
    private static RepositorioCliente instancia; //atributo estatico para garantir o armazenamento de apenas uma instancia

    public RepositorioCliente (){
        this.clientesBD = new ArrayList<>();
    }

    //usando Singleton para criar apenas uma unica instancia global
    public static RepositorioCliente getInstance(){

        // verifica se ja foi instaciado, senao cria uma única
        if (instancia == null){
            instancia = new RepositorioCliente();
        }
        return instancia; //retorna a única instancia criada

    } 
    //adicionando o cliente no repositorio
    @Override
    public void adicionar (Cliente cliente) {
        clientesBD.add(cliente);
    }

    //buscando o cliente no repositorio
    @Override
    public Cliente buscar (String cpf){
        for (Cliente cliente : clientesBD) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listar () {
        return new ArrayList<>(clientesBD);
    }
    
}
