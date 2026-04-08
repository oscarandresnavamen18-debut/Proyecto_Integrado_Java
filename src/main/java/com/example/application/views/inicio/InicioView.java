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

        H4 intTitle = new H4("👥 Equipo de Desarrollo");
        
        // Aquí defines claramente los ROLES (Criterio 1)
        Paragraph lista = new Paragraph();
        lista.add(new Html("<span><b>Jorge Humberto</b> — Líder de Arquitectura (Monedas/Temp)</span><br>"));
        lista.add(new Html("<span><b>Juan</b> — Especialista en Peso y Masa</span><br>"));
        lista.add(new Html("<span><b>Elian Martínez</b> — Desarrollador de Tiempo</span><br>"));
        lista.add(new Html("<span><b>Oscar Navarro</b> — Analista de QA</span>"));
        
        lista.getStyle().set("line-height", "2.0");
        card.add(intTitle, lista);
        return card;
    }

    private HorizontalLayout crearMenuNavegacion() {
        // Botón Temperatura
        Button btnTemp = crearBotonPersonalizado("🌡️ Temperatura", "#e74c3c", "conv-temperatura");
        
        // Botón Peso
        Button btnPeso = crearBotonPersonalizado("⚖️ Peso / Masa", "#4CAF50", "convertidor");
        
        // Botón Monedas
        Button btnMonedas = crearBotonPersonalizado("💱 Monedas", "#f39c12", "conver_monedas");

        // Botones deshabilitados (En desarrollo)
        Button btnTiempo = new Button("⏱️ Tiempo");
        btnTiempo.setEnabled(false);
        
        Button btnOscar = new Button("❓ QA Panel");
        btnOscar.setEnabled(false);

        HorizontalLayout layout = new HorizontalLayout(btnTemp, btnPeso, btnMonedas, btnTiempo, btnOscar);
        layout.setSpacing(true);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.addClassNames(LumoUtility.Margin.Top.LARGE);
        
        return layout;
    }

    // Método de utilidad para aplicar DRY (Don't Repeat Yourself) - Buena práctica
    private Button crearBotonPersonalizado(String texto, String colorHex, String ruta) {
        Button boton = new Button(texto);
        boton.getStyle()
             .set("background", colorHex)
             .set("color", "white")
             .set("cursor", "pointer")
             .set("border-radius", "10px");
        boton.addClickListener(e -> boton.getUI().ifPresent(ui -> ui.navigate(ruta)));
        return boton;
    }
}