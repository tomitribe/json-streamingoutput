/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.superbiz;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.*;
import javax.ws.rs.core.StreamingOutput;

import java.io.IOException;
import java.io.OutputStream;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Lock(READ)
@Singleton
@Path("/color")
public class ColorService {

    @GET
    public StreamingOutput get() {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                    final JsonGenerator generator = Json.createGenerator(outputStream);
                    generator.writeStartArray();

                    for (int i = 0; i < 10000000; i++) {
                        generator.writeStartObject();
                        generator.write("name","tomee");
                        generator.write("status","awesome");
                        generator.writeEnd();
                        generator.flush();
                    }

                    generator.writeEnd();
                    generator.flush();
            }
        };
    }


}
