package servlets;

import controller.BrokenLinksController;

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
//        super.doPost(request, response);
        String url = request.getParameter("url");
        Map<String, Integer> brokenLinks = null;
        Map<String, Integer> workingLinks = null;
        if (url != "") {
            BrokenLinksController linksController = new BrokenLinksController(url);
            linksController.findAllLinks();
            linksController.findBrokenLinks();
            brokenLinks = linksController.getBrokenLinks();
            workingLinks = linksController.getWorkingLinks();
            request.setAttribute("brokenLinks", brokenLinks);
            request.setAttribute("workingLinks", workingLinks);
        }
        RequestDispatcher req = request.getRequestDispatcher("index.jsp");
        req.include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
