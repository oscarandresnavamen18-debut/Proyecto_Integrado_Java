package com.example.application.views.inicio;


public abstract class Convertor {
    private String nombreCategoria;
    private String icono;
    private double valorEntrada;
    private String unidadOrigen;
    private String unidadDestino;
    private double resultado;
   
   
    public Convertor(String nombreCategoria, String icono, double valorEntrada, String unidadOrigen,
        String unidadDestino, double resultado) {
        this.nombreCategoria = nombreCategoria;
        this.icono = icono;
        this.valorEntrada = valorEntrada;
        this.unidadOrigen = unidadOrigen;
        this.unidadDestino = unidadDestino;
        this.resultado = resultado;
    }


    public String getNombreCategoria() {
        return nombreCategoria;
    }


    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


    public String getIcono() {
        return icono;
    }


    public void setIcono(String icono) {
        this.icono = icono;
    }


    public double getValorEntrada() {
        return valorEntrada;
    }


    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }


    public String getUnidadOrigen() {
        return unidadOrigen;
    }


    public void setUnidadOrigen(String unidadOrigen) {
        this.unidadOrigen = unidadOrigen;
    }


    public String getUnidadDestino() {
        return unidadDestino;
    }


    public void setUnidadDestino(String unidadDestino) {
        this.unidadDestino = unidadDestino;
    }


    public double getResultado() {
        return resultado;
    }


    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
  
    public abstract double convertir();
}


