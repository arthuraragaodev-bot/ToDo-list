package org.arthur.java;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] argumentos) throws IOException {
        Scanner input = new Scanner(System.in);
        Dados dados = new Dados();
        List<Tarefa> tarefasCarregadas = dados.carregarTarefas();


        boolean loop = true;
        while (loop) {
            System.out.print("Menu Tarefas\n[1] criar tarefa\n[2] editar Tarefa\n[3] apagar Tarefa\n[4] mostrar tarefas\n[5] sair\n:");
            String escolha = input.nextLine();

            switch(escolha) {
                default -> System.out.println("escolha invalida!");
                case "1" -> {
                    System.out.print("Digite a tarefa: ");

                    String tarefaASerFeita = input.nextLine();
                    System.out.print("Digite o horario a ser realizada: ");
                    String horario = input.nextLine();
                    try {
                        Tarefa tarefa = new Tarefa(tarefaASerFeita, LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm")));
                        tarefasCarregadas.add(tarefa);
                        dados.salvarTarefa(tarefa);
                    } catch (DateTimeParseException e) {
                        System.out.println("Horario Invalido!");
                    }
                }

                case "2" -> {
                    System.out.print("Digite o id da tarefa a ser atualizada: ");
                    String id = input.nextLine();
                    System.out.print("[1] tarefa\n[2] horario\n:");
                    String escolhaEditar = input.nextLine();
                    switch (escolhaEditar) {
                        case "1" -> {
                            System.out.print("digite a tarefa corrigida: ");
                            String oqueFazer = input.nextLine();
                            try {
                                tarefasCarregadas.get(Integer.parseInt(id) - 1).setOqueFazer(oqueFazer);
                                dados.atualizarTarefa(Integer.parseInt(id), tarefasCarregadas.get(Integer.parseInt(id)));
                            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                                System.out.println("Id invalido");
                            }
                        }
                        case "2" -> {
                            System.out.print("digite o horario da tarefa: ");

                            String horario = input.nextLine();

                            try {
                                Tarefa tarefa = tarefasCarregadas.get(Integer.parseInt(id) - 1);
                                tarefa.setHorarioParaConcluir(LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm")));
                                dados.atualizarTarefa(Integer.parseInt(id) - 1, tarefa);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("id invalido");
                            } catch (DateTimeParseException e) {
                                System.out.println("data invalida");
                            } catch (Exception e) {
                                System.out.println("id invalido");
                            }
                        }
                    }
                }
                    case "3" -> {
                        System.out.print("id:");
                        String id = input.nextLine();
                        try {
                            tarefasCarregadas.remove(Integer.parseInt(id) - 1);
                            dados.deletarTarefa(Integer.parseInt(id) - 1);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Id invalido");
                        } catch (Exception parseException) {
                            System.out.println("Id invalido");
                        }
                    }
                    case "4" -> {
                        int contador = 1;
                        for (Tarefa tarefa : tarefasCarregadas) {
                            System.out.println(contador + "|" + tarefa);
                            contador++;
                        }
                    }
                    case "5" -> {
                        loop = false;
                    }
            }
        }
        input.close();
    }
}