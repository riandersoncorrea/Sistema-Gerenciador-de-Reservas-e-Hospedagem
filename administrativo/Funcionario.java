package administrativo;
import repositorio.RepositorioCliente;
import repositorio.RepositorioFuncionario;

public class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private RepositorioCliente clienteBd;
    private RepositorioFuncionario funcionarioBd;
    
    //Passar o repositório como dependência no construtor do Funcionario para que ele use sempre a mesma instância do RepositorioCliente.
    public Funcionario(int id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.clienteBd = RepositorioCliente.getInstance();
        this.funcionarioBd = RepositorioFuncionario.getInstance();
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

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarioBd.adicionarFuncionario(funcionario);
    }

    public void realizarCheckIn() {

    }

    public void realizarCheckOut() {
         
    }

    public void gerarRelatorio() {

    }

    @Override
    public String toString() {
        return "Funcionário {\n" +
            "  Nome: " + nome + ",\n" +
            "  Id: " + id + ",\n" +
            "  Cargo: " + cargo + "\n" +
            "}";
    }
    
}
