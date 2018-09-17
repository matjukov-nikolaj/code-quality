package com.qualitycontrol;

import java.util.ArrayList;
import java.util.List;

public class ParametersController {

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar lw01.jar <a> <b> <c>";
    private static final Double ZERO = 1E-12;

    private String[] args;
    private List<Double> triangleSides = new ArrayList<>();

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        try {
            if (this.args.length != 3) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException argumentEx) {
            System.out.println(INCORRECT_DATA);
            System.out.println("java -jar lw01.jar <a> <b> <c>");
        }
    }

    public void checkSidesOfATriangle() {
        try {
            for (int i = 0; i < this.args.length; ++i) {
                double value = Double.parseDouble(this.args[i]);
                if (value < ZERO) {
                    throw new Exception(INCORRECT_DATA);
                }
                triangleSides.add(value);
            }
        } catch (Exception ex) {
            triangleSides.clear();
            System.out.println(INCORRECT_DATA);
        }
    }

    public List<Double> getTriangleSides() {
        return triangleSides;
    }
}
