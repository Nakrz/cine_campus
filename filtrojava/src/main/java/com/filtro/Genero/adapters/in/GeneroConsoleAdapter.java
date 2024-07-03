package com.filtro.Genero.adapters.in;

import com.filtro.Genero.domain.Genero;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;
import com.filtro.Genero.application.GeneroService;

public class GeneroConsoleAdapter {

    Scanner sc = new Scanner(System.in);

    private final GeneroService generoService;

    public GeneroConsoleAdapter(GeneroService generoService) {
        this.generoService = generoService;
    }

    public void startGenero() {
        String option;
        do {
            displayMenu();
            option = sc.nextLine();

            switch (option) {
                case "1":
                    createGenero();
                    break;
                case "2":
                    searchGenero();
                    break;
                case "3":
                    updateGenero();
                    break;
                case "4":
                    deleteGenero();
                    break;
                case "5":
                    getAllGeneros();
                    break;
                case "0":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (!option.equals("0"));
    }

    private void displayMenu() {
        System.out.println("\n*************** MENÚ ***************");
        System.out.println("1. Registrar Género");
        System.out.println("2. Buscar Género por ID");
        System.out.println("3. Actualizar Género por ID");
        System.out.println("4. Eliminar Género por ID");
        System.out.println("5. Listar Todos los Géneros");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void createGenero() {
        String rta;
        do {
            System.out.println("\n*************** REGISTRAR GÉNERO ***************");
            System.out.print("[*] INGRESE EL ID DEL GÉNERO A CREAR: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume la nueva línea pendiente

            Optional<Genero> existingGenero = generoService.findGeneroById(id);
            if (existingGenero.isPresent()) {
                System.out.println(MessageFormat.format("[!] EL ID ({0}) YA ESTÁ OCUPADO.", id));
            } else {
                System.out.print("[*] INGRESE LA DESCRIPCIÓN DEL GÉNERO: ");
                String generoDescripcion = sc.nextLine();

                Genero newGenero = new Genero(id, generoDescripcion);
                generoService.createGenero(newGenero);
                System.out.println("[*] Género registrado exitosamente.");
            }

            System.out.print("[?] DESEA AÑADIR OTRO GÉNERO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO: ");
            rta = sc.nextLine();
        } while (rta.equalsIgnoreCase("S"));
    }

    public void searchGenero() {
        System.out.print("\n[?] INGRESE EL ID DEL GÉNERO A BUSCAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
                g -> System.out.println("[*] ID: " + g.getId() + " | Descripción: " + g.getDescripcion()),
                () -> System.out.println("[!] GÉNERO NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updateGenero() {
        System.out.print("\n[*] INGRESE EL ID DEL GÉNERO A EDITAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
                g -> {
                    System.out.println("[*] ID: " + g.getId() + " | Descripción: " + g.getDescripcion());
                    System.out.print("[*] INGRESE LA NUEVA DESCRIPCIÓN: ");
                    String updateDescripcion = sc.nextLine();

                    Genero updatedGenero = new Genero(g.getId(), updateDescripcion);
                    generoService.updateGenero(updatedGenero);
                    System.out.println("[*] Género actualizado exitosamente.");
                },
                () -> System.out.println("[!] GÉNERO NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void deleteGenero() {
        System.out.print("\n[*] INGRESE EL ID DEL GÉNERO A ELIMINAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
                g -> {
                    generoService.deleteGenero(findId);
                    System.out.println("[*] Género eliminado correctamente.");
                },
                () -> System.out.println("[!] GÉNERO NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllGeneros() {
        System.out.println("\n*************** LISTADO DE GÉNEROS ***************");
        generoService.findAllGeneros().forEach(g -> {
            System.out.println("[*] ID: " + g.getId() + " | Descripción: " + g.getDescripcion());
        });
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

}



