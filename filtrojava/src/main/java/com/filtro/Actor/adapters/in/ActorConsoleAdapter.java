package com.filtro.Actor.adapters.in;


import java.util.Optional;
import java.util.List;
import com.filtro.Actor.application.ActorService;
import com.filtro.Actor.domain.Actor;
import java.util.Scanner;
import java.text.MessageFormat;



public class ActorConsoleAdapter{

    Scanner sc = new Scanner(System.in);

    private final ActorService actorService; 

    public ActorConsoleAdapter(ActorService actorService) {
        this.actorService = actorService;
    }


    public void createActor() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {

            System.out.println("*************** REGISTRAR ACTOR ***************");
            System.out.println("[*] INGRESE EL ID DEL ACTOR A CREAR: ");
            int id = sc.nextInt();
            Optional<Actor> actor = actorService.findActorById(id);
            actor.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {

                    System.out.println("*************** REGISTRAR ACTOR ***************");
                    sc.nextLine();

                    System.out.println("[*] INGRESE EL NOMBRE DEL ACTOR: ");
                    String nombre = sc.nextLine();


                    System.out.println("[*] INGRESE LA ID DEL ACTOR: ");
                    int idNacionalidad = sc.nextInt();

                    System.out.println("[*] INGRESE LA DESCRIPCION DEL ACTOR: ");
                    int edad = sc.nextInt();
                    
                    System.out.println("[*] INGRESE LA DESCRIPCION DEL ACTOR: ");
                    int idGenero = sc.nextInt();
                
                    Actor newActor = new Actor(id, nombre, idNacionalidad, edad, idGenero);
                    actorService.createActor(newActor);
                });

            System.out.println("[?] DESEA AÑADIR OTRO ACTOR? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchActor(){
        System.out.println("[?]  INGRESE EL ID DEL ACTOR A BUSCAR\n\n");
        int findId = sc.nextInt();

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
            a -> System.out.println("[*]  ID: "+ a.getId()),
            () -> System.out.println("[!]  ACTOR NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }


    public void updateActor(){
        System.out.println("[*]   INGRESE EL ID DEL ACTOR A EDITAR\n\n");
        int findId = sc.nextInt();

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
            a -> {
                System.out.println("[*]  ID: " + a.getId());
                System.out.println("[*]  INGRESE EL NUEVO NOMBRE");
                String updateNombre = sc.nextLine();
                System.out.println("[*]  INGRESE EL NUEVO ID DE NACIONALIDAD");
                int updateIdNacionalidad = sc.nextInt();
                System.out.println("[*]  INGRESE LA NUEVA EDAD");
                int updateEdad = sc.nextInt();
                System.out.println("[*]  INGRESE EL NUEVO GENERO");
                int updateIdGenero = sc.nextInt();


    
                Actor updatedActor = new Actor(findId, updateNombre, updateIdNacionalidad, updateEdad, updateIdGenero);
                actorService.updateActor(updatedActor);
            },
            () -> System.out.println("[!]  ACTOR NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR....");
        sc.nextLine();     
    
    }

    public void deleteActor(){
        System.out.println("[*]  INGRESE EL ID DEL ACTOR A ELIMINAR\n\n");
        int findId = sc.nextInt();

        Optional<Actor> actor = actorService.findActorById(findId);
        actor.ifPresentOrElse(
            a -> {
                actorService.deleteActor(findId);
            },
            () -> System.out.println("[!]  ACTOR NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    
    public void getAllActores(){
        actorService.findAllActores().forEach(a -> {
            System.out.println("[*]  ID: "+ a.getId() + "NOMBRE " + a.getNombre()+ "ID NACIONALIDAD " + a.getIdNacionalidad()+ "EDAD " + a.getEdad()+ "ID DE GENERO " + a.getIdGenero());
        });
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

}

