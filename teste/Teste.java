package teste;

import administrativo.Cliente;
import administrativo.Funcionario;
import repositorio.RepositorioCliente;

public class Teste {
    public static void main(String[] args) {

        RepositorioCliente repositorio = new RepositorioCliente();

       Funcionario funcionario1 = new Funcionario(12, "Rianderson", "Atendente", repositorio);

       Cliente cliente1 = new Cliente("Enzo", 123456789, 5555555);

       Cliente cliente2 = new Cliente("Maria", 987654321, 22222222);
       

       funcionario1.cadastrarCliente(cliente1);
       funcionario1.cadastrarCliente(cliente2);

       System.out.println(repositorio.listarClientes());
    
       
    }
}
