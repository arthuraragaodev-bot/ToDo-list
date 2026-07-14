package org.arthur.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dados {
    private Path arquivo = Paths.get("tarefas.txt");

    public Dados() throws IOException {
        if(Files.notExists(arquivo)) {
            Files.createFile(arquivo);
        }
    }

    public void salvarTarefa(Tarefa tarefa) throws IOException {
        Files.writeString(arquivo,tarefa.getOqueFazer() + ";" + tarefa.getHorarioParaConcluir() + System.lineSeparator(), StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);
    }

    public void atualizarTarefa(int id,Tarefa tarefa) throws IOException {
        List<String> linhas = Files.readAllLines(arquivo,StandardCharsets.ISO_8859_1);
        if(linhas.size() >= id) {
            linhas.remove(id );
            linhas.add(id, tarefa.getOqueFazer() + ";" + tarefa.getHorarioParaConcluir());
            Files.writeString(arquivo,linhas.get(0) + System.lineSeparator(),StandardCharsets.ISO_8859_1,StandardOpenOption.WRITE);
            linhas.remove(0);
            for(String linha : linhas) {
                Files.writeString(arquivo,linha + System.lineSeparator(),StandardCharsets.ISO_8859_1,StandardOpenOption.APPEND);
            }
        }
    }

    public void deletarTarefa(int id) throws IOException{
        List<String> linhas = Files.readAllLines(arquivo);
        int idVerdadeiro = id;
        if(linhas.size() >= idVerdadeiro) {
            linhas.remove(idVerdadeiro);
            Files.writeString(arquivo,linhas.get(0) + System.lineSeparator(),StandardCharsets.ISO_8859_1,StandardOpenOption.WRITE);
            for (String linha : linhas) {
                Files.writeString(arquivo,linha + System.lineSeparator(),StandardCharsets.ISO_8859_1,StandardOpenOption.APPEND);
            }
        }
    }

    public List<Tarefa> carregarTarefas() throws IOException {
        List<String> linhas = Files.readAllLines(arquivo,StandardCharsets.ISO_8859_1);
        List<Tarefa> tarefasCarregadas = new ArrayList<>();
        for(String linha : linhas) {
            String[] dadosTarefa = linha.split(";");
            tarefasCarregadas.add(new Tarefa(dadosTarefa[0], LocalTime.parse(dadosTarefa[1], DateTimeFormatter.ofPattern("HH:mm"))));
        }
        return tarefasCarregadas;
    }
}
