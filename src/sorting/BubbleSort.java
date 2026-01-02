package sorting;

import model.Estudante;

public class BubbleSort {
    public static void bubbleSort(Estudante[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j].compareTo(vetor[j + 1]) > 0) {
                    Estudante aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }
    }

    public static void bubbleSortOtimizado(Estudante[] vetor) {
        int n = vetor.length;
        boolean trocou = false;

        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j].compareTo(vetor[j + 1]) > 0) {
                    Estudante aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
    }
}
