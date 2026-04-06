package com.example.application.views.inicio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
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

@PageTitle("Conversión Temperatura")
@Route("conv-temperatura")
@AnonymousAllowed
@Menu(order = 2, title = "Conversión Temperatura")
public class Conv_temperatura extends VerticalLayout {

    private NumberField inputTemperature;
    private ComboBox<String> fromUnit;
    private ComboBox<String> toUnit;
    private TextField resultField;
    private Button convertButton;
    private Button clearButton;

    public Conv_temperatura() {
        getStyle().set("background", "linear-gradient(135deg, #f56c5d, #ecbe72)");
        getStyle().set("min-height", "100vh");
        
        setSpacing(false);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassNames("text-center");

        H2 header = new H2("Convertidor de Temperatura");
        header.addClassNames(LumoUtility.Margin.Top.XLARGE, LumoUtility.Margin.Bottom.MEDIUM);
        add(header);

        // Sección de ejemplos explicativos
        VerticalLayout examplesSection = new VerticalLayout();
        examplesSection.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        examplesSection.addClassNames("bg-gray-900", "rounded-lg", "p-4", "w-96");

        H3 examplesTitle = new H3("📘 Ejemplos de conversión");
        examplesTitle.addClassNames("text-base", "font-semibold");

        Paragraph ex1 = new Paragraph("🔹 100 °C → 212 °F  (agua hirviendo)");
        Paragraph ex2 = new Paragraph("🔹 0 °C → 273.15 K  (cero absoluto en Celsius)");
        Paragraph ex3 = new Paragraph("🔹 98.6 °F → 37 °C  (temperatura corporal)");
        Paragraph formula = new Paragraph("📐 Fórmulas: °F = (°C × 9/5) + 32  |  K = °C + 273.15");

        ex1.addClassNames("text-sm", "text-gray-600");
        ex2.addClassNames("text-sm", "text-gray-600");
        ex3.addClassNames("text-sm", "text-gray-600");
        formula.addClassNames("text-xm", "font-medium", "text-gray-700");

        examplesSection.add(examplesTitle, ex1, ex2, ex3, formula);
        add(examplesSection);

        createTemperatureConverter();
    }

    private void createTemperatureConverter() {
        inputTemperature = new NumberField("Temperatura");
        inputTemperature.setPlaceholder("Ingrese valor");
        inputTemperature.setWidth("200px");

        fromUnit = new ComboBox<>("De");
        fromUnit.setItems("Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)");
        fromUnit.setValue("Celsius (°C)");
        fromUnit.setWidth("200px");

        toUnit = new ComboBox<>("A");
        toUnit.setItems("Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)");
        toUnit.setValue("Fahrenheit (°F)");
        toUnit.setWidth("200px");

        convertButton = new Button("Convertir");
        convertButton.addClickListener(e -> convertTemperature());

        clearButton = new Button("Limpiar");
        clearButton.addClickListener(e -> clearFields());

        resultField = new TextField("Resultado");
        resultField.setReadOnly(true);
        resultField.setWidth("200px");

        HorizontalLayout buttonLayout = new HorizontalLayout(convertButton, clearButton);
        buttonLayout.addClassNames("gap-2");

        VerticalLayout converterLayout = new VerticalLayout();
        converterLayout.addClassNames("mt-4", "gap-2");
        converterLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        converterLayout.add(inputTemperature, fromUnit, toUnit, buttonLayout, resultField);

        add(converterLayout);
    }

    private void convertTemperature() {
        if (inputTemperature.getValue() == null) {
            Notification.show("Por favor ingrese una temperatura");
            return;
        }

        double temp = inputTemperature.getValue();
        String from = fromUnit.getValue();
        String to = toUnit.getValue();

        double result = performConversion(temp, from, to);

        String toSymbol = to.contains("Celsius") ? "°C" : to.contains("Fahrenheit") ? "°F" : "K";

        resultField.setValue(String.format("%.2f %s", result, toSymbol));
    }

    private double performConversion(double temp, String from, String to) {
        double celsius;

        if (from.contains("Celsius")) {
            celsius = temp;
        } else if (from.contains("Fahrenheit")) {
            celsius = (temp - 32) * 5 / 9;
        } else {
            celsius = temp - 273.15;
        }

        if (to.contains("Celsius")) {
            return celsius;
        } else if (to.contains("Fahrenheit")) {
            return celsius * 9 / 5 + 32;
        } else {
            return celsius + 273.15;
        }
    }

    private void clearFields() {
        inputTemperature.clear();
        resultField.clear();
        fromUnit.setValue("Celsius (°C)");
        toUnit.setValue("Fahrenheit (°F)");
    }
}