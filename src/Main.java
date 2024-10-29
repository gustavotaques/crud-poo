import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (ConnectionMySQL.getConnectionMySQL() != null) {
            Scanner scanner = new Scanner(System.in);
            PrestadorServico prestadorServico;

            while (true) {
                System.out.println("\n--- Gerenciamento de Prestadores de Serviço ---");
                System.out.println("1. Criar Prestador de Serviço");
                System.out.println("2. Ler Prestador de Serviço");
                System.out.println("3. Atualizar Prestador de Serviço");
                System.out.println("4. Deletar Prestador de Serviço");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine();

                if (opcao.equals("5")) {
                    System.out.println("Encerrando...");
                    break;
                }

                System.out.print("Tipo de Prestador (1 para Homologado, 2 para Não Homologado): ");
                String tipo = scanner.nextLine();

                if (tipo.equals("1")) {
                    prestadorServico = new PrestadorHomologado();
                } else if (tipo.equals("2")) {
                    prestadorServico = new PrestadorNaoHomologado();
                } else {
                    System.out.println("Tipo de prestador inválido.");
                    continue;
                }

                try {
                    String cpf, nome;
                    double valorHora;

                    switch (opcao) {
                        case "1":
                            // Criar
                            System.out.print("Digite o CPF: ");
                            cpf = scanner.nextLine();
                            System.out.print("Digite o Nome: ");
                            nome = scanner.nextLine();
                            System.out.print("Digite o Valor Hora: ");
                            valorHora = Double.parseDouble(scanner.nextLine());

                            prestadorServico.setCpf(cpf);
                            prestadorServico.setNome(nome);
                            prestadorServico.setValorHora(valorHora);

                            prestadorServico.criar_prestador();
                            break;
                        case "2":
                            // Ler
                            System.out.print("Digite o CPF: ");
                            cpf = scanner.nextLine();

                            prestadorServico.setCpf(cpf);

                            prestadorServico.ler_prestador();
                            break;
                        case "3":
                            // Atualizar
                            System.out.print("Digite o CPF: ");
                            cpf = scanner.nextLine();
                            System.out.print("Digite o Novo Nome: ");
                            nome = scanner.nextLine();
                            System.out.print("Digite o Novo Valor Hora: ");
                            valorHora = Double.parseDouble(scanner.nextLine());

                            prestadorServico.setCpf(cpf);
                            prestadorServico.setNome(nome);
                            prestadorServico.setValorHora(valorHora);

                            prestadorServico.atualizar_prestador();
                            break;
                        case "4":
                            // Deletar
                            System.out.print("Digite o CPF: ");
                            cpf = scanner.nextLine();

                            prestadorServico.setCpf(cpf);

                            prestadorServico.deletar_prestador();
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro: " + e.getMessage());
                }
            }

            scanner.close();
        }
    }
}