package sorting;

import model.Estudante;

public class CountingSort {

    public static void countingSort(Estudante[] vetor, int notaMin, int notaMax) {
        int range = notaMax - notaMin + 1;

        int[] count = new int[range];

        //Contar as notas
        for (Estudante e : vetor) {
            count[e.getNota() - notaMin]++;
        }

        // 3) Soma cumulativa
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        Estudante[] ordenado = new Estudante[vetor.length];

        int n = vetor.length;

        //Posicionar os objetos (decrescente e estável)
        for (int i = n - 1; i >= 0; i--) {
            Estudante e = vetor[i];
            int chave = e.getNota() - notaMin;

            // posição como se fosse crescente
            int posCrescente = count[chave] - 1;

            // espelhamento para ordem decrescente
            int posDecrescente = (n - 1) - posCrescente;

            ordenado[posDecrescente] = e;
            count[chave]--;
        }

        //Copia de volta para o vetor original
        System.arraycopy(ordenado, 0, vetor, 0, n);
    }
}