package com.qualitycontrol;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ParametersController parametersController = new ParametersController(args);
            PrintStream st = new PrintStream(new FileOutputStream(args[0]));
            System.setErr(st);
            System.setOut(st);
            parametersController.checkSidesOfATriangle();
            List<Double> sides = parametersController.getTriangleSides();
            if (sides.isEmpty()) {
                return;
            }
            TriangleTypeSelector triangleTypeSelector = new TriangleTypeSelector(sides.get(0), sides.get(1), sides.get(2));
            triangleTypeSelector.selectType();
            String result = triangleTypeSelector.getTriangleType();
            System.out.println(result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
