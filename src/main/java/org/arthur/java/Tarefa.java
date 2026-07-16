package org.arthur.java;



public record Tarefa(String oqueFazer) {

    @Override
    public String toString() {
        return "----------Tarefa----------" + "\n" + oqueFazer + "\n" + "----------Tarefa--------";
    }
}
