package org.arthur.java;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private String oqueFazer;
    private LocalTime horarioParaConcluir;

    public Tarefa(String tarefa,LocalTime horarioParaConcluir) {
        oqueFazer = tarefa;
        this.horarioParaConcluir = horarioParaConcluir;
    }

    public void setHorarioParaConcluir(LocalTime horario) {
        this.horarioParaConcluir = horario;
    }

    public void setOqueFazer(String oqueFazer) {
        this.oqueFazer = oqueFazer;
    }

    public String getHorarioParaConcluir() {
        return horarioParaConcluir.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getOqueFazer() {
        return oqueFazer;
    }

    @Override
    public String toString() {
        return "----------Tarefa----------" + "\n" + getOqueFazer() + "\n" + getHorarioParaConcluir() + "\n" + "----------Tarefa--------";
    }

}
