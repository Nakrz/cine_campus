package com.filtro.Pelicula.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.filtro.Pelicula.application.PeliculaService;
import com.filtro.Pelicula.domain.Pelicula;

public class PeliculaConsoleAdapter {

    private final Scanner sc = new Scanner(System.in);
    private final PeliculaService peliculaService;

    public PeliculaConsoleAdapter(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    public void startPelicula() {
        String option;
        do {
            displayMenu();
            option = sc.nextLine();

            switch (option) {
                case "1":
                    createPelicula();
                    break;
                case "2":
                    searchPeliculaById();
                    break;
                case "3":
                    updatePelicula();
                    break;
                case "4":
                    deletePelicula();
                    break;
                case "5":
                    listAllPeliculas();
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
        System.out.println("\n*************** MENÚ PELÍCULAS ***************");
        System.out.println("1. Registrar Película");
        System.out.println("2. Buscar Película por ID");
        System.out.println("3. Actualizar Película por ID");
        System.out.println("4. Eliminar Película por ID");
        System.out.println("5. Listar Todas las Películas");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }

    private void createPelicula() {
        System.out.println("\n*************** REGISTRAR PELÍCULA ***************");
        System.out.print("[*] Ingrese el código interno de la película: ");
        String codinterno = sc.nextLine();

        System.out.print("[*] Ingrese el nombre de la película: ");
        String nombre = sc.nextLine();

        System.out.print("[*] Ingrese la duración de la película (en minutos o HH:MM): ");
        String duracion = sc.nextLine();

        System.out.print("[*] Ingrese la sinopsis de la película: ");
        String sinopsis = sc.nextLine();

        Pelicula nuevaPelicula = new Pelicula(0, codinterno, nombre, duracion, sinopsis);
        peliculaService.createPelicula(nuevaPelicula);
        System.out.println("[*] Película registrada exitosamente.");
    }

    private void searchPeliculaById() {
        System.out.print("\n[*] Ingrese el ID de la película a buscar: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        Optional<Pelicula> peliculaOptional = peliculaService.findPeliculaById(id);
        if (peliculaOptional.isPresent()) {
            Pelicula pelicula = peliculaOptional.get();
            System.out.println("[*] Película encontrada:");
            System.out.println("    ID: " + pelicula.getId());
            System.out.println("    Código Interno: " + pelicula.getCodinterno());
            System.out.println("    Nombre: " + pelicula.getNombre());
            System.out.println("    Duración: " + pelicula.getDuracion());
            System.out.println("    Sinopsis: " + pelicula.getSinopsis());
        } else {
            System.out.println("[!] Película no encontrada.");
        }
    }

    private void updatePelicula() {
        System.out.print("\n[*] Ingrese el ID de la película a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        Optional<Pelicula> peliculaOptional = peliculaService.findPeliculaById(id);
        if (peliculaOptional.isPresent()) {
            Pelicula pelicula = peliculaOptional.get();
            System.out.println("[*] Película actual:");
            System.out.println("    ID: " + pelicula.getId());
            System.out.println("    Código Interno: " + pelicula.getCodinterno());
            System.out.println("    Nombre: " + pelicula.getNombre());
            System.out.println("    Duración: " + pelicula.getDuracion());
            System.out.println("    Sinopsis: " + pelicula.getSinopsis());

            System.out.println("\n[*] Ingrese los nuevos datos:");

            System.out.print("[*] Nuevo código interno (actual: " + pelicula.getCodinterno() + "): ");
            String nuevoCodinterno = sc.nextLine();

            System.out.print("[*] Nuevo nombre (actual: " + pelicula.getNombre() + "): ");
            String nuevoNombre = sc.nextLine();

            System.out.print("[*] Nueva duración (actual: " + pelicula.getDuracion() + "): ");
            String nuevaDuracion = sc.nextLine();

            System.out.print("[*] Nueva sinopsis (actual: " + pelicula.getSinopsis() + "): ");
            String nuevaSinopsis = sc.nextLine();

            Pelicula peliculaActualizada = new Pelicula(id, nuevoCodinterno, nuevoNombre, nuevaDuracion, nuevaSinopsis);
            peliculaService.updatePelicula(peliculaActualizada);
            System.out.println("[*] Película actualizada exitosamente.");
        } else {
            System.out.println("[!] Película no encontrada.");
        }
    }

    private void deletePelicula() {
        System.out.print("\n[*] Ingrese el ID de la película a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        Optional<Pelicula> peliculaOptional = peliculaService.findPeliculaById(id);
        if (peliculaOptional.isPresent()) {
            peliculaService.deletePelicula(id);
            System.out.println("[*] Película eliminada correctamente.");
        } else {
            System.out.println("[!] Película no encontrada.");
        }
    }

    private void listAllPeliculas() {
        System.out.println("\n*************** LISTADO DE PELÍCULAS ***************");
        List<Pelicula> peliculas = peliculaService.findAllPeliculas();
        peliculas.forEach(pelicula -> {
            System.out.println("ID: " + pelicula.getId());
            System.out.println("Código Interno: " + pelicula.getCodinterno());
            System.out.println("Nombre: " + pelicula.getNombre());
            System.out.println("Duración: " + pelicula.getDuracion());
            System.out.println("Sinopsis: " + pelicula.getSinopsis());
            System.out.println("-----------------------------------------");
        });
    }
}