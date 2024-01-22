package testeJunit;

import java.time.LocalDate;
import java.time.LocalTime;

public class Festa {
    private long codigo;
    private int quantidadeConvidados;
    private LocalDate data;
    private String diaSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioTermino;
    private String tema;
    private long codigoCliente;
    private long codigoFornecedor;

    public Festa(long codigoCliente, int quantidadeConvidados, LocalDate data, String diaSemana,
                 LocalTime horarioInicio, LocalTime horarioTermino, String tema,
                  long codigoFornecedor) {
    
        this.quantidadeConvidados = quantidadeConvidados;
        this.data = data;
        this.diaSemana = diaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.tema = tema;
        this.codigoCliente = codigoCliente;
        this.codigoFornecedor = codigoFornecedor;
    }

    // Getters e Setters
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public void setQuantidadeConvidados(int quantidadeConvidados) {
        this.quantidadeConvidados = quantidadeConvidados;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(LocalTime horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public long getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(long codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }
}
