package util;

import model.Estudante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeDados {

    private static final Random random = new Random();

    //Lidos apenas uma vez
    private static final List<String> NOMES = carregarDados("nomes.txt");
    private static final List<String> SOBRENOMES = carregarDados("sobrenomes.txt");

    private GeradorDeDados() {
    }

    private static List<String> carregarDados(String nomeArquivo) {
        List<String> dados = new ArrayList<>();
        InputStream is = GeradorDeDados.class.getResourceAsStream(nomeArquivo);

        if (is == null) {
            System.err.println("Arquivo não encontrado no pacote: " + nomeArquivo);
            return dados;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    dados.add(linha.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + nomeArquivo);
        }

        return dados;
    }

    private static String gerarNomeAleatorio() {
        if (NOMES.isEmpty() || SOBRENOMES.isEmpty()) {
            throw new IllegalStateException("Listas de nomes ou sobrenomes estão vazias.");
        }

        String nome = NOMES.get(random.nextInt(NOMES.size()));
        String sobrenome = SOBRENOMES.get(random.nextInt(SOBRENOMES.size()));

        return nome + " " + sobrenome;
    }

    public static Estudante[] gerarEstudantes(int tamanho) {
        Estudante[] estudantes = new Estudante[tamanho];

        for (int i = 0; i < tamanho; i++) {
            String nome = gerarNomeAleatorio();
            int nota = random.nextInt(0, 11);
            int matricula = i + 1;

            estudantes[i] = new Estudante(nome, nota, matricula);
        }

        return estudantes;
    }
}
