package ui;

import model.Estudante;
import searching.BuscaBinaria;
import searching.BuscaLinear;
import searching.BuscaLinearDuasPontas;
import sorting.*;
import util.GeradorDeDados;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner sc = new Scanner(System.in);

    private boolean vetorCriado;
    private boolean vetorOrdenado;

    // Vetor ORIGINAL (sempre ordenado por matrícula)
    private Estudante[] vetorEstudantes;

    // Vetor AUXILIAR (ordenado pelo critério do compareTo da classe Estudante)
    private Estudante[] vetorEstudantesOrdenado;

    public void start() {
        boolean executando = true;

        while (executando) {
            String opcao = menuPrincipal();

            switch (opcao) {
                case "1" -> gerarVetor();

                case "2" -> {
                    String entradaOrdenacao = menuOrdenacao();
                    if (entradaOrdenacao != null) {
                        vetorEstudantesOrdenado =
                                ordenarVetor(
                                        Arrays.copyOf(vetorEstudantes, vetorEstudantes.length),
                                        entradaOrdenacao
                                );
                        vetorOrdenado = true;
                    }
                }

                case "3" -> {
                    String entradaBusca = menuBusca();
                    if (entradaBusca != null) {
                        Estudante resultado = executarBusca(vetorEstudantes, entradaBusca);
                        exibirResultadoBusca(resultado);
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

    private String menuPrincipal() {
        System.out.println("1. Gerar vetor");
        System.out.println("2. Ordenar vetor");
        System.out.println("3. Buscar estudante");
        System.out.println("4. Status do vetor");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
        return sc.nextLine().trim();
    }

    private void gerarVetor() {
        int tamanho = validarInteiro("Tamanho");

        vetorEstudantes = GeradorDeDados.gerarEstudantes(tamanho);
        vetorEstudantesOrdenado = null;

        vetorCriado = true;
        vetorOrdenado = false;

        System.out.println("Vetor criado com sucesso!");
    }

    private String menuOrdenacao() {
        if (!vetorCriado) {
            System.out.println("Nenhum vetor criado.");
            return null;
        }

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

        if (opcao.isEmpty() || !opcao.matches("[0-9]") || opcao.equals("0")) {
            System.out.println("Opção inválida");
            return null;
        }

        return opcao;
    }

    private Estudante[] ordenarVetor(Estudante[] vetor, String opcao) {

        long inicio = System.nanoTime();
        String algoritmo;

        switch (opcao) {
            case "1" -> { BubbleSort.bubbleSort(vetor); algoritmo = "Bubble Sort"; }
            case "2" -> { BubbleSort.bubbleSortOtimizado(vetor); algoritmo = "Bubble Sort Otimizado"; }
            case "3" -> { SelectionSort.selectionSort(vetor); algoritmo = "Selection Sort"; }
            case "4" -> { SelectionSort.selectionSortEstavel(vetor); algoritmo = "Selection Sort Estável"; }
            case "5" -> { InsertionSort.insertionSort(vetor); algoritmo = "Insertion Sort"; }
            case "6" -> { MergeSort.mergeSort(vetor, 0, vetor.length - 1); algoritmo = "Merge Sort"; }
            case "7" -> { Arrays.sort(vetor); algoritmo = "Tim Sort"; }
            case "8" -> { QuickSort.quickSort(vetor, 0, vetor.length - 1); algoritmo = "Quick Sort"; }
            case "9" -> { CountingSort.countingSort(vetor, 0, 10); algoritmo = "Counting Sort"; }
            default -> { return vetor; }
        }

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1_000_000_000.0;

        System.out.printf("Ordenação concluída {%s}\n", algoritmo);
        System.out.printf("Tempo: %.6f segundos\n", tempo);
        System.out.println("Primeira posição: " + vetor[0]);
        System.out.println("Última posição: " + vetor[vetor.length - 1]);

        return vetor;
    }

    private String menuBusca() {
        if (!vetorCriado) {
            System.out.println("Nenhum vetor criado.");
            return null;
        }

        System.out.println("---- Algoritmos de Busca ----");
        System.out.println("1. Busca linear iterativa");
        System.out.println("2. Busca linear recursiva");
        System.out.println("3. Busca binária iterativa");
        System.out.println("4. Busca binária recursiva");
        System.out.println("5. Busca linear duas pontas");
        System.out.println("0. Sair");

        String opcao = sc.nextLine().trim();

        if (opcao.isEmpty() || !opcao.matches("[0-5]") || opcao.equals("0")) {
            System.out.println("Opção inválida");
            return null;
        }

        return opcao;
    }

    private Estudante executarBusca(Estudante[] vetor, String opcao) {
        int matricula = validarInteiro("Matrícula");

        return switch (opcao) {
            case "1" -> BuscaLinear.buscaLinearIterativa(vetor, matricula);
            case "2" -> BuscaLinear.buscaLinearRecursiva(vetor, matricula, 0);
            case "3" -> BuscaBinaria.buscaBinariaIterativa(vetor, matricula);
            case "4" -> BuscaBinaria.buscaBinariaRecursiva(vetor, matricula, 0, vetor.length - 1);
            case "5" -> BuscaLinearDuasPontas.buscaLinearDuasPontas(vetor, matricula);
            default -> null;
        };
    }

    private void exibirResultadoBusca(Estudante estudante) {
        if (estudante != null) {
            System.out.println("Estudante encontrado:");
            System.out.println(estudante);
        } else {
            System.out.println("Estudante não encontrado");
        }
    }

    private void statusVetor() {
        if (!vetorCriado) {
            System.out.println("Vetor inexistente");
        } else if (vetorOrdenado) {
            System.out.printf("Vetor original: %d elementos (por matrícula)\n", vetorEstudantes.length);
            System.out.printf("Vetor auxiliar: %d elementos (ordenado para análise)\n",
                    vetorEstudantesOrdenado.length);
        } else {
            System.out.printf("Vetor de tamanho %d (não ordenado para análise)\n", vetorEstudantes.length);
        }
    }

    private int validarInteiro(String msg) {
        int valor;
        do {
            System.out.printf("%s: ", msg);
            while (!sc.hasNextInt()) {
                System.out.print("Valor inválido. Digite um número inteiro: ");
                sc.next();
            }
            valor = sc.nextInt();
            sc.nextLine();
        } while (valor <= 0);
        return valor;
    }
}
