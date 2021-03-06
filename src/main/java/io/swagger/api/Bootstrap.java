package io.swagger.api;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.InputStream;
import java.util.Properties;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Swagger Server")
      .description("No description provided (generated by Swagger Codegen https://github.com/io.swagger-api/io.swagger-codegen)")
      .termsOfService("")
      .contact(new Contact()
        .email(""))
      .license(new License()
        .name("")
        .url("http://unlicense.org"));

    Properties prop = new Properties();
    ServletContext context = config.getServletContext();

    try {
      InputStream input = context.getResourceAsStream("/WEB-INF/tictactoe.properties");
      prop.load(input);

    } catch (Exception e) {
      e.printStackTrace();
    }

    Swagger swagger = new Swagger().info(info);
    Redis rs = new Redis();
    JedisPool jp = rs.createJedisPool(prop);
    context.setAttribute("DB_CONN_POOL",jp);

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
