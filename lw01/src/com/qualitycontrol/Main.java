package com.qualitycontrol;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ParametersController parametersController = new ParametersController(args);
        parametersController.checkSidesOfATriangle();
        List<Double> sides = parametersController.getTriangleSides();
        if (sides.isEmpty()) {
            return;
        }
        TriangleTypeSelector triangleTypeSelector = new TriangleTypeSelector(sides.get(0), sides.get(1), sides.get(2));
        triangleTypeSelector.selectType();
        String result = triangleTypeSelector.getTriangleType();
        System.out.println(result);
    }


}
