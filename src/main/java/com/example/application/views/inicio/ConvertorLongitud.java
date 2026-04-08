package com.example.application.views.inicio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("ConvertorLongitud")
@Route("ConvertorLongitud")
@AnonymousAllowed
@Menu(order = 5)
public class ConvertorLongitud extends VerticalLayout implements IConvertidor {

    private double valorEntrada;
    private String unidadOrigen;
    private String unidadDestino;
    private double resultado;

    
    public ConvertorLongitud() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background", "linear-gradient(135deg, #86f18f, #a8e063)");
        getStyle().set("min-height", "100vh");

        Div card = new Div();
        card.getStyle()
                .set("background", "rgba(255,255,255,0.15)")
                .set("padding", "30px")
                .set("border-radius", "15px")
                .set("box-shadow", "0 8px 20px rgba(0,0,0,0.1)")
                .set("width", "350px")
                .set("text-align", "center");

        H1 titulo = new H1("📏 Convertidor");
        titulo.getStyle().set("margin", "0");

        H4 subtitulo = new H4("Longitud");
        subtitulo.getStyle().set("color", "gray");

        NumberField valor = new NumberField();
        valor.setPlaceholder("Ingrese valor");
        valor.setWidthFull();

        ComboBox<String> origen = new ComboBox<>("De");
        origen.setItems("angstrom", "nm", "micron", "mm", "cm", "m", "km", "in", "ft", "yd", "mi", "nmi");
        origen.setWidthFull();

        ComboBox<String> destino = new ComboBox<>("A");
        destino.setItems("angstrom", "nm", "micron", "mm", "cm", "m", "km", "in", "ft", "yd", "mi", "nmi");
        destino.setWidthFull();

        Button convertirBtn = new Button("Convertir");
        convertirBtn.getStyle()
                .set("background", "#4CAF50")
                .set("color", "white")
                .set("width", "100%")
                .set("border-radius", "10px");

        H1 resultadoTexto = new H1("0");
        resultadoTexto.getStyle()
                .set("color", "#4CAF50")
                .set("margin-top", "15px");

        convertirBtn.addClickListener(e -> {
            if (valor.isEmpty() || origen.isEmpty() || destino.isEmpty()) {
                Notification.show("Error: Completa los campos");
                return;
            }

            this.valorEntrada = valor.getValue();
            this.unidadOrigen = origen.getValue();
            this.unidadDestino = destino.getValue();

            double resultado = convertir();
            resultadoTexto.setText(String.format("%.6f", resultado));
        });

        card.add(titulo, subtitulo, valor, origen, destino, convertirBtn, resultadoTexto);
        add(card);
    }

    // Getters y setters
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

    @Override
    public double convertir() {

        double valor = getValorEntrada();
        double enMetros = 0;
        double resultado = 0;

        // ORIGEN → METROS
        switch (getUnidadOrigen()) {
            case "angstrom": enMetros = valor * 1e-10;  break;
            case "nm":       enMetros = valor * 1e-9;   break;
            case "micron":   enMetros = valor * 1e-6;   break;
            case "mm":       enMetros = valor / 1000;   break;
            case "cm":       enMetros = valor / 100;    break;
            case "m":        enMetros = valor;           break;
            case "km":       enMetros = valor * 1000;   break;
            case "in":       enMetros = valor * 0.0254; break;
            case "ft":       enMetros = valor * 0.3048; break;
            case "yd":       enMetros = valor * 0.9144; break;
            case "mi":       enMetros = valor * 1609.34;break;
            case "nmi":      enMetros = valor * 1852;   break;
            default: System.out.println("Unidad origen no válida");
        }

        // METROS → DESTINO
        switch (getUnidadDestino()) {
            case "angstrom": resultado = enMetros / 1e-10;  break;
            case "nm":       resultado = enMetros / 1e-9;   break;
            case "micron":   resultado = enMetros / 1e-6;   break;
            case "mm":       resultado = enMetros * 1000;   break;
            case "cm":       resultado = enMetros * 100;    break;
            case "m":        resultado = enMetros;           break;
            case "km":       resultado = enMetros / 1000;   break;
            case "in":       resultado = enMetros / 0.0254; break;
            case "ft":       resultado = enMetros / 0.3048; break;
            case "yd":       resultado = enMetros / 0.9144; break;
            case "mi":       resultado = enMetros / 1609.34;break;
            case "nmi":      resultado = enMetros / 1852;   break;
            default: System.out.println("Unidad destino no válida");
        }

        setResultado(resultado);
        return resultado;
    }
}