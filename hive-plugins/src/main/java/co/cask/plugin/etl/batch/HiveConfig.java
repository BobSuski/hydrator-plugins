package co.cask.plugin.etl.batch;

import co.cask.cdap.api.plugin.PluginConfig;

/**
 * Created by rsinha on 10/26/15.
 */
public class HiveConfig extends PluginConfig {

  public String dbName;

  public String tableName;

  public String metaStoreURI;
}