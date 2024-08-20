import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBancario {
    private static ArrayList<ContaBancaria> contas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar conta bancária");
            System.out.println("2 - Excluir conta bancária");
            System.out.println("3 - Depósito");
            System.out.println("4 - Saque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarConta(scanner);
                    break;
                case 2:
                    excluirConta(scanner);
                    break;
                case 3:
                    deposito(scanner);
                    break;
                case 4:
                    saque(scanner);
                    break;
                case 5:
                    transferencia(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void cadastrarConta(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("E-mail: ");
        String email = scanner.next();
        System.out.print("Agência: ");
        String agencia = scanner.next();
        System.out.print("Número da conta: ");
        String numero = scanner.next();

        Cliente cliente = new Cliente(nome, cpf, email);
        ContaBancaria conta = new ContaBancaria(cliente, agencia, numero);
        contas.add(conta);

        System.out.println("Conta cadastrada com sucesso!");
    }

    private static void excluirConta(Scanner scanner) {
        System.out.print("Número da conta para excluir: ");
        String numero = scanner.next();
        boolean encontrada = false;

        for (ContaBancaria conta : contas) {
            if (conta.getNumero().equals(numero)) {
                contas.remove(conta);
                System.out.println("Conta excluída com sucesso!");
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void deposito(Scanner scanner) {
        System.out.print("Número da conta para depósito: ");
        String numero = scanner.next();
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();

        for (ContaBancaria conta : contas) {
            if (conta.getNumero().equals(numero)) {
                conta.depositar(valor);
                System.out.println("Depósito realizado com sucesso!");
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static void saque(Scanner scanner) {
        System.out.print("Número da conta para saque: ");
        String numero = scanner.next();
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();

        for (ContaBancaria conta : contas) {
            if (conta.getNumero().equals(numero)) {
                if (conta.sacar(valor)) {
                    System.out.println("Saque realizado com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static void transferencia(Scanner scanner) {
        System.out.print("Número da conta de origem: ");
        String numeroOrigem = scanner.next();
        System.out.print("Número da conta de destino: ");
        String numeroDestino = scanner.next();
        System.out.print("Valor da transferência: ");
        double valor = scanner.nextDouble();

        ContaBancaria contaOrigem = null;
        ContaBancaria contaDestino = null;

        for (ContaBancaria conta : contas) {
            if (conta.getNumero().equals(numeroOrigem)) {
                contaOrigem = conta;
            }
            if (conta.getNumero().equals(numeroDestino)) {
                contaDestino = conta;
            }
        }

        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.transferir(contaDestino, valor)) {
                System.out.println("Transferência realizada com sucesso!");
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Conta(s) não encontrada(s).");
        }
    }
}
