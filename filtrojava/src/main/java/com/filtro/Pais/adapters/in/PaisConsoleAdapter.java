package com.filtro.Pais.adapters.in;

import com.filtro.Pais.application.PaisService;
import com.filtro.Pais.domain.Pais;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

public class PaisConsoleAdapter {

    private final PaisService paisService;
    private final Scanner sc;

    public PaisConsoleAdapter(PaisService paisService) {
        this.paisService = paisService;
        this.sc = new Scanner(System.in);
    }

    public void startPais() {
        String option;
        do {
            displayMenu();
            option = sc.nextLine();

            switch (option) {
                case "1":
                    createPais();
                    break;
                case "2":
                    searchPais();
                    break;
                case "3":
                    updatePais();
                    break;
                case "4":
                    deletePais();
                    break;
                case "5":
                    getAllPaises();
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
        System.out.println("1. Registrar País");
        System.out.println("2. Buscar País por ID");
        System.out.println("3. Actualizar País por ID");
        System.out.println("4. Eliminar País por ID");
        System.out.println("5. Listar Todos los Países");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void createPais() {
        String rta;
        do {
            System.out.println("\n*************** REGISTRAR PAÍS ***************");
            System.out.print("[*] INGRESE EL ID DEL PAÍS A CREAR: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume la nueva línea pendiente

            Optional<Pais> existingPais = paisService.getPaisById(id);
            if (existingPais.isPresent()) {
                System.out.println(MessageFormat.format("[!] EL ID ({0}) YA ESTÁ OCUPADO.", id));
            } else {
                System.out.print("[*] INGRESE EL NOMBRE DEL PAÍS: ");
                String nombre = sc.nextLine();

                Pais newPais = new Pais(id, nombre);
                paisService.createPais(newPais);
                System.out.println("[*] País registrado exitosamente.");
            }

            System.out.print("[?] DESEA AÑADIR OTRO PAÍS? [S] - SI | [INGRESE CUALQUIER TECLA] - NO: ");
            rta = sc.nextLine();
        } while (rta.equalsIgnoreCase("S"));
    }

    public void searchPais() {
        System.out.print("\n[?] INGRESE EL ID DEL PAÍS A BUSCAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Pais> pais = paisService.getPaisById(findId);
        pais.ifPresentOrElse(
                p -> System.out.println("[*] ID: " + p.getId() + " | Nombre: " + p.getDescripcion()),
                () -> System.out.println("[!] PAÍS NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updatePais() {
        System.out.print("\n[*] INGRESE EL ID DEL PAÍS A EDITAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Pais> pais = paisService.getPaisById(findId);
        pais.ifPresentOrElse(
                p -> {
                    System.out.println("[*] ID: " + p.getId() + " | Nombre: " + p.getDescripcion());
                    System.out.print("[*] INGRESE EL NUEVO NOMBRE: ");
                    String updateNombre = sc.nextLine();

                    Pais updatedPais = new Pais(findId, updateNombre);
                    paisService.updatePais(updatedPais);
                    System.out.println("[*] País actualizado exitosamente.");
                },
                () -> System.out.println("[!] PAÍS NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void deletePais() {
        System.out.print("\n[*] INGRESE EL ID DEL PAÍS A ELIMINAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); // Consume la nueva línea pendiente

        Optional<Pais> pais = paisService.getPaisById(findId);
        pais.ifPresentOrElse(
                p -> {
                    paisService.deletePais(findId);
                    System.out.println("[*] País eliminado correctamente.");
                },
                () -> System.out.println("[!] PAÍS NO ENCONTRADO")
        );
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllPaises() {
        System.out.println("\n*************** LISTADO DE PAÍSES ***************");
        paisService.getAllPaises().forEach(p -> {
            System.out.println("[*] ID: " + p.getId() + " | Nombre: " + p.getDescripcion());
        });
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
