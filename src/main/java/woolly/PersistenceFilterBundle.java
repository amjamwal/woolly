
package woolly;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PersistenceFilterBundle implements Bundle {

  @Inject
  private Provider<PersistService> persistServiceProvider;
  @Inject
  private UnitOfWork unitOfWork;

  @Override
  public void initialize(Bootstrap<?> bootstrap) {

  }

  @Override
  public void run(Environment environment) {
    FilterRegistration.Dynamic
        persistFilter =
        environment.servlets().addFilter("persistFilter", new PersistFilter(unitOfWork,
                                                                            persistServiceProvider
                                                                                .get()));
    persistFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
  }
}