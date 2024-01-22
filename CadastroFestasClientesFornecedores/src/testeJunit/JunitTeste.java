package testeJunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JunitTeste {

    private ArrayList<Cliente> clientesCadastrados;
    private Scanner scanner;
    
    // Variáveis para redirecionar a saída do sistema
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        clientesCadastrados = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Redirecionar a saída do sistema para outContent
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // Restaurar a saída do sistema original
        System.setOut(originalOut);
    }

    @Test
    public void testCadastrarCliente() {
        // Simular entrada de dados do usuário
        String input = "123\nJohn Doe\n123 Main St\n555-1234\n01/01/1990";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Executar o método cadastrarCliente()
        cadastrarCliente();

        // Verificar se o cliente foi cadastrado corretamente
        assertEquals(1, clientesCadastrados.size());
        Cliente cliente = clientesCadastrados.get(0);
        assertEquals(123, cliente.getCodigo());
        assertEquals("John", cliente.getNome());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("555-1234", cliente.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
    }

    @Test
    public void testCadastrarClienteExistente() {
        // Adicionar um cliente existente para simular a situação
        Cliente clienteExistente = new Cliente(456, "Jane Doe", "456 Oak St", "555-5678",
                LocalDate.of(1995, 5, 10));
        clientesCadastrados.add(clienteExistente);

        // Simular entrada de dados do usuário com o mesmo código do cliente existente
        String input = "456\nJohn Smith\n789 Elm St\n555-9876\n02/02/1985";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Executar o método cadastrarCliente()
        cadastrarCliente();

        // Verificar se a mensagem de cliente existente foi exibida
        assertEquals("Já existe um cliente cadastrado com esse código.", systemOut());
    }

    // Método auxiliar para capturar a saída do sistema
    private String systemOut() {
        return outContent.toString().trim();
    }

    private void cadastrarCliente() {
        System.out.println("Digite o código do cliente: ");
        long codigoCliente = scanner.nextLong();

        // Verificar se o código do cliente já existe
        if (buscarCliente(codigoCliente) != null) {
            System.out.println("Já existe um cliente cadastrado com esse código.");
            return;
        }

        System.out.println("Digite o nome do cliente: ");
        String nome = scanner.next();

        System.out.println("Digite o endereço do cliente: ");
        String endereco = scanner.next();

        System.out.println("Digite o telefone do cliente: ");
        String telefone = scanner.next();

        System.out.println("Digite a data de nascimento do cliente (DD/MM/AAAA): ");
        String dataNascimentoStr = scanner.next();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = new Cliente(codigoCliente, nome, endereco, telefone, dataNascimento);
        clientesCadastrados.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private Cliente buscarCliente(long codigoCliente) {
        for (Cliente cliente : clientesCadastrados) {
            if (cliente.getCodigo() == codigoCliente) {
                return cliente;
            }
        }
        return null;
    }

}
