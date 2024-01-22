	package testeJunit;
	
	public class Fornecedor {
	    private long codigo;
	    private String nome;
	    private String telefone;
	
	    public Fornecedor(long codigo, String nome, String telefone) {
	        this.codigo = codigo;
	        this.nome = nome;
	        this.telefone = telefone;
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
	
	    public String getTelefone() {
	        return telefone;
	    }
	
	    public void setTelefone(String telefone) {
	        this.telefone = telefone;
	    }
	}
