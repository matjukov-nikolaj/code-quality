package com.qualitycontrol;

public class TriangleTypeSelector {

    private static final String ISOSCELES = "Равнобедренный";
    private static final String EQUILATERAL = "Равносторонний";
    private static final String NORMAL = "Обычный";
    private static final String NOT_TRIANGLE = "Не треугольник";

    private double firstSide;
    private double secondSide;
    private double thirdSide;
    private String triangleType = "";

    public TriangleTypeSelector(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public void selectType() {
        if (this.firstSide == this.secondSide && this.secondSide == this.thirdSide) {
            this.triangleType = EQUILATERAL;
            return;
        }
        double max = Math.max(this.firstSide, Math.max(this.secondSide, this.thirdSide));
        if (max == this.firstSide) {
            ComparisonTwoSides(max, this.secondSide, this.thirdSide);
        }

        if (max == this.secondSide) {
            ComparisonTwoSides(max, this.firstSide, this.thirdSide);
        }

        if (max == this.thirdSide) {
            ComparisonTwoSides(max, this.firstSide, this.secondSide);
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
