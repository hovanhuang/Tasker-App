package com.project.tasker;

import com.project.tasker.core.Task;
import com.project.tasker.dao.TaskDao;
import com.project.tasker.resource.TaskResource;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class MyService extends Application<MyConfiguration> {
    public static final String CORSFILTER = "CORS";
    private static final String CORS_SUPPORTED_METHODS = "cors.supportedMethods";
    private static final String CORS_SUPPORTS_CREDENTIALS = "cors.supportsCredentials";
    private static final String CORS_EXPOSED_HEADERS = "cors.exposedHeaders";
    private static final String CORS_SUPPORTED_HEADERS = "cors.supportedHeaders";
    private static final String CORS_ALLOW_ORIGIN = "cors.allowOrigin";

    public static void main(String[] args) throws Exception {
        new MyService().run(args);
    }

    private final HibernateBundle<MyConfiguration> hibernateBundle =
            new HibernateBundle<MyConfiguration>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MyConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new MigrationsBundle<MyConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MyConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(MyConfiguration configuration, Environment environment) {
        enableCROS(configuration, environment);
        final TaskDao dao = new TaskDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TaskResource(dao));
    }

    private void enableCROS(MyConfiguration configuration, Environment environment) {

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("allowedHeaders",
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        cors.setInitParameter("allowCredentials", "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        // DO NOT pass a preflight request to down-stream auth filters
        // unauthenticated preflight requests should be permitted by spec
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
    }
}