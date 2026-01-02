package model;

public class Estudante implements Comparable<Estudante>{
    private final String nome;
    private final int nota;
    private final int matricula;

    public Estudante(String nome, int nota, int matricula) {
        this.nome = nome;
        this.nota = nota;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getNota() {
        return nota;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public int compareTo(Estudante outro) {

        //1. Nota decrescente
        if (this.nota != outro.nota){
            return Integer.compare(outro.nota, this.nota);
        }

        //2. Nome crescente
        int comparacaoNome = this.nome.compareTo(outro.nome);
        if(comparacaoNome != 0){
            return comparacaoNome;
        }

        //3. Matricula crescente
        return this.matricula - outro.matricula;
    }

    @Override
    public String toString() {
        return String.format("%s (Matrícula: %d, Nota: %d)", nome, matricula, nota);
    }
}
