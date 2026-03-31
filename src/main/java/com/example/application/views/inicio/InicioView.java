package com.example.application.views.inicio;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class InicioView extends VerticalLayout {

    public InicioView() {
        add("¡Bienvenido al Convertidor de Unidades!");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

    }
}
