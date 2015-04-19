package woolly.module;

import com.google.common.collect.Lists;
import com.google.inject.persist.jpa.JpaPersistModule;

import org.hibernate.cfg.ImprovedNamingStrategy;

import java.util.Properties;

import fk.sp.common.extensions.dropwizard.jpa.DatabaseHealthCheck;
import fk.sp.common.extensions.guice.db.DatabaseModule;
import fk.sp.common.extensions.guice.jpa.DataSourceProxy;
import fk.sp.common.extensions.guice.jpa.JpaPersistenceModule;
import woolly.PersistenceFilterBundle;

public class WoollyPersistenceModule extends JpaPersistenceModule {

  @Override
  public String getPersistenceUnitName() {
    return "woolly";
  }

  @Override
  protected void configure() {
    install(new DatabaseModule());
    DataSourceProxy dataSourceProxy = new DataSourceProxy();
    bind(DataSourceProxy.class).toInstance(dataSourceProxy);
    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.connection.datasource", dataSourceProxy);
    jpaProperties.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.INSTANCE);
    additionalJpaProperties(jpaProperties);
    install(new JpaPersistModule(getPersistenceUnitName())
                .properties(jpaProperties));

    bind(PersistenceFilterBundle.class);

    bind(DatabaseHealthCheck.class);
  }

  @Override
  protected void additionalJpaProperties(Properties jpaProperties) {
    super.additionalJpaProperties(jpaProperties);
    jpaProperties.put("hibernate.ddl2hbm.auto", "update");
    jpaProperties.put("hibernate.show_sql", false);
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    jpaProperties.put("hibernate.format_sql", false);
    jpaProperties.put("dynamicPersistenceProvider.packagesToScan", Lists.newArrayList(
        "woolly.model"));
  }
}
