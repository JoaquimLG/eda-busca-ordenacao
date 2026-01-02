package sorting;

import model.Estudante;

public class SelectionSort {
    public static void selectionSort(Estudante[] vetor) {
        int n = vetor.length;

        for(int i = 0; i < n - 1; i++) {
            int indiceMin = i;

            for (int j = i + 1; j < n; j++) {
                if (vetor[j].compareTo(vetor[indiceMin]) < 0) {
                    indiceMin = j;
                }
            }

            if(indiceMin != i) {
                Estudante aux = vetor[i];
                vetor[i] = vetor[indiceMin];
                vetor[indiceMin] = aux;
            }
        }
    }

    public static void selectionSortEstavel(Estudante[] vetor) {
        int n = vetor.length;

        for(int   i = 0; i < n - 1; i++) {
            int indiceMin = i;

            for(int j = i + 1; j < n; j++) {
                if(vetor[j].compareTo(vetor[indiceMin]) < 0) {
                    indiceMin = j;
                }
            }

            Estudante minimo =  vetor[indiceMin];

            while (indiceMin > i) {
                vetor[indiceMin] = vetor[indiceMin - 1];
                indiceMin--;
            }
            vetor[i] = minimo;
        }
    }
}
