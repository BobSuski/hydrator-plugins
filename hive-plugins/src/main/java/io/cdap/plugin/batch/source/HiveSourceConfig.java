/*
 * Copyright © 2015-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.plugin.batch.source;

import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.data.schema.Schema;
import io.cdap.plugin.batch.HiveConfig;

import java.io.IOException;
import javax.annotation.Nullable;

/**
 * Configurations for Hive batch source
 */
public class HiveSourceConfig extends HiveConfig {
  @Name(Hive.PARTITIONS)
  @Description("Hive expression filter for scan defining partitions to read. For example if your table is " +
    "partitioned on 'type' and you want to read 'type1' partition: 'type = \"type1\"'" +
    "This filter must reference only partition columns. Values from other columns will cause the pipeline to fail.")
  @Nullable
  public String partitions;

  @Name(Hive.SCHEMA)
  @Description("Optional schema to use while reading from Hive table. If no schema is provided then the " +
    "schema of the source table will be used. Note: If you want to use a hive table which has non-primitive types as " +
    "a source then you should provide a schema here with non-primitive fields dropped else your pipeline will fail.")
  @Nullable
  public String schema;

  /**
   * @return {@link Schema} of the dataset if one was given else null
   */
  @Nullable
  public Schema getSchema() {
    try {
      return schema == null ? null : Schema.parseJson(schema);
    } catch (IOException e) {
      throw new IllegalArgumentException(String.format("Unable to parse schema '%s'. Reason: %s",
                                                       schema, e.getMessage()), e);
    }
  }
}
