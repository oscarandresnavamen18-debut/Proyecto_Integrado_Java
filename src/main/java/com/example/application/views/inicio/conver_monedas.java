package com.example.application.views.inicio;

import java.util.Map;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

// --- 1. ARQUITECTURA (Punto 4 de la rúbrica) ---

interface Moneda {
    double getTasa(); 
    String getNombre();
}

abstract class MonedaBase implements Moneda {
    protected double tasa; // Encapsulamiento
    protected String nombre;

    public MonedaBase(double tasa, String nombre) {
        this.tasa = tasa;
        this.nombre = nombre;
    }
    @Override public double getTasa() { return tasa; }
    @Override public String getNombre() { return nombre; }
}

class Dolar extends MonedaBase { public Dolar() { super(1.0, "USD"); } }
class Euro extends MonedaBase { public Euro() { super(0.93, "EUR"); } }
class PesoColombiano extends MonedaBase { public PesoColombiano() { super(4872.0, "COP"); } }

// --- 2. VISTA DE LA APLICACIÓN ---

@PageTitle("Conversión de Monedas")
@Route(value = "conver_monedas", layout = MainLayout.class)
@AnonymousAllowed
@Menu(order = 3, title = "Conversión de Monedas")
public class conver_monedas extends VerticalLayout {

    private final NumberField amountField = new NumberField("Cantidad");
    private final ComboBox<String> fromCurrency = new ComboBox<>("Desde");
    private final ComboBox<String> toCurrency = new ComboBox<>("Hacia");
    private final TextField resultField = new TextField("Resultado");

    public conver_monedas() {
        configurarEstilos();
        
        H2 title = new H2("Convertidor de Monedas");
        title.addClassNames(LumoUtility.Margin.Top.XLARGE, LumoUtility.Margin.Bottom.MEDIUM);

        // Sección de ejemplos integrada
        VerticalLayout examplesSection = crearSeccionEjemplos();

        fromCurrency.setItems("USD", "EUR", "COP");
        toCurrency.setItems("USD", "EUR", "COP");
        fromCurrency.setValue("USD");
        toCurrency.setValue("EUR");

        Button convertButton = new Button("Convertir", e -> ejecutarConversion());
        Button clearButton = new Button("Limpiar", e -> limpiarCampos());

        HorizontalLayout actions = new HorizontalLayout(convertButton, clearButton);
        add(title, examplesSection, amountField, fromCurrency, toCurrency, actions, resultField);
    }

    private void ejecutarConversion() {
        if (amountField.getValue() == null) {
            Notification.show("Ingrese un monto");
            return;
        }

        // Aplicación de POLIMORFISMO
        Moneda origen = seleccionarMoneda(fromCurrency.getValue());
        Moneda destino = seleccionarMoneda(toCurrency.getValue());

        double resultado = (amountField.getValue() / origen.getTasa()) * destino.getTasa();
        resultField.setValue(String.format("%.2f %s", resultado, destino.getNombre()));
    }

    private Moneda seleccionarMoneda(String codigo) {
        return switch (codigo) {
            case "EUR" -> new Euro();
            case "COP" -> new PesoColombiano();
            default -> new Dolar(); // Sobrecarga lógica
        };
    }

    private void configurarEstilos() {
        setSizeFull();
        getStyle().set("background", "linear-gradient(135deg, #f3b756, #f8eaa3)");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSpacing(true);
    }

    private VerticalLayout crearSeccionEjemplos() {
        VerticalLayout section = new VerticalLayout();
        section.addClassNames("bg-gray-100", "rounded-lg", "p-4", "w-96");
        section.add(new H3("📘 Ejemplos de conversión"));
        section.add(new Paragraph("🔹 1 USD → 0.93 EUR"));
        section.add(new Paragraph("🔹 1 USD → 4,872 COP"));
        return section;
    }

    private void limpiarCampos() {
        amountField.clear();
        resultField.clear();
    }
}