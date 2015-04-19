package woolly;

import com.google.inject.Stage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hubspot.dropwizard.guice.GuiceBundle;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import fk.sp.common.extensions.RequestContextFilter;
import fk.sp.common.extensions.dropwizard.logging.RequestContextBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import woolly.config.WoollyConfiguration;
import woolly.module.WoollyModule;
import woolly.module.WoollyPersistenceModule;

@Slf4j
public class WoollyService extends Application<WoollyConfiguration> {

  public static void main(String[] args) throws Exception {
    new WoollyService().run(args);
  }

  @Override
  public void initialize(final Bootstrap<WoollyConfiguration> bootstrap) {

    GuiceBundle.Builder<WoollyConfiguration> guiceBundleBuilder = GuiceBundle.newBuilder();

    bootstrap.addBundle(guiceBundleBuilder
                            .setConfigClass(WoollyConfiguration.class)
                            .addModule(new WoollyPersistenceModule())
                            .addModule(new WoollyModule())
                            .build(Stage.DEVELOPMENT));

    bootstrap.addBundle(new MigrationsBundle<WoollyConfiguration>() {
      public DataSourceFactory getDataSourceFactory(
          WoollyConfiguration configuration) {
        return configuration.getDatabaseConfiguration();
      }
    });

    bootstrap.addBundle(new PersistenceFilterBundle());
    bootstrap.addBundle(new RequestContextBundle());
  }

  @Override
  public void run(WoollyConfiguration configuration, Environment environment)
      throws Exception {
    FilterRegistration.Dynamic
        crossOriginFilter =
        environment.servlets().addFilter("crossOriginFilter", CrossOriginFilter.class);
    crossOriginFilter
        .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/api-docs/*");

    FilterRegistration.Dynamic
        requestContextFilter =
        environment.servlets().addFilter("requestContextFilter", RequestContextFilter.class);
    requestContextFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    environment.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }
}
