package project1.Listener; /**
 * @param
 * @return
 */

import jdk.nashorn.internal.objects.annotations.Property;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener()
public class ApplicationListener implements ServletContextListener{

    public ApplicationListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        InputStream inputStream = ApplicationListener.class.getResourceAsStream("application.properties");
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
            String domain = properties.getProperty("domain");
            sce.getServletContext().setAttribute("domain",domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
