package sorting;

import model.Estudante;

public class InsertionSort {
    public static void insertionSort(Estudante[] vetor){
        int n = vetor.length;

        for (int i = 1; i < n; i++) {
            Estudante chave = vetor[i];
            int j = i - 1;

            while(j >= 0 && vetor[j].compareTo(chave) > 0){
                vetor[j+1] = vetor[j];
                j--;
            }

            vetor[j+1] = chave;
        }
    }
}
