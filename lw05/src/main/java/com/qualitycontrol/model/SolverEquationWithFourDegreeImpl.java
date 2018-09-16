package com.qualitycontrol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

//"ax^4 + bx^3 + cx^2 + dx + e = 0"
public class SolverEquationWithFourDegreeImpl implements SolverEquationWithFourDegree {

    private static final Double ZERO = 1E-12;

    private List<Double> result = new ArrayList<Double>();

    private Double a;
    private Double b;
    private Double c;
    private Double d;
    private Double e;

    private String exceptionMessage = "";

    public SolverEquationWithFourDegreeImpl(Double a, Double b, Double c, Double d, Double e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    public void solveEquation() {
        try {
            if (isZero(this.a)) {
                throw new IllegalArgumentException("The first coefficient can't be equal to zero.");
            }
            Double p = getCoefficientP();
            Double q = getCoefficientQ();
            Double r = getCoefficientR();

            List<Double> yRoots = getSolveEquationWithFourRoots(p, q, r);
            List<Double> roots = getXRoots(yRoots);
            if (roots.isEmpty()) {
                throw new IllegalArgumentException("There are no real roots.");
            }
            this.result.addAll(roots);
        } catch (Exception e) {
            this.exceptionMessage = e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    public List<Double> getRoots() {
        return result;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    private List<Double> getXRoots(List<Double> depressedRoots) {
        List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < depressedRoots.size(); ++i) {
            double element = (depressedRoots.get(i) - this.b / (4 * this.a));
            if (result.indexOf(element) == -1) {
                result.add(element);
            }
        }
        return result;
    }

    private List<Double> getSolveEquationWithFourRoots(Double p, Double q, Double r) {
        if (isZero(q)) {
            return getSolveWhenQEqualZero(p, r);
        }
        return getSolveWhenQUnequalZero(p, q, r);
    }

    private List<Double> getSolveWhenQUnequalZero(Double p, Double q, Double r) {
        List<Double> result = new ArrayList<Double>();
        Double a1 = p;
        Double a2 = (p * p - 4 * r) / 4;
        Double a3 = -q * q / 8;
        List<Double> realRootsOfEquationWithThirdDegree = getRootsOfEquationWithThirdDegree(a1, a2, a3);
        Double z0 = Collections.max(realRootsOfEquationWithThirdDegree);
        Double d1 = 2 * z0 - 4 * ((p / 2) + z0 + (q / (2 * Math.sqrt(2 * z0))));
        if (isMoreZero(d1)) {
            result.add((Math.sqrt(2 * z0) + Math.sqrt(d1)) / 2);
            result.add((Math.sqrt(2 * z0) - Math.sqrt(d1)) / 2);
        }
        if (isZero(d1)) {
            result.add(Math.sqrt(2 * z0) / 2);
        }

        double d2 = 2 * z0 - 4 * ((p / 2) + z0 - (q / (2 * Math.sqrt(2 * z0))));
        if (isMoreZero(d2)) {
            result.add((-Math.sqrt(2 * z0) + Math.sqrt(d2)) / 2);
            result.add((-Math.sqrt(2 * z0) - Math.sqrt(d2)) / 2);
        }
        if (isZero(d2)) {
            result.add(-Math.sqrt(2 * z0) / 2);
        }

        return result;
    }

    private List<Double> getRootsOfEquationWithThirdDegree(Double a1, Double a2, Double a3) {
        List<Double> result = new ArrayList<Double>();
        Double Q = (3 * a2 - Math.pow(a1, 2)) / 9;
        Double R = (9 * a2 * a1 - 27 * a3 - 2 * Math.pow(a1, 3)) / 54;
        Double D = Math.pow(Q, 3) + Math.pow(R, 2);
        Double S = Math.cbrt(R + Math.sqrt(D));
        Double T = Math.cbrt(R - Math.sqrt(D));

        if (isZero(D) && isZero(R)) {
            result.add(-a1 / 3);
        }

        if (isZero(D) && !isZero(R)) {
            result.add(2 * Math.cbrt(R) - a1 / 3);
            result.add((-Math.cbrt(R)) - a1 / 3);
        }

        if (isMoreZero(D)) {
            result.add(((-a1 / 3) + (S + T)));
        }

        if (isLessZero(D)) {
            double O = (Math.acos(R / Math.sqrt(Math.pow(-Q, 3)))) / 3.0;
            result.add(2 * Math.sqrt(-Q) * Math.cos(O) - (a1 / 3));
            result.add(2 * Math.sqrt(-Q) * Math.cos(O + 2 * Math.PI / 3) - (a1 / 3));
            result.add(2 * Math.sqrt(-Q) * Math.cos(O - 2 * Math.PI / 3) - (a1 / 3));
        }
        result.removeAll(Collections.singleton(Double.NaN));
        return result;
    }

    private List<Double> getSolveWhenQEqualZero(Double p, Double r) {
        double discriminant = getDiscriminantOfQuadraticEquation(1.0, p, r);
        List<Double> result = new ArrayList<Double>();
        if (discriminant >= 0) {
            result.addAll(getSolveEquationWithTwoRootsWhenDiscriminantNotLessThenZero(-p - Math.sqrt(discriminant)));
            result.addAll(getSolveEquationWithTwoRootsWhenDiscriminantNotLessThenZero(-p + Math.sqrt(discriminant)));
        }
        return result;
    }

    private List<Double> getSolveEquationWithTwoRootsWhenDiscriminantNotLessThenZero(Double D) {
        List<Double> result = new ArrayList<Double>();
        if (isMoreZero(D)) {
            result.add(Math.sqrt(D / 2));
            result.add(-Math.sqrt(D / 2));
        }
        if (isZero(D)) {
            result.add(0.0);
        }
        return result;
    }

    private double getDiscriminantOfQuadraticEquation(Double a, Double b, Double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    private Double getCoefficientP() {
        return (8 * this.a * this.c - 3 * Math.pow(this.b, 2)) / (8 * Math.pow(this.a, 2));
    }

    private Double getCoefficientQ() {
        double numerator = (8 * Math.pow(this.a, 2) * this.d + Math.pow(this.b, 3) - 4 * this.a * this.b * this.c);
        double denominator = (8 * Math.pow(this.a, 3));
        return numerator / denominator;
    }

    private Double getCoefficientR() {
        double numerator = (16 * this.a * Math.pow(b, 2) * this.c - 64 * Math.pow(this.a, 2) * this.b * this.d - 3 * Math.pow(this.b, 4) + 256 * Math.pow(this.a, 3) * this.e);
        double denominator = (256 * Math.pow(this.a, 4));
        return numerator / denominator;
    }

    private boolean isZero(Double value) {
        return Math.abs(value) < ZERO;
    }

    private boolean isMoreZero(Double value) {
        return value > ZERO;
    }

    private boolean isLessZero(Double value) {
        return value < ZERO;
    }

}
