package com.example.application.views.inicio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Menu;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Convertidor Peso/Masa")
@Route("convertidor")
@Menu(order = 1, icon = LineAwesomeIconUrl.TOOLBOX_SOLID)
public class ConvertorView extends VerticalLayout {

    public ConvertorView() {
        Div card = new Div();
        card.getStyle()
                .set("background", "#ffffff")
                .set("padding", "30px")
                .set("border-radius", "15px")
                .set("box-shadow", "0 8px 20px rgba(0,0,0,0.1)")
                .set("width", "350px")
                .set("text-align", "center");

        H1 titulo = new H1("⚖️ Convertidor");
        titulo.getStyle().set("margin", "0");

        H4 subtitulo = new H4("Peso / Masa");
        subtitulo.getStyle().set("color", "gray");

        NumberField valor = new NumberField();
        valor.setPlaceholder("Ingrese valor");
        valor.setWidthFull();

        ComboBox<String> origen = new ComboBox<>("De");
        origen.setItems("Kilogramos", "Libras", "Gramos", "Onzas", "Toneladas");
        origen.setWidthFull();

        ComboBox<String> destino = new ComboBox<>("A");
        destino.setItems("Kilogramos", "Libras", "Gramos", "Onzas", "Toneladas");
        destino.setWidthFull();

        Button convertirBtn = new Button("Convertir");
        convertirBtn.getStyle()
                .set("background", "#4CAF50")
                .set("color", "white")
                .set("width", "100%")
                .set("border-radius", "10px");
        H1 resultadoTexto = new H1("0");
        resultadoTexto.getStyle()
                .set("color", "#4CAF50")
                .set("margin-top", "15px");

        convertirBtn.addClickListener(e -> {

            if (valor.isEmpty() || origen.isEmpty() || destino.isEmpty()) {
                Notification.show(" Error: Completa los campos");
                return;
            }

            ConvertorPesoMasa convertidor = new ConvertorPesoMasa(
                    "Peso",
                    "⚖️",
                    valor.getValue(),
                    origen.getValue(),
                    destino.getValue());

            double resultado = convertidor.convertir();

            resultadoTexto.setText(String.format("%.2f", resultado));
        });

        card.add(titulo, subtitulo, valor, origen, destino, convertirBtn, resultadoTexto);

        //  CENTRAR 
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(card);
    }
}