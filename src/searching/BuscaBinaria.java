package searching;

import model.Estudante;

public class BuscaBinaria {
    public static Estudante buscaBinariaIterativa(Estudante[] vetor, int matricula){
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (vetor[meio].getMatricula() == matricula){
                return vetor[meio];
            }

            else if(vetor[meio].getMatricula() < matricula){
                inicio = meio + 1;
            }
            else{
                fim = meio - 1;
            }
        }

        return null;
    }

    public static Estudante buscaBinariaRecursiva (Estudante[] vetor, int matricula, int inicio, int fim){
        if (inicio > fim) {
            return null;
        }

        int meio = (inicio + fim) / 2;

        if(vetor[meio].getMatricula() == matricula){
            return vetor[meio];
        }

        else if(vetor[meio].getMatricula() < matricula){
            return buscaBinariaRecursiva(vetor,matricula, meio + 1, fim);
        }
        else{
            return buscaBinariaRecursiva(vetor, matricula, inicio, meio - 1);
        }
    }
}
