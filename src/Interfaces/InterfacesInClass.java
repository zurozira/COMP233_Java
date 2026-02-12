package Interfaces;

import java.util.Arrays;

public class InterfacesInClass {

    public static void main(String[] args) {
        Widget[] widgets = new Widget[4];
        widgets[0] = new Widget(1001, "Screw", 36);
        widgets[1] = new Widget(1006, "Bolt", 32);
        widgets[2] = new Widget(1003, "Zebra", 3);
        widgets[3] = new Widget(1006, "Screw", 400);

        for (Widget widget : widgets) {
            System.out.print(widget);
        }

        Arrays.sort(widgets);

        System.out.println("AFTER SORTING: ");

        for (Widget widget : widgets) {
            System.out.print(widget);
        }
    }
}
