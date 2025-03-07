package administrativo;

public class Cliente {
    private String nome;
    private int cpf;
    private int telefone;

    //construtor
    public Cliente(String nome, int cpf, int telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    //getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente {\n" +
            "  Nome: " + nome + ",\n" +
            "  CPF: " + cpf + ",\n" +
            "  Telefone: " + telefone + "\n" +
            "}";
    }


    
}
