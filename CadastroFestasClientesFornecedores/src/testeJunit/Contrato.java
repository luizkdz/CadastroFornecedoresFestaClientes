package testeJunit;

public class Contrato {
    private long numeroContrato;
    private double valorTotal;
    private double desconto;
    private double valorFinal;
    private char formaPagamento;
    private String status;
    private long codigoFesta;

    public Contrato(long numeroContrato, double valorTotal, double desconto, char formaPagamento, String status, long codigoFesta) {
        this.numeroContrato = numeroContrato;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.codigoFesta = codigoFesta;
    }

    public double calcularValorFinal() {
        this.valorFinal = this.valorTotal - this.desconto;
        return this.valorFinal;
    }

    // Getters e Setters

    public long getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(long numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public char getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(char formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCodigoFesta() {
        return codigoFesta;
    }

    public void setCodigoFesta(long codigoFesta) {
        this.codigoFesta = codigoFesta;
    }
}

