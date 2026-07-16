package org.arthur.java;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record Tarefa(String oqueFazer) {

    @Override
    public String toString() {
        return "----------Tarefa----------" + "\n" + oqueFazer + "\n" + "----------Tarefa--------";
    }
}
