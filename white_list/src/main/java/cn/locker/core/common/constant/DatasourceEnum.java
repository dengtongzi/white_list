/**
 * Copyright 2018-2020 dengtongzi  (https://gitee.com/dengtongzi)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.locker.core.common.constant;

/**
 * 多数据源的枚举
 *
 * @author dengtongzi
 * 
 */
public interface DatasourceEnum {

    /**
     * locker数据源
     */
    String DATA_SOURCE_locker = "dataSourcelocker";

    /**
     * 其他业务的数据源（第二个数据库）
     */
    String DATA_SOURCE_BIZ = "dataSourceBiz";

}
