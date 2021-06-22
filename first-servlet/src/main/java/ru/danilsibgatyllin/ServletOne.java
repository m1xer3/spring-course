package ru.danilsibgatyllin;


import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ServletOne extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init(){
        this.productRepository=(ProductRepository)getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> productlist = productRepository.getAllProduct();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\" width=\"600\">\n");
        sb.append("<tr><td><b>Product Name</b></td><td><b>Cost</b></td></tr>\n");
        for (Product product : productlist) {
            sb.append("<tr>\n");
            sb.append("<td>"+product.getTitle()+"</td><td>"+product.getCost()+"</td>\n");
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        resp.getWriter().println(sb);
    }

}
