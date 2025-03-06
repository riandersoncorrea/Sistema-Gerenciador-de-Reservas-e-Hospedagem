package administrativo;
import repositorio.RepositorioCliente;

public class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private RepositorioCliente clienteBd;

    public Funcionario(int id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
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

    public void cadastrarCliente(Cliente cliente) {
        //devo chamar o metodo adicionarCliente() da classe RepositorioCliente?
        
    }

    public void realizarCheckIn() {

    }

    public void realizarCheckOut() {
         
    }

    public void gerarRelatorio() {

    }


    
}
