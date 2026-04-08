package com.example.application.views.inicio;

// Importaciones de Vaadin (componentes UI)
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

// Configuración de la vista
@PageTitle("ConvertorLongitud")
@Route("ConvertorLongitud")
@AnonymousAllowed
@Menu(order = 5)

// HERENCIA + INTERFAZ
public class ConvertorLongitud extends VerticalLayout implements IConvertidor {

    // Atributos (ENCAPSULAMIENTO)
    private double valorEntrada;
    private String unidadOrigen;
    private String unidadDestino;
    private double resultado;

    // Constructor (interfaz gráfica)
    public ConvertorLongitud() {

        // Configuración de pantalla
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Fondo
        getStyle().set("background", "linear-gradient(135deg, #86f18f, #a8e063)");
        getStyle().set("min-height", "100vh");

        // Contenedor principal
        Div card = new Div();
        card.getStyle()
                .set("background", "rgba(255,255,255,0.15)")
                .set("padding", "30px")
                .set("border-radius", "15px")
                .set("width", "350px");

        // Títulos
        H1 titulo = new H1("📏 Convertidor");
        H4 subtitulo = new H4("Longitud");

        // Campo de entrada
        NumberField valor = new NumberField();
        valor.setPlaceholder("Ingrese valor");

        // ComboBox origen
        ComboBox<String> origen = new ComboBox<>("De");
        origen.setItems("m", "km", "cm", "mm", "in", "ft");

        // ComboBox destino
        ComboBox<String> destino = new ComboBox<>("A");
        destino.setItems("m", "km", "cm", "mm", "in", "ft");

        // Botón convertir (EVENTO)
        Button convertirBtn = new Button("Convertir");

        // Resultado
        H1 resultadoTexto = new H1("0");

        // Acción del botón
        convertirBtn.addClickListener(e -> {

            // Validación
            if (valor.isEmpty() || origen.isEmpty() || destino.isEmpty()) {
                Notification.show("Error: Completa los campos");
                return;
            }

            // Guardar datos
            this.valorEntrada = valor.getValue();
            this.unidadOrigen = origen.getValue();
            this.unidadDestino = destino.getValue();

            // Llamar método (POLIMORFISMO)
            double resultado = convertir();

            // Mostrar resultado
            resultadoTexto.setText(String.format("%.6f", resultado));
        });

        // Agregar elementos
        card.add(titulo, subtitulo, valor, origen, destino, convertirBtn, resultadoTexto);
        add(card);
    }

    // GETTERS Y SETTERS (ENCAPSULAMIENTO)
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

    // MÉTODO PRINCIPAL (POLIMORFISMO)
    @Override
    public double convertir() {

        double valor = getValorEntrada();
        double enMetros = 0;
        double resultado = 0;

        // Convertir origen → metros
        switch (getUnidadOrigen()) {
            case "m":  enMetros = valor; break;
            case "km": enMetros = valor * 1000; break;
            case "cm": enMetros = valor / 100; break;
            case "mm": enMetros = valor / 1000; break;
            case "in": enMetros = valor * 0.0254; break;
            case "ft": enMetros = valor * 0.3048; break;
        }

        // Convertir metros → destino
        switch (getUnidadDestino()) {
            case "m":  resultado = enMetros; break;
            case "km": resultado = enMetros / 1000; break;
            case "cm": resultado = enMetros * 100; break;
            case "mm": resultado = enMetros * 1000; break;
            case "in": resultado = enMetros / 0.0254; break;
            case "ft": resultado = enMetros / 0.3048; break;
        }

        setResultado(resultado);
        return resultado;
    }
}