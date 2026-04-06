package com.example.application.views.inicio;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button; // ✅ corregido
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification; // ✅ agregado
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Conversión Tiempo")
@Route(value = "ConvertorTiempo", layout = MainLayout.class)
@AnonymousAllowed
@Menu(order = 4)
public class ConvertorTiempo extends VerticalLayout {

    public ConvertorTiempo() {

        setSizeFull();
         getStyle().set("background", "linear-gradient(135deg, #6cf55d, #72cbec)");
         getStyle().set("min-height", "100vh");

        VerticalLayout header = new VerticalLayout();
        header.setHeight("90px");
        header.getStyle().set("border", "3px solid red");
        header.setAlignItems(Alignment.CENTER);

        H1 titulo = new H1("Calculadora De Tiempo");
        header.add(titulo);
        add(header);

        VerticalLayout calculadora = new VerticalLayout();
        calculadora.setWidthFull();
        calculadora.setHeight("500px");
        calculadora.setAlignItems(Alignment.CENTER);
        calculadora.getStyle().set("border", "3px solid blue");

        NumberField tiempo = new NumberField("Tiempo a calcular");
        tiempo.setWidth("250px");

        ComboBox<String> desde = new ComboBox<>("De");
        desde.setWidth("250px");
        desde.setItems("Segundos", "Minutos", "Horas", "Días");
        desde.setValue("Segundos");

        ComboBox<String> hacia = new ComboBox<>("A");
        hacia.setWidth("250px");
        hacia.setItems("Segundos", "Minutos", "Horas", "Días");
        hacia.setValue("Minutos");

        TextField resultado = new TextField("Resultado");
        resultado.setWidth("250px");
        resultado.setReadOnly(true);

        Button btnConvertir = new Button("Convertir"); 
        btnConvertir.addClickListener(e -> {

            if (tiempo.getValue() == null) {
                Notification.show("Ingresa un tiempo"); 
                return;
            }

            double valor = tiempo.getValue();
            String d = desde.getValue();
            String h = hacia.getValue();

            if (d.equals(h)) {
                resultado.setValue(valor + " " + h);
                return;
            }

            double segundos = valor;
            if (d.equals("Minutos"))  segundos = valor * 60;
            else if (d.equals("Horas"))  segundos = valor * 3600;
            else if (d.equals("Días"))   segundos = valor * 86400;

            double total = segundos;
            if (h.equals("Minutos"))  total = segundos / 60;
            else if (h.equals("Horas"))  total = segundos / 3600;
            else if (h.equals("Días"))   total = segundos / 86400;

            resultado.setValue(total + " " + h);
        });

        Button btnLimpiar = new Button("Limpiar"); 
        btnLimpiar.addClickListener(e -> {
            tiempo.clear();
            resultado.clear();
            desde.setValue("Segundos");
            hacia.setValue("Minutos");
        });

        HorizontalLayout botones = new HorizontalLayout(btnConvertir, btnLimpiar);
        calculadora.add(tiempo, desde, hacia, botones, resultado);
        add(calculadora); // 

        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setHeight("100px");
        footer.getStyle().set("border", "3px solid green");
        footer.setJustifyContentMode(JustifyContentMode.CENTER);
        footer.setAlignItems(Alignment.CENTER);
        footer.setSpacing(true);

        Image logoInsta = new Image(
            "https://freelogopng.com/images/all_img/1658588965instagram-logo-png-transparent-background.png",
            "Instagram Logo"
        );
        logoInsta.setWidth("40px");
        logoInsta.setHeight("40px");

        Anchor linkinsta = new Anchor(
            "https://www.instagram.com/elianmarti17",
            logoInsta
        );
        linkinsta.setTarget("_blank");

        H3 nombre = new H3("Creado Por: Elian Martinez");
        nombre.getStyle().set("margin-right", "20px");
        H3 redes = new H3("Redes Sociales");

        footer.add(nombre, redes, linkinsta);
        add(footer);
    }
}