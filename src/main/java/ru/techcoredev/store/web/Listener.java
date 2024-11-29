package ru.techcoredev.store.web;

import ru.techcoredev.store.db.dbconnect.DBType;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    private static DBType dbType = DBType.HIBERNATE_POSTGRES;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("dbType", dbType);
    }
}
