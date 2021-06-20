package ru.danilsibgatyllin;


import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ShoppingBasket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/basket")
public class BasketWeb extends HttpServlet {

    private ShoppingBasket shoppingBasket;

    @Override
    public void init() {
        this.shoppingBasket =(ShoppingBasket)getServletContext().getAttribute("shoppingBasket");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb =new StringBuilder();
        List<Product> productlist = shoppingBasket.getProductsInBasket();
        sb.append("<h1> Продукты в корзине</h1>\n");
        sb.append("<table border=\"1\" width=\"600\">\n");
        sb.append("<tr><td><b>Product Name</b></td><td><b>Cost</b></td></tr>\n");
        for (Product product : productlist) {
            sb.append("<tr>\n");
            sb.append("<td>"+product.getTitle()+"</td><td>"+product.getCost()+"</td>\n");
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        sb.append("<p>Итого :"+shoppingBasket.getTotalPrice()+"</p>");
        resp.getWriter().println(sb);

    }


}
