/**
 * Copyright 2018-2020 dengtongzi  
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
package cn.locker.core.converter;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import cn.locker.core.reqres.request.RequestData;

import java.util.List;

/**
 * 请求转化处理器
 *
 * @author dengtongzi
 * 
 */
public class RequestDataTypeMethodProcessor extends RequestResponseBodyMethodProcessor {

    public RequestDataTypeMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestData.class);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return false;
    }
}

