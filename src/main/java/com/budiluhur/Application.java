package com.budiluhur;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import java.io.File;

public class Application {

    public static void main(
            String[] args)
            throws Exception {
        System.out.println(
                "Menjalankan Embedded "
                        + "Tomcat Server...");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        Context ctx = tomcat.addContext("",
                new File(".").getAbsolutePath());
        Tomcat.addServlet(ctx, "ProductServlet",
                new ProductServlet());
        Tomcat.addServlet(ctx, "CategoryServlet",
                new CategoryServlet());
        ctx.addServletMappingDecoded(
                "/products", "ProductServlet");
        ctx.addServletMappingDecoded(
                "/categories", "CategoryServlet");
        System.out.println("Server berhasil "
                + "berjalan di: "
                + "http://localhost:8080/products");
        tomcat.start();
        tomcat.getServer().await();
    }
}