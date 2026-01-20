package sorting;

import model.Estudante;

public class QuickSort {
    public static void quickSort(Estudante[] vetor, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }

        int pivoIndex = partition(vetor, inicio, fim);

        quickSort(vetor, inicio, pivoIndex - 1);
        quickSort(vetor, pivoIndex + 1, fim);
    }

    private static int partition(Estudante[] vetor, int inicio, int fim) {
        Estudante pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++){
            if (vetor[j].compareTo(pivo) <= 0) {
                i++;
                swap(vetor, i, j);
            }
        }

        swap(vetor, i + 1, fim);
        return i + 1;
    }

    private static void swap(Estudante[] vetor, int i, int j) {
        Estudante aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}
