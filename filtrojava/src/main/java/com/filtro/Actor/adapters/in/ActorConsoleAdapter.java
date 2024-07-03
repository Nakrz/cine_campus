package com.filtro.Actor.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.filtro.Actor.application.ActorService;
import com.filtro.Actor.domain.Actor;
import com.filtro.Genero.domain.Genero;
import com.filtro.Pais.domain.Pais;

public class ActorConsoleAdapter {

    private final ActorService actorService;
    private final Scanner sc;

    public ActorConsoleAdapter(ActorService actorService) {
        this.actorService = actorService;
        this.sc = new Scanner(System.in);
    }

    public void starActor() {
        String option;
        do {
            displayMenu();
            option = sc.nextLine();

            switch (option) {
                case "1":
                    createActor();
                    break;
                case "2":
                    searchActor();
                    break;
                case "3":
                    updateActor();
                    break;
                case "4":
                    deleteActor();
                    break;
                case "5":
                    getAllActores();
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
        System.out.println("1. Registrar Actor");
        System.out.println("2. Buscar Actor por ID");
        System.out.println("3. Actualizar Actor por ID");
        System.out.println("4. Eliminar Actor por ID");
        System.out.println("5. Listar Todos los Actores");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }


//Creacion de actor 
    private void createActor() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
    
        do {
            System.out.println("\n*************** REGISTRAR ACTOR ***************");
            System.out.print("[*] INGRESE EL ID DEL ACTOR A CREAR: ");
            int id = sc.nextInt();
            sc.nextLine(); 
    
            Optional<Actor> existingActor = actorService.findActorById(id);
            if (existingActor.isPresent()) {
                System.out.println("[!] EL ID (" + id + ") YA ESTÁ OCUPADO.");
                continue; 
            }
    
            System.out.print("[*] INGRESE EL NOMBRE DEL ACTOR: ");
            String nombre = sc.nextLine();


            //Lista los paises
            List<Pais> paises = actorService.getAllPaises();
            if (paises.isEmpty()) {
                System.out.println("[!] No hay países registrados. Por favor registre un país antes de continuar.");
                continuar = false;
                break; 
            }
    
            System.out.println("\n*************** LISTADO DE PAÍSES ***************");
            for (Pais pais : paises) {
                System.out.println("[*] ID: " + pais.getId() + " | Nombre: " + pais.getDescripcion());
            }
    
            System.out.print("[*] INGRESE LA ID DE NACIONALIDAD DEL ACTOR: ");
            int idNacionalidad = sc.nextInt();
            sc.nextLine(); 
    
            Optional<Pais> paisOptional = actorService.getPaisById(idNacionalidad);
            if (!paisOptional.isPresent()) {
                System.out.println("[!] La ID de país ingresada no es válida. Intente de nuevo.");
                continuar = false;
                break; 
            }
    
            //Lista generos 
            List<Genero> generos = actorService.findAllGeneros();
            if (generos.isEmpty()) {
                System.out.println("[!] No hay géneros registrados. Por favor registre un género antes de continuar.");
                continuar = false;
                break; 
            }
    
            System.out.println("\n*************** LISTADO DE GÉNEROS ***************");
            for (Genero genero : generos) {
                System.out.println("[*] ID: " + genero.getId() + " | Nombre: " + genero.getDescripcion());
            }
    
            System.out.print("[*] INGRESE LA ID DEL GÉNERO DEL ACTOR: ");
            int idGenero = sc.nextInt();
            sc.nextLine(); 
    
            Optional<Genero> generoOptional = actorService.findGeneroById(idGenero);
            if (!generoOptional.isPresent()) {
                System.out.println("[!] La ID de género ingresada no es válida. Intente de nuevo.");
                continuar = false;
                break; 
            }
    
            System.out.print("[*] INGRESE LA EDAD DEL ACTOR: ");
            int edad = sc.nextInt();
            sc.nextLine(); 
    
            Actor newActor = new Actor(id, nombre, idNacionalidad, edad, idGenero);
            actorService.createActor(newActor);
            System.out.println("[*] Actor registrado exitosamente.");
    
            System.out.print("[?] DESEA AÑADIR OTRO ACTOR? [S] - SI | [INGRESE CUALQUIER TECLA] - NO: ");
            String respuesta = sc.nextLine().trim();
            if (!respuesta.equalsIgnoreCase("S")) {
                continuar = false;
            }
    
        } while (continuar);

    }

    //Buscar  actor

    public void searchActor() {
        System.out.print("\n[?] INGRESE EL ID DEL ACTOR A BUSCAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); 

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
                a -> System.out.println("[*] ID: " + a.getId() + " | Nombre: " + a.getNombre() + " | Nacionalidad: "
                        + a.getIdNacionalidad() + " | Edad: " + a.getEdad() + " | Género: " + a.getIdGenero()),
                () -> System.out.println("[!] ACTOR NO ENCONTRADO"));
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    //Actualizar actor
    public void updateActor() {
        System.out.print("\n[*] INGRESE EL ID DEL ACTOR A EDITAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); 

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
                a -> {
                    System.out.println("[*] ID: " + a.getId() + " | Nombre: " + a.getNombre() + " | Nacionalidad: "
                            + a.getIdNacionalidad() + " | Edad: " + a.getEdad() + " | Género: " + a.getIdGenero());
                    System.out.print("[*] INGRESE EL NUEVO NOMBRE: ");
                    String updateNombre = sc.nextLine();

                    System.out.print("[*] INGRESE EL NUEVO ID DE NACIONALIDAD: ");
                    int updateIdNacionalidad = sc.nextInt();

                    System.out.print("[*] INGRESE LA NUEVA EDAD: ");
                    int updateEdad = sc.nextInt();

                    System.out.print("[*] INGRESE EL NUEVO ID DE GÉNERO: ");
                    int updateIdGenero = sc.nextInt();

                    Actor updatedActor = new Actor(findId, updateNombre, updateIdNacionalidad, updateEdad,
                            updateIdGenero);
                    actorService.updateActor(updatedActor);
                    System.out.println("[*] Actor actualizado exitosamente.");
                },
                () -> System.out.println("[!] ACTOR NO ENCONTRADO"));
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }


    //Elimina un actor
    public void deleteActor() {
        System.out.print("\n[*] INGRESE EL ID DEL ACTOR A ELIMINAR: ");
        int findId = sc.nextInt();
        sc.nextLine(); 

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
                a -> {
                    actorService.deleteActor(findId);
                    System.out.println("[*] Actor eliminado correctamente.");
                },
                () -> System.out.println("[!] ACTOR NO ENCONTRADO"));
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    //Lista los Actores 
    public void getAllActores() {
        System.out.println("\n*************** LISTADO DE ACTORES ***************");
        actorService.findAllActores().forEach(a -> {
            System.out.println("[*] ID: " + a.getId() + " | Nombre: " + a.getNombre() + " | Nacionalidad: "
                    + a.getIdNacionalidad() + " | Edad: " + a.getEdad() + " | Género: " + a.getIdGenero());
        });
        System.out.print("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
