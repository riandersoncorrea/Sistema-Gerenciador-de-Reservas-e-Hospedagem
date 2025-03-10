package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Cliente;

public class RepositorioCliente {
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
    public void adicionarCliente (Cliente cliente) {
        clientesBD.add(cliente);
    }

    public Cliente buscarCliente (int cpf){
        for (Cliente cliente : clientesBD) {
            if (cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientesBD);
    }
    
}
