package searching;

import model.Estudante;

public class BuscaLinear {
    public static Estudante buscaLinearIterativa(Estudante[] vetor, int matricula){
        int n = vetor.length;

        for (Estudante estudante : vetor) {
            if (estudante.getMatricula() == matricula) {
                return estudante;
            }
        }
        return null;
    }

    public static Estudante buscaLinearRecursiva(Estudante[] vetor, int matricula, int indice){
        if (indice >= vetor.length) {
            return null;
        }

        if (vetor[indice].getMatricula() == matricula) {
            return vetor[indice];
        }

        return buscaLinearRecursiva(vetor, matricula, indice + 1);
    }
}
