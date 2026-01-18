package sorting;

import model.Estudante;

public class MergeSort {
    public static void mergeSort(Estudante[] vetor, int left, int right) {
        if (left >= right) {
            return; //Caso base
        }

        int mid = (left + right) / 2;

        mergeSort(vetor, left, mid);
        mergeSort(vetor, mid + 1, right);
        merge(vetor, left, mid, right);
    }

    private static void merge(Estudante[] vetor, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Estudante[] leftArray = new Estudante[n1];
        Estudante[] rightArray = new Estudante[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = vetor[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = vetor[mid + 1 + i];
        }

        int i = 0, j = 0,  k = left;

        while(i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) < 0) {
                vetor[k] = leftArray[i];
                i++;
            } else {
                vetor[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i < n1) {
            vetor[k] = leftArray[i];
            i++;
            k++;
        }

        while(j < n2) {
            vetor[k] = rightArray[j];
            j++;
            k++;
        }
    }
}