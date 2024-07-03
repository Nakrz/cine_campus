package com.filtro.Pelicula.domain;

public class Pelicula {
    private int id;
    private String codinterno;
    private String nombre;
    private String duracion;
    private String sinopsis;



    public Pelicula() {}

    public Pelicula(int id, String codinterno, String duracion, String sinopsis, String nombre) {
        this.id = id;
        this.codinterno = codinterno;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodinterno() {
        return codinterno;
    }
    public void setCodinterno(String codinterno) {
        this.codinterno = codinterno;
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
