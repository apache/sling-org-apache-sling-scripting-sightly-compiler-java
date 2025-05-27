/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.scripting.sightly.java.compiler;

import org.apache.sling.commons.compiler.source.JavaEscapeHelper;

/**
 * The {@code JavaEscapeUtils} provides useful methods for escaping or transforming invalid Java tokens to valid ones that could be used in
 * generated Java source code.
 *
 * @deprecated since version 2.1.0 of the API; see {@link JavaEscapeHelper} for a replacement.
 */
@Deprecated
public class JavaEscapeUtils {

    /**
     * Converts the given identifier to a legal Java identifier
     *
     * @param identifier the identifier to convert
     * @return legal Java identifier corresponding to the given identifier
     */
    public static String makeJavaIdentifier(String identifier) {
        return JavaEscapeHelper.getJavaIdentifier(identifier);
    }

    /**
     * Mangle the specified character to create a legal Java class name.
     *
     * @param ch the character to mangle
     * @return the mangled
     */
    public static String mangleChar(char ch) {
        return JavaEscapeHelper.escapeChar(ch);
    }

    /**
     * Provided a mangled string (obtained by calling {@link #mangleChar(char)}) it will will return the character that was mangled.
     *
     * @param mangled the mangled string
     * @return the original character
     */
    public static char unmangle(String mangled) {
        return JavaEscapeHelper.unescape(mangled);
    }

    /**
     * Converts the given scriptName to a Java package or fully-qualified class name
     *
     * @param scriptName the scriptName to convert
     * @return Java package corresponding to the given scriptName
     */
    public static String makeJavaPackage(String scriptName) {
        return JavaEscapeHelper.makeJavaPackage(scriptName);
    }

    /**
     * Test whether the argument is a Java keyword.
     *
     * @param key the String to test
     * @return {@code true} if the String is a Java keyword, {@code false} otherwise
     */
    public static boolean isJavaKeyword(String key) {
        return JavaEscapeHelper.isJavaKeyword(key);
    }
}
