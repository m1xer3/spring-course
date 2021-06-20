package ru.danilsibgatyllin;

import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;
import ru.danilsibgatyllin.models.ShoppingBasket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/product-spring/*")
public class ProductWebMain extends HttpServlet {
    private ProductRepository productRepository;
    private ShoppingBasket shoppingBasket;

    @Override
    public void init(){
        this.productRepository=(ProductRepository)getServletContext().getAttribute("productRepository");
        this.shoppingBasket =(ShoppingBasket)getServletContext().getAttribute("shoppingBasket");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> productlist = productRepository.getAllProduct();
        StringBuilder sb = new StringBuilder();
        if(req.getPathInfo()==null){
            sb.append("<table border=\"1\" width=\"600\">\n");
            sb.append("<tr><td><b>Product Name</b></td><td><b>Cost</b></td></tr>\n");
            for (Product product : productlist) {
                sb.append("<tr>\n");
                sb.append("<td><a href=\""+req.getRequestURI()+"/"+product.getId()+"\">"+product.getTitle()+"</a></td><td>"+product.getCost()+"</td>\n");
                sb.append("</tr>\n");
            }
            sb.append("</table>\n");
        }
        else{
            Product product = productRepository.getOneProduct(Long.parseLong(req.getPathInfo().substring(1)));
            if(product!=null){
                sb.append("<h2> Товар: "+product.getTitle()+"</h2>\n");
                sb.append("<h2> Цена: "+product.getCost()+" рублей</h2>\n");
                sb.append("<form method=\"POST\" action=\""+req.getRequestURI()+"?addProduct="+product.getId()+"\">\n" +
                        "<input type=submit value=\"Добавить в корзину\" name=\"add\">\n" +
                        "</form>");
            }
            else {
                sb.append("<h2>Товара с указанным id не существует</h2>");
            }
        }
        sb.append("<a href =\""+req.getContextPath()+"/basket"+"\"> Корзина</a>");
        resp.getWriter().println(sb);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter( "addProduct");
        Product addProduct = productRepository.getOneProduct(Long.parseLong(param));
        shoppingBasket.putProductIntoBasket(addProduct);
        resp.getWriter().println("<h1>Продукт "+addProduct.getTitle()+" добавлен в корзину</h1>");

    }
}
