package woolly.common.jpa;

import com.google.common.collect.Lists;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

public class SimplePersistenceUnitInfo implements PersistenceUnitInfo {

  private String persistenceUnitName;
  private String persistenceProviderClassName;
  private PersistenceUnitTransactionType transactionType;
  private DataSource nonJtaDataSource;
  private List<String> mappingFileNames = Lists.newArrayList();
  private List<String> managedClassNames = Lists.newArrayList();

  @Override
  public DataSource getJtaDataSource() {
    return null;
  }


  @Override
  public List<URL> getJarFileUrls() {
    return Collections.emptyList();
  }

  @Override
  public URL getPersistenceUnitRootUrl() {
    return null;
  }

  @Override
  public boolean excludeUnlistedClasses() {
    return false;
  }

  @Override
  public SharedCacheMode getSharedCacheMode() {
    return null;
  }

  @Override
  public ValidationMode getValidationMode() {
    return null;
  }

  @Override
  public Properties getProperties() {
    return null;
  }

  @Override
  public String getPersistenceXMLSchemaVersion() {
    return "2.0";
  }

  @Override
  public ClassLoader getClassLoader() {
    return this.getClass().getClassLoader();
  }

  @Override
  public void addTransformer(ClassTransformer transformer) {

  }

  @Override
  public ClassLoader getNewTempClassLoader() {
    return this.getClass().getClassLoader();
  }

    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

    public String getPersistenceProviderClassName() {
        return this.persistenceProviderClassName;
    }

    public PersistenceUnitTransactionType getTransactionType() {
        return this.transactionType;
    }

    public DataSource getNonJtaDataSource() {
        return this.nonJtaDataSource;
    }

    public List<String> getMappingFileNames() {
        return this.mappingFileNames;
    }

    public List<String> getManagedClassNames() {
        return this.managedClassNames;
    }

    public void setPersistenceUnitName(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    public void setPersistenceProviderClassName(String persistenceProviderClassName) {
        this.persistenceProviderClassName = persistenceProviderClassName;
    }

    public void setTransactionType(PersistenceUnitTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setNonJtaDataSource(DataSource nonJtaDataSource) {
        this.nonJtaDataSource = nonJtaDataSource;
    }

    public void setMappingFileNames(List<String> mappingFileNames) {
        this.mappingFileNames = mappingFileNames;
    }

    public void setManagedClassNames(List<String> managedClassNames) {
        this.managedClassNames = managedClassNames;
    }
}
