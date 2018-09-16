package servlets;

import com.qualitycontrol.model.SolverEquationWithFourDegreeImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/s")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            Double a = Double.parseDouble(request.getParameter("a"));
            Double b = Double.parseDouble(request.getParameter("b"));
            Double c = Double.parseDouble(request.getParameter("c"));
            Double d = Double.parseDouble(request.getParameter("d"));
            Double e = Double.parseDouble(request.getParameter("e"));
            SolverEquationWithFourDegreeImpl equationSolver = new SolverEquationWithFourDegreeImpl(a, b, c, d, e);
            equationSolver.solveEquation();
            List<Double> result = equationSolver.getRoots();
            String message = equationSolver.getExceptionMessage();
            request.setAttribute("message", message);
            if (message.equals("")) {
                request.setAttribute("result", result);
            }
            forwardDispatcher(request, response, "/display.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Incorrect data. Please enter the coefficients correctly.");
            System.out.println(e.getMessage());
            forwardDispatcher(request, response, "/display.jsp");
        }
    }

    private void forwardDispatcher(HttpServletRequest request, HttpServletResponse response, String template) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(template);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            forwardDispatcher(request, response, "/index.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
