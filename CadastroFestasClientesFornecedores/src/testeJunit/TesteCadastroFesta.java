package testeJunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteCadastroFesta {

    private List<Festa> festasCadastradas;
    private Cliente clienteExistente;
    private Fornecedor fornecedorExistente;

    @BeforeEach
    public void setUp() {
        festasCadastradas = new ArrayList<>();
        clienteExistente = new Cliente(1, "Cliente Teste", "Endereço Teste", "123456789", LocalDate.now());
        fornecedorExistente = new Fornecedor(1, "Fornecedor Teste", "987654321");
    }

    @Test
    public void testCadastrarFesta_ClienteExistente() {
        // Configurar a entrada do usuário simulada
        String input = "1\n10\n01/01/2023\nTema Teste\n2\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Configurar um cliente existente
        Cliente clienteExistente = new Cliente(1, "João", "Rua A", "123456789", LocalDate.of(1990, 1, 1));

        // Configurar outros objetos necessários para o teste
        List<Festa> festasCadastradas = new ArrayList<>();
        Fornecedor fornecedorExistente = new Fornecedor(1, "Fornecedor A", "987654321");

        // Criar uma instância de FestaController
        FestaController festaController = new FestaController();

        // Chamar o método cadastrarFesta()
        festaController.cadastrarFesta(festasCadastradas, clienteExistente, fornecedorExistente);

        // Verificar se a festa foi cadastrada com sucesso
        Assertions.assertEquals(1, festasCadastradas.size());
        Festa festaCadastrada = festasCadastradas.get(0);
        Assertions.assertEquals(1, festaCadastrada.getCodigoCliente());
        Assertions.assertEquals(10, festaCadastrada.getQuantidadeConvidados());
        Assertions.assertEquals(LocalDate.of(2023, 1, 1), festaCadastrada.getData());
        Assertions.assertEquals("Tema Teste", festaCadastrada.getTema());
        Assertions.assertEquals(2, festaCadastrada.getCodigoFornecedor());
        // Outras asserções, se necessário
    }

    @Test
    public void testCadastrarFesta_ClienteNaoEncontrado() {
        String input = "2\n10\n01/01/2023\nTema Teste\n2\n1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        FestaController festaController = new FestaController();
        festaController.cadastrarFesta(festasCadastradas, clienteExistente, fornecedorExistente);

        Assertions.assertEquals(0, festasCadastradas.size());
    }
}

class FestaController {
    public void cadastrarFesta(List<Festa> festasCadastradas, Cliente clienteExistente, Fornecedor fornecedorExistente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código do cliente: ");
        long codigoCliente = scanner.nextLong();

        // Verificar se o cliente existe no sistema
        Cliente cliente = buscarCliente(clienteExistente, codigoCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Digite a quantidade de convidados: ");
        int quantidadeConvidados = scanner.nextInt();

        System.out.println("Digite a data da festa (DD/MM/AAAA): ");
        String dataFestaStr = scanner.next();
        LocalDate dataFesta = LocalDate.parse(dataFestaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println("Digite o tema da festa: ");
        String tema = scanner.next();

        LocalTime horarioInicio;
        LocalTime horarioTermino;

        if (dataFesta.getDayOfWeek() == DayOfWeek.SATURDAY) {
            System.out.println("Escolha o horário da festa para sábado:");
            System.out.println("1 - Das 12 às 16 horas");
            System.out.println("2 - Das 18 às 22 horas");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                horarioInicio = LocalTime.of(12, 0);
            } else if (opcao == 2) {
                horarioInicio = LocalTime.of(18, 0);
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            horarioTermino = horarioInicio.plusHours(4);
        } else {
            System.out.println("Digite o horário de início da festa (HH:MM): ");
            String horarioInicioStr = scanner.next();
            horarioInicio = LocalTime.parse(horarioInicioStr, DateTimeFormatter.ofPattern("HH:mm"));
            horarioTermino = horarioInicio.plusHours(4);
        }

        for (Festa festaExistente : festasCadastradas) {
            if (horarioInicio.isBefore(festaExistente.getHorarioTermino()) && festaExistente.getData().equals(dataFesta)) {
                System.out.println("Já existe uma festa cadastrada nesse horário.");
                return;
            }
        }

        System.out.print("Digite o código do fornecedor: ");
        long codigoFornecedor = scanner.nextLong();

        // Verificar se o fornecedor existe no sistema
        Fornecedor fornecedor = buscarFornecedor(fornecedorExistente, codigoFornecedor);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        Festa festa = new Festa(codigoCliente, quantidadeConvidados, dataFesta, dataFesta.getDayOfWeek().toString(), horarioInicio, horarioTermino, tema, codigoFornecedor);
        festasCadastradas.add(festa);

        System.out.println("Festa cadastrada com sucesso!");
    }

    private Cliente buscarCliente(Cliente clienteExistente, long codigoCliente) {
        if (clienteExistente.getCodigo() == codigoCliente) {
            return clienteExistente;
        }
        return null;
    }

    private Fornecedor buscarFornecedor(Fornecedor fornecedorExistente, long codigoFornecedor) {
        if (fornecedorExistente.getCodigo() == codigoFornecedor) {
            return fornecedorExistente;
        }
        return null;
    }
}
