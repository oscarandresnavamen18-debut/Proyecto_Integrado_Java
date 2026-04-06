package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class InicioView extends VerticalLayout {

    public InicioView() {
           getStyle().set("background", "linear-gradient(135deg, #f56c5d, #ecbe72)");
        getStyle().set("min-height", "100vh");
        setSpacing(false);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 titulo = new H2("🔢 Convertidor de Unidades");
        titulo.getStyle().set("margin-bottom", "0");

        Paragraph descripcion = new Paragraph("Proyecto desarrollado en Java con Vaadin Flow");
        descripcion.getStyle().set("color", "green").set("margin-top", "6px");

        // Integrantes
        Div integrantes = new Div();
        integrantes.getStyle()
                .set("background", "#e0970e")
                .set("padding", "20px")
                .set("border-radius", "12px")
                .set("text-align", "center")
                .set("margin-bottom", "24px");

        H4 intTitle = new H4("👥 Integrantes");
        Paragraph lista = new Paragraph(
                """
                Jorge Humberto \u2014 Monedas & Temperatura
                Juan \u2014 Peso / Masa
                Elian Mart\u00ednez \u2014 Tiempo
                Oscar Navarro \u2014 Por definir""");
        lista.getStyle().set("white-space", "pre-line").set("line-height", "1.8");
        integrantes.add(intTitle, lista);

        // Botones
        Button btnTemperatura = new Button("🌡️ Temperatura");
        btnTemperatura.getStyle()
                .set("background", "#e74c3c").set("color", "white")
                .set("border-radius", "10px").set("padding", "10px 20px");
        btnTemperatura.addClickListener(e ->
                btnTemperatura.getUI().ifPresent(ui -> ui.navigate("conv-temperatura")));

        Button btnPeso = new Button("⚖️ Peso / Masa");
        btnPeso.getStyle()
                .set("background", "#4CAF50").set("color", "white")
                .set("border-radius", "10px").set("padding", "10px 20px");
        btnPeso.addClickListener(e ->
                btnPeso.getUI().ifPresent(ui -> ui.navigate("convertidor")));
        Button btnMonedas = new Button("💱 Monedas");
        btnMonedas.getStyle()
        .set("background", "#f39c12").set("color", "white")
        .set("border-radius", "10px").set("padding", "10px 20px");
        btnMonedas.addClickListener(e ->
        btnMonedas.getUI().ifPresent(ui -> ui.navigate("conver_monedas")));

        Button btnTiempo = new Button("⏱️ Tiempo");
        btnTiempo.getStyle()
                .set("background", "#3498db").set("color", "white")
                .set("border-radius", "10px").set("padding", "10px 20px");
        btnTiempo.setEnabled(false); // aún no disponible

        Button btnOscar = new Button("❓ Por definir");
        btnOscar.getStyle()
                .set("background", "#9b59b6").set("color", "white")
                .set("border-radius", "10px").set("padding", "10px 20px");
        btnOscar.setEnabled(false); // aún no disponible

        HorizontalLayout botones = new HorizontalLayout(
                btnTemperatura, btnPeso, btnMonedas, btnTiempo, btnOscar);
        botones.setSpacing(true);
        botones.setJustifyContentMode(JustifyContentMode.CENTER);

        add(titulo, descripcion, integrantes, botones);
    }
}