package ru.onetwo33;

import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.findAll();

        resp.getWriter().println("<table border=\"1\">");
            resp.getWriter().println("<tr>");
                resp.getWriter().println("<th>ID</th>");
                resp.getWriter().println("<th>Title</th>");
                resp.getWriter().println("<th>Cost</th>");
            resp.getWriter().println("</tr>");
            for (Product product : products) {
                resp.getWriter().println("<tr>");
                    resp.getWriter().println("<td>" + product.getId() + "</td>");
                    resp.getWriter().println("<td>" + product.getName() + "</td>");
                    resp.getWriter().println("<td>" + product.getCost() + "</td>");
                resp.getWriter().println("</tr>");
            }
        resp.getWriter().println("</table>");
    }
}
