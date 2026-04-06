package com.example.application.views.inicio;

import java.util.HashMap;
import java.util.Map;

import com.example.application.views.MainLayout;
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

@PageTitle("Conversión de Monedas")
@Route(value = "conver_monedas", layout = MainLayout.class)
@AnonymousAllowed
@Menu(order = 3, title = "Conversión de Monedas")
public class conver_monedas extends VerticalLayout {

    private final NumberField amountField;
    private final ComboBox<String> fromCurrency;
    private final ComboBox<String> toCurrency;
    private final TextField resultField;
    private final Button convertButton;
    private final Button clearButton;

    private final Map<String, Double> usdRates;

    public conver_monedas() {
        setSizeFull();
        getStyle().set("background", "linear-gradient(135deg, #f3b756, #f8eaa3)");
        getStyle().set("min-height", "100vh");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSpacing(false);
        addClassNames("text-center");

        usdRates = buildRates();

        H2 title = new H2("Convertidor de Monedas");
        title.addClassNames(LumoUtility.Margin.Top.XLARGE, LumoUtility.Margin.Bottom.MEDIUM);

        // Sección de ejemplos explicativos
        VerticalLayout examplesSection = new VerticalLayout();
        examplesSection.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        examplesSection.addClassNames("bg-gray-100", "rounded-lg", "p-4", "w-96");

        H3 examplesTitle = new H3("📘 Ejemplos de conversión");
        examplesTitle.addClassNames("text-base", "font-semibold");

        Paragraph ex1 = new Paragraph("🔹 1 USD → 0.93 EUR  (dólar a euro)");
        Paragraph ex2 = new Paragraph("🔹 1 USD → 4,872 COP  (dólar a peso colombiano)");
        Paragraph ex3 = new Paragraph("🔹 1 GBP → 1.25 USD  (libra a dólar)");
        Paragraph formula = new Paragraph("📐 Método: se convierte primero a USD, luego a la moneda destino.");

        ex1.addClassNames("text-sm", "text-gray-900");
        ex2.addClassNames("text-sm", "text-gray-900");
        ex3.addClassNames("text-sm", "text-gray-900");
        formula.addClassNames("text-sm", "font-medium", "text-gray-700");

        examplesSection.add(examplesTitle, ex1, ex2, ex3, formula);

        amountField = new NumberField("Cantidad");
        amountField.setWidth("220px");
        amountField.setPlaceholder("Ingrese monto");

        fromCurrency = new ComboBox<>("Desde");
        toCurrency = new ComboBox<>("Hacia");

        fromCurrency.setItems("USD", "EUR", "GBP", "JPY", "COP");
        toCurrency.setItems("USD", "EUR", "GBP", "JPY", "COP");
        fromCurrency.setValue("USD");
        toCurrency.setValue("EUR");
        fromCurrency.setWidth("220px");
        toCurrency.setWidth("220px");

        convertButton = new Button("Convertir", e -> convertCurrency());
        clearButton = new Button("Limpiar", e -> clearAll());

        resultField = new TextField("Resultado");
        resultField.setWidth("220px");
        resultField.setReadOnly(true);

        HorizontalLayout actions = new HorizontalLayout(convertButton, clearButton);
        actions.addClassNames("gap-2");

        add(title, examplesSection, amountField, fromCurrency, toCurrency, actions, resultField);
    }

    private Map<String, Double> buildRates() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.93);
        rates.put("GBP", 0.80);
        rates.put("JPY", 140.0);
        rates.put("COP", 4872.0);
        return rates;
    }

    private void convertCurrency() {
        Double amount = amountField.getValue();
        String from = fromCurrency.getValue();
        String to = toCurrency.getValue();

        if (amount == null) {
            Notification.show("Ingrese un monto válido", 3000, Notification.Position.MIDDLE);
            return;
        }
        if (from == null || to == null) {
            Notification.show("Seleccione ambas monedas", 3000, Notification.Position.MIDDLE);
            return;
        }

        double result = convertAmount(amount, from, to);
        resultField.setValue(String.format("%.2f %s", result, to));
    }

    private double convertAmount(double amount, String from, String to) {
        if (from.equals(to)) {
            return amount;
        }
        double inUsd = amount / usdRates.getOrDefault(from, 1.0);
        return inUsd * usdRates.getOrDefault(to, 1.0);
    }

    private void clearAll() {
        amountField.clear();
        resultField.clear();
        fromCurrency.setValue("USD");
        toCurrency.setValue("EUR");
    }
}