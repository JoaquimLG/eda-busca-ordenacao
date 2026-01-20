package searching;

import model.Estudante;

public class BuscaLinearDuasPontas {
    public static Estudante buscaLinearDuasPontas(Estudante[] vetor,  int matricula){
        int inicio = 0;
        int fim = vetor.length - 1;

        while(inicio <= fim) {

            if(vetor[inicio].getMatricula() == matricula){
                return vetor[inicio];
            }

            if (vetor[fim].getMatricula() == matricula){
                return vetor[fim];
            }

            inicio++;
            fim--;
        }

        return null;
    }
}
