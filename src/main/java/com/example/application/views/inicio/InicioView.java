package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID) // Cambiado a HOME para mejor semántica
public class InicioView extends VerticalLayout {

    public InicioView() {
        configurarLayoutPrincipal();

        // 1. Títulos (Criterio 2: Propósito del proyecto)
        H2 titulo = new H2("🔢 Sistema Integral de Conversión");
        titulo.addClassNames(LumoUtility.Margin.Bottom.NONE);

        Paragraph descripcion = new Paragraph("Aplicación orientada a objetos desarrollada con Java y Vaadin Flow");
        descripcion.addClassNames(LumoUtility.TextColor.SUCCESS, LumoUtility.FontWeight.MEDIUM);

        // 2. Sección de Integrantes (Criterio 1: Roles y Participación)
        Div integrantesCard = crearTarjetaIntegrantes();

        // 3. Panel de Navegación (Botones)
        HorizontalLayout menuBotones = crearMenuNavegacion();

        add(titulo, descripcion, integrantesCard, menuBotones);
    }

    private void configurarLayoutPrincipal() {
        getStyle().set("background", "linear-gradient(135deg, #f56c5d, #ecbe72)");
        getStyle().set("min-height", "100vh");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSpacing(true);
    }

    private Div crearTarjetaIntegrantes() {
        Div card = new Div();
        card.addClassNames(
            LumoUtility.Background.CONTRAST_10, 
            LumoUtility.Padding.LARGE, 
            LumoUtility.BorderRadius.LARGE,
            LumoUtility.BoxShadow.MEDIUM
        );
        card.getStyle().set("text-align", "center").set("background", "rgba(255, 255, 255, 0.2)");

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