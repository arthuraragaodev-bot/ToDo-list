package org.arthur.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dados<T> {
    private Path arquivo = Paths.get("tarefas.json");

    public Dados() throws IOException {
        if(Files.notExists(arquivo)) {
            Files.createFile(arquivo);
        }
    }

    public void salvarObjetosEmJson(List<T> lista) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Files.delete(arquivo);
        Files.createFile(arquivo);
        for (T tipoGenerico: lista) {
            objectMapper.writeValue(arquivo.toFile(),tipoGenerico);
        }
    }

    public List<T> carregarObjetosJsonEmUmaLista(Class<T> classe) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> linhas = Files.readAllLines(arquivo);
        List<T> lista = new ArrayList<>();
        for(String linha : linhas) {
            lista.add(objectMapper.readValue(linha,classe));
        }
        return lista;
    }
}
