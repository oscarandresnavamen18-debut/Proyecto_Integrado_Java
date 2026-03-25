package com.example.application.views.inicio;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class InicioView extends VerticalLayout {

    public InicioView() {
        setSpacing(false);

        Image img = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHefDutjw2ztSQwW4UXCziu4lCYtmsZH6aHQ&s",
                "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("Bienvenidos a Nuestro convertor");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("En este Conveertor vamos a encontrar multiples Cosas.😊"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
