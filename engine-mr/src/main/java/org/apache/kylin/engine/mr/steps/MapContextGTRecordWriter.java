/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kylin.engine.mr.steps;

import java.io.IOException;

import org.apache.hadoop.mapreduce.MapContext;
import org.apache.kylin.cube.CubeSegment;
import org.apache.kylin.cube.model.CubeDesc;
import org.apache.kylin.engine.mr.ByteArrayWritable;

/**
 */
public class MapContextGTRecordWriter extends KVGTRecordWriter {

    protected MapContext<?, ?, ByteArrayWritable, ByteArrayWritable> mapContext;

    public MapContextGTRecordWriter(MapContext<?, ?, ByteArrayWritable, ByteArrayWritable> mapContext, CubeDesc cubeDesc, CubeSegment cubeSegment) {
        super(cubeDesc, cubeSegment);
        this.mapContext = mapContext;
    }

    @Override
    protected void writeAsKeyValue(ByteArrayWritable key, ByteArrayWritable value) throws IOException {
        try {
            mapContext.write(key, value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }

}