package teste;

import administrativo.Cliente;
import administrativo.Funcionario;
import repositorio.RepositorioCliente;
import repositorio.RepositorioFuncionario;

public class Teste {
    public static void main(String[] args) {

        RepositorioCliente repositorio = new RepositorioCliente();
        RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario();

       Funcionario funcionario1 = new Funcionario(12, "Rianderson", "Atendente", repositorio, repositorioFuncionario);

       Cliente cliente1 = new Cliente("Enzo", 123456789, 5555555);

       Cliente cliente2 = new Cliente("Maria", 987654321, 22222222);
       
       Funcionario funcionario2 = new Funcionario(31, "José", "Recepcionista", repositorio, repositorioFuncionario);

       funcionario1.cadastrarCliente(cliente1);
       funcionario1.cadastrarCliente(cliente2);
       funcionario1.cadastrarFuncionario(funcionario1);
       funcionario1.cadastrarFuncionario(funcionario2);

       System.out.println(repositorio.listarClientes());
       System.out.println(repositorioFuncionario.listaFuncionario());
    
       
    }
}
