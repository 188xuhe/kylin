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

package org.apache.kylin.engine.mr.steps.filter;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UHCDictPathFilter implements PathFilter {
    private static final Logger logger = LoggerFactory.getLogger(UHCDictPathFilter.class);

    private static final String DCIFILE_POSTFIX = ".dci";

    @Override
    public boolean accept(Path path) {

        Pattern pattern = Pattern.compile(DCIFILE_POSTFIX);
        Matcher matcher = pattern.matcher(path.getName().toLowerCase(Locale.ROOT));

        if (matcher.find()) {
            logger.info("filter file: " + path.getName());
            return false;
        }

        return true;
    }
}