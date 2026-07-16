package org.arthur.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Dados {
    private Path arquivo = Paths.get("tarefas.json");

    public Dados() throws IOException {
        if(Files.notExists(arquivo)) {
            Files.createFile(arquivo);
        }
    }

    public void salvarTarefas(List<Tarefa> tarefas) throws IOException {
        Files.delete(arquivo);
        Files.createFile(arquivo);
        ObjectMapper objectMapper = new ObjectMapper();
        for (Tarefa tarefa: tarefas) {
            objectMapper.writeValue(arquivo.toFile(),tarefa);
        }
    }

    public List<Tarefa> carregarTarefas() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Tarefa> tarefas = new ArrayList<>();
        Tarefa tarefa = objectMapper.readValue(arquivo.toFile(),Tarefa.class);
        tarefas.add(tarefa);
        return tarefas;
    }
}
