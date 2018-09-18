package servlets;

import controller.BrokenLinksController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/s")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long startTime = System.currentTimeMillis();
            response.setContentType("text/html");
            String url = request.getParameter("url");
            if (!url.equals("")) {
                BrokenLinksController linksController = new BrokenLinksController(url);
                linksController.findLinks();
                Map<String, Integer> brokenLinks = linksController.getBrokenLinks();
                Map<String, Integer> workingLinks = linksController.getWorkingLinks();
                request.setAttribute("brokenLinks", brokenLinks);
                request.setAttribute("workingLinks", workingLinks);
                Long timeInSeconds = (System.currentTimeMillis() - startTime) / 1000L;
                request.setAttribute("time", timeInSeconds);
            }
            forwardDispatcher(request, response, "/display.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
