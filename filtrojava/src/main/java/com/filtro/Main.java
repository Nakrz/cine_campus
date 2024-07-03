package com.filtro;

import java.util.Scanner;
import com.filtro.Actor.application.ActorService;
import com.filtro.Genero.adapters.in.GeneroConsoleAdapter;
import com.filtro.Genero.adapters.out.GeneroMySQLRepository;
import com.filtro.Genero.application.GeneroService;
import com.filtro.Pais.adapters.in.PaisConsoleAdapter;
import com.filtro.Pais.adapters.out.PaisMySQLRepository;
import com.filtro.Pais.application.PaisService;
import com.filtro.Pelicula.adapters.in.PeliculaConsoleAdapter;
import com.filtro.Pelicula.adapters.out.PeliculaMySQLRepository;
import com.filtro.Pelicula.application.PeliculaService;
import com.filtro.Actor.adapters.in.ActorConsoleAdapter;
import com.filtro.Actor.adapters.out.ActorMySQLRepository;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        String url = "jdbc:mysql://localhost:3306/cinecampus";
        String user = "root";
        String password = "123456";
        

        GeneroMySQLRepository generoMySQLRepository = new GeneroMySQLRepository(url, user, password);
        GeneroService generoService = new GeneroService(generoMySQLRepository);
        GeneroConsoleAdapter generoConsoleAdapter = new GeneroConsoleAdapter(generoService);
        
        PaisMySQLRepository paisMySQLRepository = new PaisMySQLRepository(url, user, password);
        PaisService paisService = new PaisService(paisMySQLRepository);
        PaisConsoleAdapter paisConsoleAdapter = new PaisConsoleAdapter(paisService);

        PeliculaMySQLRepository peliculaMySQLRepository = new PeliculaMySQLRepository(url, user, password);
        PeliculaService peliculaService = new PeliculaService(peliculaMySQLRepository);
        PeliculaConsoleAdapter peliculaConsoleAdapter = new PeliculaConsoleAdapter(peliculaService);

        ActorMySQLRepository actorMySQLRepository = new ActorMySQLRepository(url, user, password);
        ActorService actorService = new ActorService(actorMySQLRepository, paisMySQLRepository, generoMySQLRepository);
        ActorConsoleAdapter actorConsoleAdapter = new ActorConsoleAdapter(actorService);
        
    
        boolean bandera = true;
            while (bandera){
                System.out.println("\n\n\nBIENVENIDO AL CINE CAMPUS :");
                System.out.println("---------------------------------------");
                System.out.println("");
                System.out.println("1. GESTOR DE ACTORES");
                System.out.println("2. GESTOR DE GENEROS");
                System.out.println("3. GESTOR DE PAISES");
                System.out.println("4. GESTOR DE PELICULAS");
                System.out.println("5. GESTOR TIPO DE ACTORES");
                System.out.println("6. LISTAR ACTORES POR PELICULAS");
                System.out.println("0. Salir");
                System.out.println("---------------------------------------");
                System.out.println("Elija una opcion que desea realizar: ");
                int opcion = sc.nextInt();
            
                switch (opcion) {
                    case 1:
                        System.out.println("GESTION DE ACTORES");
                        actorConsoleAdapter.starActor();
                        break;
                    case 2:
                        System.out.println("GESTION DE GENEROS");
                        generoConsoleAdapter.startGenero();

                        break;
                    case 3:
                        System.out.println("GESTION DE PAISES");
                        paisConsoleAdapter.startPais();

                        break;

                    case 4:
                        System.out.println("GESTOR DE PELICULAS");
                        peliculaConsoleAdapter.startPelicula();

                    case 5:
                        System.out.println("GESTION TIPO DE ACTORES");
                        System.out.println("En proceso de desarrollo");

                        break;
                    case 6:
                        System.out.println("LISTAR ACTORES POR PELICULAS");
                        System.out.println("En proceso de desarrollo");

                        break;
                    case 0:
                        System.out.println("SALIENDO DEL CINE CAMPUS");
                        bandera = false;
                    default:
                        System.out.println("Opcion incorrecta, seleccione una opcion valid");
                        break;
                }
            }

        }    
}
