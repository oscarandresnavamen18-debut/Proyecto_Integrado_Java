package com.example.application.views.inicio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class InicioView extends VerticalLayout {

    public InicioView() {
         H1 titulo = new H1("📏 Conversor de Longitud");

        //  Input
        NumberField input = new NumberField("Valor");

        //  Unidad origen
        ComboBox<String> origen = new ComboBox<>("De");
        origen.setItems("km","m","cm","mm","nm","angstrom","micron","in","ft","yd","mi","nmi");

        //  Unidad destino
        ComboBox<String> destino = new ComboBox<>("A");
        destino.setItems("km","m","cm","mm","nm","angstrom","micron","in","ft","yd","mi","nmi");

        //  Resultado
        H2 resultado = new H2("Resultado: 0");

        //  Botón
        Button boton = new Button("Convertir", e -> {

            //  Validación
            if (input.getValue() == null || origen.getValue() == null || destino.getValue() == null) {
                Notification.show("Completa todos los campos");
                return;
            }

            //  Lógica
            ConvertorLongitud c = new ConvertorLongitud(
                    "Longitud",
                    "📏",
                    input.getValue(),
                    origen.getValue(),
                    destino.getValue(),
                    0
            );

            c.convertir();

            //  Mostrar resultado
            resultado.setText("Resultado: " + c.getResultado());
        });

        //  Estilos básicos (centrado)
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        //  Agregar todo
        add(titulo, input, origen, destino, boton, resultado);
    }
    }

