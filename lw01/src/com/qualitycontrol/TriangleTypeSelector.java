package com.qualitycontrol;

public class TriangleTypeSelector {

    private static final String ISOSCELES = "Isosceles";
    private static final String EQUILATERAL = "Equilateral";
    private static final String NORMAL = "Normal";
    private static final String NOT_TRIANGLE = "Not triangle";

    private double a;
    private double b;
    private double c;
    private String triangleType = "";

    public TriangleTypeSelector(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void selectType() {
        if (this.a == this.b && this.b == this.c) {
            this.triangleType = EQUILATERAL;
            return;
        }
        double max = Math.max(this.a, Math.max(this.b, this.c));
        if (max == this.a) {
            ComparisonTwoSides(max, this.b, this.c);
        }

        if (max == this.b) {
            ComparisonTwoSides(max, this.a, this.c);
        }

        if (max == this.c) {
            ComparisonTwoSides(max, this.a, this.b);
        }
        if (this.triangleType.isEmpty()) {
            this.triangleType = NORMAL;
        }
    }

    private void ComparisonTwoSides(double max, double lhs, double rhs) {
        if (max >= (lhs + rhs)) {
            this.triangleType = NOT_TRIANGLE;
            return;
        }
        if (lhs == rhs) {
            this.triangleType = ISOSCELES;
        }
    }

    public String getTriangleType() {
        return triangleType;
    }

}
