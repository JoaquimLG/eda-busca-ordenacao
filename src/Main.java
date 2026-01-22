import model.Estudante;
import searching.BuscaBinaria;
import searching.BuscaLinear;
import searching.BuscaLinearDuasPontas;
import sorting.*;
import util.GeradorDeDados;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean vetorCriado;
    private static boolean vetorOrdenado;
    private static Estudante[] vetorEstudantes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            String opcao = menuPrincipal(sc);

            switch (opcao) {
                case "1" -> gerarVetor(sc);
                case "2" -> {
                    String entradaOrdenacao = menuOrdenacao(sc);
                    if (entradaOrdenacao != null) {
                        Estudante[] vetorEstudantesOrdenacao =
                                Arrays.copyOf(vetorEstudantes, vetorEstudantes.length);
                        executarOrdenacao(vetorEstudantesOrdenacao, entradaOrdenacao);
                    }
                }
                case "3" -> {
                    String entradaBusca = menuBusca(sc);
                    if (entradaBusca != null) {
                        executarBusca(vetorEstudantes, entradaBusca, sc);
                    }
                }
                case "4" -> statusVetor();
                case "0" -> {
                    System.out.println("Encerrando programa...");
                    sc.close();
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static String menuPrincipal(Scanner sc) {
        System.out.println("1. Gerar vetor");
        System.out.println("2. Ordenar vetor");
        System.out.println("3. Buscar estudante");
        System.out.println("4. Status do vetor");
        System.out.println("0. Sair");

        System.out.print("Opção: ");
        return sc.nextLine().trim();
    }

    private static void gerarVetor(Scanner sc) {
        if (vetorCriado) {
            System.out.println("Já existe um vetor criado");
            System.out.print("Deseja criar um novo vetor? [S/N]");

            String entrada = sc.nextLine().trim().toUpperCase();

            while (entrada.isEmpty() ||
                    (entrada.charAt(0) != 'S' && entrada.charAt(0) != 'N')) {
                System.out.print("Opção inválida. Digite S ou N: ");
                entrada = sc.nextLine().trim().toUpperCase();
            }

            if (entrada.charAt(0) == 'N') {
                System.out.println("Operação cancelada");
                return;
            }
        }

        vetorCriado = false;
        vetorOrdenado = false;

        int tamanho = validarInteiro("Tamanho", sc);
        vetorEstudantes = GeradorDeDados.gerarEstudantes(tamanho);

        vetorCriado = true;
        vetorOrdenado = false;

        System.out.println("Vetor criado com sucesso!");
    }

    private static String menuOrdenacao(Scanner sc) {
        if (vetorCriado && vetorOrdenado) {
            System.out.println("Vetor já está ordenado. Gere um novo vetor para ordená-lo");
            return null;
        } else if (vetorCriado) {
            System.out.println("---- Algoritmos de Ordenação ----");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Bubble Sort Otimizado");
            System.out.println("3. Selection Sort");
            System.out.println("4. Selection Sort Estável");
            System.out.println("5. Insertion Sort");
            System.out.println("6. Merge Sort");
            System.out.println("7. Tim Sort");
            System.out.println("8. Quick Sort");
            System.out.println("9. Counting Sort");
            System.out.println("0. Sair");

            String opcao = sc.nextLine().trim();

            // matches() verifica se a STRING INTEIRA segue o padrão informado
            // Aqui, "[0-9]" significa: exatamente UM dígito entre 0 e 9
            if (opcao.isEmpty() || !opcao.matches("[0-9]")) {
                System.out.println("Opção inválida");
                return null;
            }

            if (opcao.equals("0")) {
                return null;
            }

            return opcao;
        } else {
            System.out.println("Nenhum vetor criado. Crie primeiro um vetor");
            return null;
        }
    }

    private static void executarOrdenacao(Estudante[] vetor, String opcao) {
        long tempoInicial = System.nanoTime();
        String algoritmo;

        switch (opcao) {
            case "1" -> {
                BubbleSort.bubbleSort(vetor);
                algoritmo = "Bubble Sort";
            }
            case "2" -> {
                BubbleSort.bubbleSortOtimizado(vetor);
                algoritmo = "Bubble Sort Otimizado";
            }
            case "3" -> {
                SelectionSort.selectionSort(vetor);
                algoritmo = "Selection Sort";
            }
            case "4" -> {
                SelectionSort.selectionSortEstavel(vetor);
                algoritmo = "Selection Sort Estável";
            }
            case "5" -> {
                InsertionSort.insertionSort(vetor);
                algoritmo = "Insertion Sort";
            }
            case "6" -> {
                MergeSort.mergeSort(vetor, 0, vetor.length - 1);
                algoritmo = "Merge Sort";
            }
            case "7" -> {
                Arrays.sort(vetor);
                algoritmo = "Tim Sort";
            }
            case "8" -> {
                QuickSort.quickSort(vetor, 0, vetor.length - 1);
                algoritmo = "Quick Sort";
            }
            case "9" -> {
                CountingSort.countingSort(vetor, 0, 10);
                algoritmo = "Counting Sort";
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        long tempoFinal = System.nanoTime();
        vetorOrdenado = true;

        double tempoSegundos = (tempoFinal - tempoInicial) / 1_000_000_000.0;

        System.out.printf("Ordenação concluída {%s}!\n", algoritmo);
        System.out.printf("Tempo de execução = %.6f segundos\n", tempoSegundos);
        System.out.println("Primeira posição: " + vetor[0]);
        System.out.println("Última posição: " + vetor[vetor.length - 1]);
    }

    private static String menuBusca(Scanner sc) {
        if (vetorCriado) {
            System.out.println("---- Algoritmos de Busca ----");
            System.out.println("1. Busca linear iterativa");
            System.out.println("2. Busca linear recursiva");
            System.out.println("3. Busca binária iterativa");
            System.out.println("4. Busca binária recursiva");
            System.out.println("5. Busca linear iterativa duas pontas");
            System.out.println("0. Sair");

            String opcao = sc.nextLine().trim();

            // Mesmo conceito do menu de ordenação:
            // "[0-5]" aceita apenas UM dígito entre 0 e 5.
            if (opcao.isEmpty() || !opcao.matches("[0-5]")) {
                System.out.println("Opção inválida");
                return null;
            }

            if (opcao.equals("0")) {
                return null;
            }

            return opcao;
        } else {
            System.out.println("Nenhum vetor criado. Crie primeiro um vetor");
            return null;
        }
    }

    private static void executarBusca(Estudante[] vetor, String opcao, Scanner sc) {
        int matricula = validarInteiro("Matrícula", sc);
        String algoritmo;
        Estudante estudanteBusca;

        long tempoInicial = System.nanoTime();

        switch (opcao) {
            case "1" -> {
                estudanteBusca = BuscaLinear.buscaLinearIterativa(vetor, matricula);
                algoritmo = "Busca Linear Iterativa";
            }
            case "2" -> {
                estudanteBusca = BuscaLinear.buscaLinearRecursiva(vetor, matricula, 0);
                algoritmo = "Busca Linear Recursiva";
            }
            case "3" -> {
                estudanteBusca = BuscaBinaria.buscaBinariaIterativa(vetor, matricula);
                algoritmo = "Busca Binária Iterativa";
            }
            case "4" -> {
                estudanteBusca = BuscaBinaria.buscaBinariaRecursiva(
                        vetor, matricula, 0, vetor.length - 1);
                algoritmo = "Busca Binária Recursiva";
            }
            case "5" -> {
                estudanteBusca = BuscaLinearDuasPontas.buscaLinearDuasPontas(vetor, matricula);
                algoritmo = "Busca Linear Duas Pontas";
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        long tempoFinal = System.nanoTime();
        double tempoSegundos = (tempoFinal - tempoInicial) / 1_000_000_000.0;

        System.out.printf("Busca concluída {%s}!\n", algoritmo);
        System.out.printf("Tempo de execução = %.6f segundos\n", tempoSegundos);

        if (estudanteBusca != null) {
            System.out.println(estudanteBusca);
        } else {
            System.out.println("Estudante não encontrado");
        }
    }

    private static void statusVetor() {
        if (vetorCriado && vetorOrdenado) {
            System.out.printf("Vetor de tamanho %d\nJá está ordenado\n", vetorEstudantes.length);
        } else if (vetorCriado) {
            System.out.printf("Vetor de tamanho %d\nNão está ordenado\n", vetorEstudantes.length);
        } else {
            System.out.println("Vetor inexistente");
        }
    }

    private static int validarInteiro(String msg, Scanner sc) {
        int valor;
        do {
            System.out.printf("%s: ", msg);

            while (!sc.hasNextInt()) {
                System.out.print("Valor inválido. Digite um número inteiro: ");
                sc.next();
            }

            valor = sc.nextInt();
            sc.nextLine();

            if (valor <= 0) {
                System.out.printf("%s precisa ser > 0\n", msg);
            }
        } while (valor <= 0);

        return valor;
    }
}
