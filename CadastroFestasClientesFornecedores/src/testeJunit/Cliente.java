package testeJunit;

import java.time.LocalDate;


public class Cliente {
//	código do cliente, nome, endereço, telefone, data de nascimento
	 	private long codigo;
	    private String nome;
	    private String endereco;
	    private String telefone;
	    private LocalDate dataNascimento;
	    
	    public Cliente(long codigo, String nome, String endereco, String telefone, LocalDate dataNascimento) {
	        this.codigo = codigo;
	        this.nome = nome;
	        this.endereco = endereco;
	        this.telefone = telefone;
	        this.dataNascimento = dataNascimento;
	    }

	    // Getters e Setters
	    public long getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(long codigo) {
	        this.codigo = codigo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getEndereco() {
	        return endereco;
	    }

	    public void setEndereco(String endereco) {
	        this.endereco = endereco;
	    }

	    public String getTelefone() {
	        return telefone;
	    }

	    public void setTelefone(String telefone) {
	        this.telefone = telefone;
	    }

	    public LocalDate getDataNascimento() {
	        return dataNascimento;
	    }

	    public void setDataNascimento(LocalDate dataNascimento) {
	        this.dataNascimento = dataNascimento;
	    }
}
