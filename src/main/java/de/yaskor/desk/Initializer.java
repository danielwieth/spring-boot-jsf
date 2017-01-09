package de.yaskor.desk;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import org.apache.catalina.filters.CorsFilter;
import org.primefaces.application.DialogActionListener;
import org.primefaces.application.DialogNavigationHandler;
import org.primefaces.application.DialogViewHandler;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

/**
 *
 * @author samil kale
 */
@Configuration
public class Initializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        ServletRegistration.Dynamic elResolverInitializer = sc.addServlet("elResolverInit", new HttpServlet() {
            @Override public void init(ServletConfig sc) throws ServletException {
                javax.faces.application.Application application = FacesContext.getCurrentInstance().getApplication();
                application.addELResolver(new SpringBeanFacesELResolver());
                application.setActionListener(new DialogActionListener(application.getActionListener()));
                application.setNavigationHandler(new DialogNavigationHandler((ConfigurableNavigationHandler)application.getNavigationHandler()));
                application.setViewHandler(new DialogViewHandler(application.getViewHandler()));
            }
        });
        elResolverInitializer.setLoadOnStartup(2);

        FilterRegistration.Dynamic corsFilter = sc.addFilter("corsFilter", CorsFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        
        sc.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("primefaces.THEME", "none");
        sc.setInitParameter("primefaces.FONT_AWESOME", "true");
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        sc.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", "true");
        System.setProperty( "jcifs.smb.client.responseTimeout", "5000" );
    }
}
