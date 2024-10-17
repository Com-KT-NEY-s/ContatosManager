package contatos;

public class Contact {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String categoria;

    public Contact(String nome, String telefone, String email, String endereco, String categoria) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.categoria = categoria;
    }

    // Getters and Setters
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
