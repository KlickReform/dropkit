package de.klickreform.dropkit;

import com.sun.jersey.api.core.ResourceConfig;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import de.klickreform.dropkit.exception.ApiExceptionMapper;
import de.klickreform.dropkit.exception.ConstraintViolationExceptionMapper;
import de.klickreform.dropkit.exception.LoggingExceptionMapper;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 * Utility class that provides several methods to activate different
 * features provided by Dropkit.
 *
 * @author Benjamin Bestmann
 */
public class Dropkit {

    /**
     * Remove all of Dropwizard's custom ExceptionMappers and register the ApiExceptionMapper
     * in the JerseyEnvironment to automagically handle all ApiException instances.
     *
     * @param environment Dropwizard Environment
     */
    public static void enableExceptionMapping(Environment environment) {
        // Remove all of Dropwizard's custom ExceptionMappers
        ResourceConfig jrConfig =  environment.jersey().getResourceConfig();
        Set<Object> dwSingletons = jrConfig.getSingletons();
        List<Object> singletonsToRemove = new ArrayList<Object>();
        for (Object s : dwSingletons) {
            if (s instanceof ExceptionMapper && s.getClass().getName().startsWith("com.yammer.dropwizard.jersey.")) {
                singletonsToRemove.add(s);
            }
        }
        for (Object s : singletonsToRemove) {
            jrConfig.getSingletons().remove(s);
        }
        environment.jersey().register(new ApiExceptionMapper());
        environment.jersey().register(new LoggingExceptionMapper());
        environment.jersey().register(new ConstraintViolationExceptionMapper());
    }

    /**
     * Enable all required HTTP headers to allow cross-origin requests.
     *
     * @param environment Dropwizard Environment
     */
    public static void enableCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE,OPTIONS,HEAD");
        filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months
        filter.setInitParameter("allowCredentials", "true");
        filter.setInitParameter("exposedHeaders", "Location");
    }

}
