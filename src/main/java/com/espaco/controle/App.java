package com.espaco.controle;

import com.espaco.controle.classes.*;
import com.espaco.controle.persistencia.NitritePersistencia;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static NitritePersistencia db;

    public static void main(String[] args) {
        db = new NitritePersistencia("mission_control.db");
        System.out.println("--- CONTROLE ESPACIAL ---");

        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> menuMissoes();
                case "2" -> menuAstronautas();
                case "3" -> menuNaves();
                case "4" -> menuResultados();
                case "0" -> {
                    System.out.println("Encerrando...");
                    rodando = false;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
        db.fecharDB();
        scanner.close();
    }

    // --- METODO AUXILIAR ---
    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n    |MENU PRINCIPAL|");
        System.out.println("+-------------------------+");
        System.out.println("| 1. Controle Missoes     |");
        System.out.println("| 2. Controle Astronautas |");
        System.out.println("| 3. Controle Naves       |");
        System.out.println("| 4. Controle Resultados  |");
        System.out.println("| 0. Sair                 |");
        System.out.println("+-------------------------+");
        System.out.print("-> ");
    }

    // --- MISSOES ---
    private static void menuMissoes() {
        System.out.println("\n.------ MENU MISSOES --------.");
        System.out.println("| 1. Cadastrar              |");
        System.out.println("| 2. Listar                 |");
        System.out.println(".---------------------------.");        
        String op = lerTexto("Opcao -> ");
        System.out.println(" ");

        switch (op) {
            case "1" -> {
                String nome = lerTexto("Nome da Missao: ");
                String data = lerTexto("Data Lancamento ex:(2025-01-01): ");
                String dataRet = lerTexto("Data Retorno ex:(2025-01-01): ");
                
                Missao m = new Missao(nome, data, dataRet);
                m.setIdMissao(System.nanoTime());
                try {
                    db.salvar(m);
                    System.out.println("Missao salva.");
                } catch (UniqueConstraintException e) {
                    System.out.println("!: Nome ja cadastrado.");
                }
            }
            case "2" -> {
                List<Missao> lista = db.listarTodos(Missao.class);
                
                if (lista.isEmpty()) {
                    System.out.println("Nenhuma missao encontrada.");
                } else {
                    System.out.printf("%-15s | %-15s | %-12s | %-12s%n", "ID", "NOME", "LANCAMENTO", "RETORNO");
                    for (Missao m : lista) {
                        System.out.printf("%-15s | %-15s | %-12s | %-12s%n", 
                            m.getIdMissao() % 10000, m.getNome(), m.getDataLancamento(), m.getDataRetorno());
                    }
                }
            }
            default -> System.out.println("Opcao invalida.");
        }
    }

    // --- ASTRONAUTAS ---
    private static void menuAstronautas() {
        System.out.println("\n.----- MENU ASTRONAUTAS -----.");
        System.out.println("| 1. Cadastrar              |");
        System.out.println("| 2. Listar                 |");
        System.out.println(".---------------------------.");        
        String op = lerTexto("Opcao -> ");
        System.out.println(" ");

        switch (op) {
            case "1" -> {
                String nome = lerTexto("Nome: ");
                String esp = lerTexto("Especialidade: ");
                try {
                    db.salvar(new Astronauta(nome, esp));
                    System.out.println("Astronauta salvo.");
                } catch (UniqueConstraintException e) {
                    System.out.println("ERRO: Nome ja cadastrado.");
                }
            }
            case "2" -> {
                List<Astronauta> lista = db.listarTodos(Astronauta.class);
                
                if (lista.isEmpty()) {
                    System.out.println("Nenhum astronauta encontrado.");
                } else {
                    System.out.printf("%-20s | %-20s%n", "NOME", "ESPECIALIDADE");
                    for (Astronauta a : lista) {
                        System.out.printf("%-20s | %-20s%n", a.getNome(), a.getEspecialidade());
                    }
                }
            }
        }
    }

    // --- NAVES ---
    private static void menuNaves() {
        System.out.println("\n.----- MENU NAVES -----.");
        System.out.println("| 1. Cadastrar           |");
        System.out.println("| 2. Listar              |");
        System.out.println(".------------------------.");        
        String op = lerTexto("Opcao -> ");
        System.out.println(" ");

        switch (op) {
            case "1" -> {
                String nome = lerTexto("Nome da Nave: ");
                String modelo = lerTexto("Modelo: ");
                try {
                    db.salvar(new Nave(nome, modelo));
                    System.out.println("Nave salva.");
                } catch (UniqueConstraintException e) {
                    System.out.println("ERRO: Nome ja cadastrado.");
                }
            }
            case "2" -> {
                List<Nave> lista = db.listarTodos(Nave.class);
                
                if (lista.isEmpty()) {
                    System.out.println("Nenhuma nave encontrada.");
                } else {
                    System.out.printf("%-20s | %-20s%n", "NOME", "MODELO");
                    for (Nave n : lista) {
                        System.out.printf("%-20s | %-20s%n", n.getNome(), n.getModelo());
                    }
                }
            }
        }
    }

    // --- RESULTADOS ---
    private static void menuResultados() {
        System.out.println("\n.----- MENU RESULTADOS -----.");
        System.out.println("| 1. Registrar                |");
        System.out.println("| 2. Listar                   |");
        System.out.println(".-----------------------------.");        
        String op = lerTexto("Opcao -> ");
        System.out.println(" ");

        switch (op) {
            case "1" -> {
                String titulo = lerTexto("Titulo: ");
                String desc = lerTexto("Descricao: ");
                String missao = lerTexto("Missao Origem: ");
                db.salvar(new Resultado(titulo, desc, missao));
                System.out.println("Resultado salvo.");
            }
            case "2" -> {
                List<Resultado> lista = db.listarTodos(Resultado.class);
                
                if (lista.isEmpty()) {
                    System.out.println("Nenhum resultado encontrado.");
                } else {
                    for (Resultado r : lista) {
                        System.out.println("Titulo: " + r.getTitulo() + " (Missao: " + r.getNomeMissaoVinculada() + ")");
                        System.out.println("Desc: " + r.getDescricao());
                        System.out.println("-");
                    }
                }
            }
        }
    }
}