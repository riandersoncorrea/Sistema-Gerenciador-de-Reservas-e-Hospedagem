package repositorio;

import java.util.ArrayList;
import java.util.List;

import administrativo.Cliente;

public class RepositorioCliente {
    private List<Cliente> clientesBD;

    public RepositorioCliente (){
        this.clientesBD = new ArrayList<>();
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
