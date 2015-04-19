package woolly.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WoollyConfiguration extends Configuration {

  @Valid
  @NotNull
  private DataSourceFactory databaseConfiguration = new DataSourceFactory();

  @Valid
  @NotNull
  private JerseyClientConfiguration clientConfiguration = new JerseyClientConfiguration();

}
