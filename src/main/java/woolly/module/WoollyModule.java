package woolly.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.LoggingFilter;

import fk.sp.common.extensions.dropwizard.db.HasDataSourceFactory;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import woolly.config.WoollyConfiguration;
import woolly.resource.TestResource;
import woolly.service.FeeService;
import woolly.service.FeeServiceImpl;


public class WoollyModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(FeeService.class).to(FeeServiceImpl.class);

    bind(TestResource.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  Client providesJerseyClient(Provider<Environment> environmentProvider,
                              JerseyClientConfiguration clientConfiguration) {
    Client
        jerseyClient =
        new JerseyClientBuilder(environmentProvider.get()).using(clientConfiguration)
            .build("Woolly");
    jerseyClient.addFilter(new LoggingFilter());
    return jerseyClient;
  }

  @Provides
  HasDataSourceFactory providesDatabaseConfiguration(
      final Provider<WoollyConfiguration> woollyConfigurationProvider) {
    return () -> woollyConfigurationProvider.get().getDatabaseConfiguration();
  }

  @Provides
  @Singleton
  ObjectMapper providesObjectMapper() {
    return Jackson.newObjectMapper();
  }

}
