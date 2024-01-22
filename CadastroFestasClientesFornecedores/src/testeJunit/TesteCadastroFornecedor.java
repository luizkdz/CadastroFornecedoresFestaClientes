package testeJunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteCadastroFornecedor{
    private List<Fornecedor> fornecedoresCadastrados;

    @BeforeEach
    public void setup() {
        fornecedoresCadastrados = new ArrayList<>();
    }

    @Test
    public void testCadastrarFornecedor() {
        // Simulando entrada do usuário
        String input = "123\nFornecedor Teste\n123456789";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Executando o método cadastrarFornecedor
        cadastrarFornecedor(fornecedoresCadastrados);

        // Verificando se o fornecedor foi cadastrado corretamente
        Assertions.assertEquals(1, fornecedoresCadastrados.size());
        Fornecedor fornecedor = fornecedoresCadastrados.get(0);
        Assertions.assertEquals(123, fornecedor.getCodigo());
        Assertions.assertEquals("Fornecedor Teste", fornecedor.getNome());
        Assertions.assertEquals("123456789", fornecedor.getTelefone());
    }

    @Test
    public void testCadastrarFornecedorCodigoExistente() {
        // Adicionando um fornecedor com código existente
        fornecedoresCadastrados.add(new Fornecedor(123, "Fornecedor Existente", "987654321"));

        // Simulando entrada do usuário
        String input = "123\nFornecedor Teste\n123456789";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Executando o método cadastrarFornecedor
        cadastrarFornecedor(fornecedoresCadastrados);

        // Verificando se a mensagem de erro foi exibida
        Assertions.assertEquals(1, fornecedoresCadastrados.size());
        Assertions.assertEquals("Já existe um fornecedor cadastrado com esse código.", systemOut());
    }

    // Método para cadastrar fornecedor
    public void cadastrarFornecedor(List<Fornecedor> fornecedoresCadastrados) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código do fornecedor: ");
        long codigoFornecedor = scanner.nextLong();

        // Verificar se o código do fornecedor já existe
        if (buscarFornecedor(codigoFornecedor, fornecedoresCadastrados) != null) {
            System.out.println("Já existe um fornecedor cadastrado com esse código.");
            return;
        }

        System.out.print("Digite o nome do fornecedor: ");
        String nome = scanner.next();

        System.out.print("Digite o telefone do fornecedor: ");
        String telefone = scanner.next();

        Fornecedor fornecedor = new Fornecedor(codigoFornecedor, nome, telefone);
        fornecedoresCadastrados.add(fornecedor);

        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    // Método para buscar fornecedor
    public Fornecedor buscarFornecedor(long codigoFornecedor, List<Fornecedor> fornecedoresCadastrados) {
        for (Fornecedor fornecedor : fornecedoresCadastrados) {
            if (fornecedor.getCodigo() == codigoFornecedor) {
                return fornecedor;
            }
        }
        return null;
    }

    // Método para capturar a saída de console
    private String systemOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out.toString().trim();
    }
}