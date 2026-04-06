package com.example.application.views.inicio;
import com.example.application.views.inicio.Convertor;

public class ConvertorPesoMasa extends Convertor implements IConvertidor {
    private String tipoConversion;

    // 🔹 Constructor usando super (HERENCIA)
    public ConvertorPesoMasa(String nombreCategoria, String icono, double valorEntrada,String unidadOrigen, String unidadDestino) {

        super(nombreCategoria, icono, valorEntrada, unidadOrigen, unidadDestino, 0);
        this.tipoConversion = "Peso/Masa";
    }

    // 🔥 POLIMORFISMO (sobrescribes el método del padre)
    @Override
    public double convertir() {

        double factor = obtenerFactor(getUnidadOrigen(), getUnidadDestino());
        double resultado = getValorEntrada() * factor;

        setResultado(resultado);

        return resultado;
    }

    private double obtenerFactor(String origen, String destino) {

        if (origen.equals("Kilogramos") && destino.equals("Libras")) {
            return 2.20462;
        } else if (origen.equals("Libras") && destino.equals("Kilogramos")) {
            return 0.453592;
        } else if (origen.equals("Gramos") && destino.equals("Onzas")) {
            return 0.035274;
        } else if (origen.equals("Onzas") && destino.equals("Gramos")) {
            return 28.3495;
        } else if (origen.equals("Toneladas") && destino.equals("Kilogramos")) {
            return 1000;
        } else if (origen.equals("Kilogramos") && destino.equals("Toneladas")) {
            return 0.001;
        }

        return 1;
    }
}