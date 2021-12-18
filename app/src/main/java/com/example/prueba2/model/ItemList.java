package com.example.prueba2.model;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String titulo;
    private String descripcion;
    private Double precio;
    private int imgResource;
    private String maps;
    private int numberInCart;
    private Double latitude;
    private Double longitude;
    private String NombreFarmacia;

    /// falta argumento de mapa
    public ItemList(String titulo, String descripcion, Double precio ,int imgResource) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgResource = imgResource;
        this.maps = "0";
        this.latitude=0.0;
        this.longitude=0.0;
        this.NombreFarmacia=" ";

    }
    public ItemList(String titulo, String descripcion, Double precio ,int imgResource, Double latitude, Double longitude, String NombreFarmacia ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgResource = imgResource;
        this.maps = "0";
        this.latitude=latitude;
        this.longitude=longitude;
        this.NombreFarmacia=NombreFarmacia;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) { this.precio = precio; }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) { this.imgResource = imgResource; }

    public String getMaps() {return maps;}

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }


    //las agregué Alan

    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLatitude() {return latitude;}

    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public Double getLongitude() {return longitude;}

    public void setNombreFarmacia(String NombreFarmacia) { this.NombreFarmacia = NombreFarmacia; }
    public String getNombreFarmacia() {return NombreFarmacia; }

}
