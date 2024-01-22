package testeJunit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static private List<Cliente> clientesCadastrados; 
    static private List<Fornecedor> fornecedoresCadastrados;
    static private List<Festa> festasCadastradas;

    public Main() {
        clientesCadastrados = new ArrayList<>();
        fornecedoresCadastrados = new ArrayList<>();
        festasCadastradas = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.executar();
    }

    public void executar() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarFornecedor();
                    break;
                case 3:
                    cadastrarFesta();
                    break;
                case 4:
                    exibirClientes();
                    break;
                case 5:
                    exibirFornecedores();
                    break;
                case 6:
                    exibirFestas();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            System.out.println();
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Fornecedor");
        System.out.println("3 - Cadastrar Festa");
        System.out.println("4 - Exibir Clientes Cadastrados");
        System.out.println("5 - Exibir Fornecedores Cadastrados");
        System.out.println("6 - Exibir Festas Cadastradas");
        System.out.println("0 - Sair");
    }

    public static Cliente cadastrarCliente(long l, String string1, String string2, String string3, LocalDate localDate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código do cliente: ");
        long codigoCliente = scanner.nextLong();

        
        if (buscarCliente(codigoCliente) != null) {
            System.out.println("Já existe um cliente cadastrado com esse código.");
            
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
        return cliente;
    }
    public static Cliente cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o código do cliente: ");
        long codigoCliente = scanner.nextLong();

        
        if (buscarCliente(codigoCliente) != null) {
            System.out.println("Já existe um cliente cadastrado com esse código.");
            
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
        return cliente;
    }

    private void cadastrarFornecedor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código do fornecedor: ");
        long codigoFornecedor = scanner.nextLong();

        
        if (buscarFornecedor(codigoFornecedor) != null) {
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

    private void cadastrarFesta() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código do cliente: ");
        long codigoCliente = scanner.nextLong();

       
        Cliente cliente = buscarCliente(codigoCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade de convidados: ");
        int quantidadeConvidados = scanner.nextInt();

        System.out.print("Digite a data da festa (DD/MM/AAAA): ");
        String dataFestaStr = scanner.next();
        LocalDate dataFesta = LocalDate.parse(dataFestaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Digite o tema da festa: ");
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
            System.out.print("Digite o horário de início da festa (HH:MM): ");
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

        
        Fornecedor fornecedor = buscarFornecedor(codigoFornecedor);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        Festa festa = new Festa(codigoCliente, quantidadeConvidados, dataFesta, dataFesta.getDayOfWeek().toString(), horarioInicio, horarioTermino, tema, codigoFornecedor);
        festasCadastradas.add(festa);

        System.out.println("Festa cadastrada com sucesso!");
    }


    private void exibirClientes() {
        System.out.println("----- CLIENTES CADASTRADOS -----");
        for (Cliente cliente : clientesCadastrados) {
            System.out.println("Código: " + cliente.getCodigo());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
            System.out.println("-----------------------");
        }
    }

    private void exibirFornecedores() {
        System.out.println("----- FORNECEDORES CADASTRADOS -----");
        for (Fornecedor fornecedor : fornecedoresCadastrados) {
            System.out.println("Código: " + fornecedor.getCodigo());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("Telefone: " + fornecedor.getTelefone());
            System.out.println("-----------------------");
        }
    }

    private void exibirFestas() {
        System.out.println("----- FESTAS CADASTRADAS -----");
        for (Festa festa : festasCadastradas) {
            System.out.println("Código do Cliente: " + festa.getCodigoCliente());
            System.out.println("Quantidade de Convidados: " + festa.getQuantidadeConvidados());
            System.out.println("Data da Festa: " + festa.getData());
            System.out.println("Dia da Semana: " + festa.getDiaSemana());
            System.out.println("Horário de Início: " + festa.getHorarioInicio());
            System.out.println("Horário de Término: " + festa.getHorarioTermino());
            System.out.println("Tema: " + festa.getTema());
            System.out.println("Código do Fornecedor: " + festa.getCodigoFornecedor());
            System.out.println("-----------------------");
        }
    }

    static Cliente buscarCliente(long codigoCliente) {
        for (Cliente cliente : clientesCadastrados) {
            if (cliente.getCodigo() == codigoCliente) {
                return cliente;
            }
        }
        return null;
    }

    private Fornecedor buscarFornecedor(long codigoFornecedor) {			
        for (Fornecedor fornecedor : fornecedoresCadastrados) {
            if (fornecedor.getCodigo() == codigoFornecedor) {
                return fornecedor;
            }
        }
        return null;
    }


}