package com.sarakhman;

import com.sarakhman.JDBS.JDBSUserDao;
import com.sarakhman.JDBS.JDBSProductDao;
import com.sarakhman.Servise.ProductService;
import com.sarakhman.Servise.SecurityService;
import com.sarakhman.Servise.UserService;
import com.sarakhman.Servlets.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;



public class Starter {


    public static void main(String[] args) throws Exception {

        JDBSUserDao jdbsUserDao = new JDBSUserDao();
        JDBSProductDao jdbsProductDao = new JDBSProductDao();


        UserService userService = new UserService(jdbsUserDao);
        SecurityService securityService = new SecurityService(jdbsUserDao);
        ProductService productService = new ProductService(jdbsProductDao);


        AddGoodsServlet addGoodsServlet = new AddGoodsServlet();
        DeleteGoodsServlet deleteGoodsServlet = new DeleteGoodsServlet();
        EditGoodsServlet editGoodsServlet = new EditGoodsServlet();
        LoginServlet loginServlet = new LoginServlet();
        RegistrationServlet registrationServlet = new RegistrationServlet();
        ShowAllGoodsServlet showAllGoodsServlet = new ShowAllGoodsServlet();

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        servletContextHandler.addServlet(String.valueOf(addGoodsServlet), "/products/add");
        servletContextHandler.addServlet(String.valueOf(deleteGoodsServlet), "/products/delete");
        servletContextHandler.addServlet(String.valueOf(editGoodsServlet), "/products/edit");
        servletContextHandler.addServlet(String.valueOf(loginServlet), "/login");
        servletContextHandler.addServlet(String.valueOf(registrationServlet), "/registration");
        servletContextHandler.addServlet(String.valueOf(showAllGoodsServlet), "/products");


        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();

    }


}
