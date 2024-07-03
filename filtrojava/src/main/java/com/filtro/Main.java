package com.filtro;

import java.util.Scanner;
import com.filtro.Actor.application.ActorService;
import com.filtro.Actor.adapters.in.ActorConsoleAdapter;
import com.filtro.Actor.adapters.out.ActorMySQLRepository;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        String url = "jdbc:mysql://localhost:3306/cinecampus";
        String user = "root";
        String password = "123456";

        ActorMySQLRepository actorMySQLRepository = new ActorMySQLRepository(url, user, password);

    
        boolean bandera = true;
            while (bandera){
                System.out.println("\n\n\nBIENVENIDO AL CINE CAMPUS :");
                System.out.println("---------------------------------------");
                System.out.println("");
                System.out.println("1. Agregar Actor");
                System.out.println("2. Editar Actor");
                System.out.println("3. Eliminar Actor");
                System.out.println("4. Listar Actores");
                System.out.println("5. Gestion de Generos");
                System.out.println("6. Gestion de Paises");
                System.out.println("7. Gestion Tipo de Actores");
                System.out.println("8. Consultar Informacion de una pelicula");
                System.out.println("9. Listar Actores por Peliculas ");
                System.out.println("10. Salir");
                System.out.println("---------------------------------------");
                System.out.println("Elija una opcion que desea realizar: ");
                int opcion = sc.nextInt();
            
                switch (opcion) {
                    case 1:
                        System.out.println("GESTION DE ACTORES");
                        
                        ActorService actorService = new ActorService(actorMySQLRepository);
                        ActorConsoleAdapter actorConsoleAdapter = new ActorConsoleAdapter(actorService);
                        actorConsoleAdapter.createActor();

                        break;
                    // case 2:
                    //     System.out.println("Editar Actor");
                    //         ActorService actorService = new ActorService(actorMySQLRepository);
                    //         ActorConsoleAdapter actorConsoleAdapter = new ActorConsoleAdapter(actorService);
                    //         actorConsoleAdapter.updateActor();
                    //     break;
                    // case 3:
                    //     System.out.println("Eliminar Actor");
                    //         ActorService actorService = new ActorService(actorMySQLRepository);
                    //         ActorConsoleAdapter actorConsoleAdapter = new ActorConsoleAdapter(actorService);
                    //         actorConsoleAdapter.deleteActor();
                    //     break;
                    // case 4:
                    //     System.out.println("Listar Actores\"");
                    //         ActorService actorService = new ActorService(actorMySQLRepository);
                    //         ActorConsoleAdapter actorConsoleAdapter = new ActorConsoleAdapter(actorService);
                    //         actorConsoleAdapter.getAllActores();
                        // break;
                    case 5:
                        System.out.println("GESTION DE GENEROS");

                        break;
                    case 6:
                        System.out.println("GESTION DE PAISES");

                        break;

                    case 7:
                        System.out.println("GESTION TIPO DE ACTORES");

                        break;
                    case 8:
                        System.out.println("CONSULTAR INFORMACION DE UNA PELICULA");

                        break;
                    case 9:
                        System.out.println("LISTAR ACTORE POR PELICULAS");

                        break;
                    case 10:
                        System.out.println("SALIENDO DEL CINE CAMPUS");
                        bandera = false;
                    default:
                        System.out.println("Opcion incorrecta, seleccione una opcion valid");
                        break;
                }
            }

        }    
}
