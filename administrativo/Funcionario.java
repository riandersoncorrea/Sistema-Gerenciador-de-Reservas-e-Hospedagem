package administrativo;
import repositorio.RepositorioCliente;

public class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private RepositorioCliente clienteBd;
    
    //Passar o repositório como dependência no construtor do Funcionario para que ele use sempre a mesma instância do RepositorioCliente.
    public Funcionario(int id, String nome, String cargo, RepositorioCliente clienteBd) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.clienteBd = clienteBd;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public RepositorioCliente getClienteBd() {
        return clienteBd;
    }


    public void setClienteBd(RepositorioCliente clienteBd) {
        this.clienteBd = clienteBd;
    }

    //metodos
    public void cadastrarCliente(Cliente cliente) {
        clienteBd.adicionarCliente(cliente);
        
    }

    public void realizarCheckIn() {

    }

    public void realizarCheckOut() {
         
    }

    public void gerarRelatorio() {

    }


    
}
