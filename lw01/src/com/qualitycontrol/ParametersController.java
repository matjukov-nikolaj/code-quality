package com.qualitycontrol;

import java.util.ArrayList;
import java.util.List;

public class ParametersController {

    private static final String INCORRECT_DATA = "Incorrect data.";
    private static final Double ZERO = 1E-12;

    private String[] args;
    private List<Double> triangleSides = new ArrayList<>();

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        try {
            if (this.args.length != 4) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException argumentEx) {
            System.out.println(argumentEx.getMessage());
        }
    }

    public void checkSidesOfATriangle() {
        try {
            for (int i = 1; i < this.args.length; ++i) {
                double value = Double.parseDouble(this.args[i]);
                if (value < ZERO) {
                    throw new Exception(INCORRECT_DATA);
                }
                triangleSides.add(value);
            }
        } catch (Exception ex) {
            triangleSides.clear();
            System.out.println(ex.getMessage());
        }
    }

    public List<Double> getTriangleSides() {
        return triangleSides;
    }
}
