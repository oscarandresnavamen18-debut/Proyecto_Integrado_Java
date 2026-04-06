package com.example.application.views.inicio;

public class ConvertorLongitud extends Convertor {

    public ConvertorLongitud(String nombreCategoria, String icono, double valorEntrada,
                             String unidadOrigen, String unidadDestino, double resultado) {
        super(nombreCategoria, icono, valorEntrada, unidadOrigen, unidadDestino, resultado);
    }

    public void convertir() {

        double valor = getValorEntrada();
        double enMetros = 0;
        double resultado = 0;

        // ORIGEN → METROS
        switch (getUnidadOrigen()) {

            case "angstrom":
                enMetros = valor * 1e-10;
                break;
            case "nm":
                enMetros = valor * 1e-9;
                break;
            case "micron":
                enMetros = valor * 1e-6;
                break;
            case "mm":
                enMetros = valor / 1000;
                break;
            case "cm":
                enMetros = valor / 100;
                break;
            case "m":
                enMetros = valor;
                break;
            case "km":
                enMetros = valor * 1000;
                break;
            case "in": // pulgadas
                enMetros = valor * 0.0254;
                break;
            case "ft": // pies
                enMetros = valor * 0.3048;
                break;
            case "yd": // yardas
                enMetros = valor * 0.9144;
                break;
            case "mi": // millas
                enMetros = valor * 1609.34;
                break;
            case "nmi": // millas nauticas
                enMetros = valor * 1852;
                break;

            default:
                System.out.println("Unidad origen no válida");
        }

        //  METROS → DESTINO
        switch (getUnidadDestino()) {

            case "angstrom":
                resultado = enMetros / 1e-10;
                break;
            case "nm":
                resultado = enMetros / 1e-9;
                break;
            case "micron":
                resultado = enMetros / 1e-6;
                break;
            case "mm":
                resultado = enMetros * 1000;
                break;
            case "cm":
                resultado = enMetros * 100;
                break;
            case "m":
                resultado = enMetros;
                break;
            case "km":
                resultado = enMetros / 1000;
                break;
            case "in":
                resultado = enMetros / 0.0254;
                break;
            case "ft":
                resultado = enMetros / 0.3048;
                break;
            case "yd":
                resultado = enMetros / 0.9144;
                break;
            case "mi":
                resultado = enMetros / 1609.34;
                break;
            case "nmi":
                resultado = enMetros / 1852;
                break;

            default:
                System.out.println("Unidad destino no válida");
        }

        setResultado(resultado);
    }
}