package com.qualitycontrol.model;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.qualitycontrol.model.*;

public class SolverEquationWithFourDegreeImplTests {

    @Test
    public void throws_an_exception_when_first_equals_to_zero() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(0.0, 1.0, 1.0, 1.0, 1.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertEquals(message, "The first coefficient can't be equal to zero.");
        assertEquals(result, new ArrayList<Double>());
    }

    @Test
    public void throws_an_exception_when_equation_has_no_valid_roots() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(7.0, 0.0, 0.0, 0.0, 9.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertEquals(message, "There are no real roots.");
        assertEquals(result, new ArrayList<Double>());
    }

    @Test
    public void get_correct_result_when_first_argument_is_one_and_other_arguments_is_zero() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 0.0, 0.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_two_real_roots() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 1.0, 1.0, 1.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        expectedResult.add(-1.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_four_real_roots() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 4.0, -4.0, -20.0, -5.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(2.236067);
        expectedResult.add(-0.267949);
        expectedResult.add(-2.236067);
        expectedResult.add(-3.73205);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_two_real_roots_from_hard_equation() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 3.0, 3.0, -1.0, -6.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(1.0);
        expectedResult.add(-2.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_correct_result_when_first_argument_is_two_and_other_arguments_is_zero() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(2.0, 0.0, 0.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_one_real_root_when_D_is_zero() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(3.0, 0.0, 0.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_one_real_root() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, -1.0, 1.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_two_correct_real_roots() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 1.0, 0.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        expectedResult.add(-1.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_three_correct_real_roots() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, 0.0, -1.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        expectedResult.add(1.0);
        expectedResult.add(-1.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_two_correct_real_roots_when_equation_have_two_real_solutions() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, -7.0, -1.0, 4.0, -9.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(7.08675);
        expectedResult.add(-1.23359);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_three_real_root() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(2.0, 3.0, 1.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(0.0);
        expectedResult.add(-0.5);
        expectedResult.add(-1.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    @Test
    public void get_two_correct_real_root_when_d2_is_equal_zero() {
        SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(1.0, -1.0, 0.0, 0.0, 0.0);
        equationSolver.solveEquation();
        List<Double> result = equationSolver.getRoots();
        String message = equationSolver.getExceptionMessage();
        assertTrue(message.equals(""));
        List<Double> expectedResult = new ArrayList<Double>();
        expectedResult.add(1.0);
        expectedResult.add(0.0);
        assertEquals(result.size(), expectedResult.size());
        checkTwoListForTheEquivalence(result, expectedResult);
    }

    private void checkTwoListForTheEquivalence(List<Double> lhs, List<Double> rhs) {
        if (lhs.size() == rhs.size()) {
            for (int i =0; i < lhs.size(); ++i) {
                DecimalFormat df = new DecimalFormat("#.#####");
                assertEquals(df.format(lhs.get(i)), df.format(rhs.get(i)));
            }
        }
    }

}
