package com.filtro.Genero.adapters.in;

import com.filtro.Genero.domain.Genero;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import com.filtro.Genero.application.GeneroService;
import java.util.Optional;

public class GeneroConsoleAdapter {


    Scanner sc = new Scanner(System.in);

    private final GeneroService generoService; 

    public GeneroConsoleAdapter(GeneroService generoService) {
        this.generoService = generoService;
    }

    public void createGenero() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {

            System.out.println("*************** REGISTRAR GENERO ***************");
            System.out.println("[*] INGRESE EL ID DEL GENERO A CREAR: ");
            int id = sc.nextInt();
            Optional<Genero> genero = generoService.findGeneroById(id);
            genero.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {

                    System.out.println("*************** REGISTRAR GENERO ***************");
            
                    System.out.println("[*] INGRESE LA DESCRIPCION DEL GENERO: ");
                    String generoDescripcion = sc.nextLine();
                
                    Genero newGenero = new Genero(id, generoDescripcion);
                    generoService.createGenero(newGenero);
                });

            System.out.println("[?] DESEA AÑADIR OTRO GENERO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchGenero(){
        System.out.println("[?]  INGRESE EL ID DEL GENERO A BUSCAR\n\n");
        int findId = sc.nextInt();

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
            a -> System.out.println("[*]  ID: "+ a.getId()),
            () -> System.out.println("[!]  GENERO NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }


    public void updateGenero(){
        System.out.println("[*]   INGRESE EL ID DE LA genero A EDITAR\n\n");
        int findId = sc.nextInt();

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
            a -> {
                System.out.println("[*]  ID: " + a.getId());
                System.out.println("[*]  INGRESE LOS NUEVOS DETALLES");
                String updateDetalles = sc.nextLine();
    
                Genero updatedGenero = new Genero(a.getId(), updateDetalles);
                generoService.updateGenero(updatedGenero);
            },
            () -> System.out.println("[!]  genero NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR....");
        sc.nextLine();     
    
    }

    public void deleteGenero(){
        System.out.println("[*]  INGRESE EL ID DEL genero A ELIMINAR\n\n");
        int findId = sc.nextInt();

        Optional<Genero> genero = generoService.findGeneroById(findId);
        genero.ifPresentOrElse(
            a -> {
                generoService.deleteGenero(findId);
            },
            () -> System.out.println("[!]  genero NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllGeneros(){
        generoService.findAllGeneros().forEach(a -> {
            System.out.println("[*]  ID: "+ a.getId() + "DESCRIPCION " + a.getDescripcion());
        });
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

}


