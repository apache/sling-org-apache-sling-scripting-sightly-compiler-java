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
package org.apache.sling.scripting.sightly.java.compiler.impl.operator;

import org.apache.sling.scripting.sightly.compiler.expression.nodes.BinaryOperator;
import org.apache.sling.scripting.sightly.java.compiler.impl.ExpressionTranslator;
import org.apache.sling.scripting.sightly.java.compiler.impl.JavaSource;
import org.apache.sling.scripting.sightly.java.compiler.impl.SourceGenConstants;
import org.apache.sling.scripting.sightly.java.compiler.impl.Type;

/**
 * Generator for numeric value
 */
public class NumericOpGen implements BinaryOpGen {

    private static final String OBJECT_NAME = BinaryOperator.class.getName();
    private static final String METHOD_NAME = "eval";

    private final String javaOperator;
    private final BinaryOperator binaryOperator;

    public NumericOpGen(String javaOperator, BinaryOperator binaryOperator) {
        this.javaOperator = javaOperator;
        this.binaryOperator = binaryOperator;
    }

    @Override
    public Type returnType(Type leftType, Type rightType) {
        return commonType(leftType, rightType);
    }

    @Override
    public void generate(JavaSource source, ExpressionTranslator visitor, TypedNode left, TypedNode right) {
        Type commonType = commonType(left.getType(), right.getType());
        source.objectModel()
                .startCall(SourceGenConstants.ROM_TO_NUMBER, true)
                .startMethodCall(OBJECT_NAME + "." + binaryOperator.name(), METHOD_NAME);
        left.getNode().accept(visitor);
        source.separateArgument();
        right.getNode().accept(visitor);
        source.endCall()
                .endCall()
                .startCall(commonType.getNativeClass() + "Value", true)
                .endCall();
    }

    protected Type commonType(Type leftType, Type rightType) {
        if (leftType == rightType && leftType == Type.LONG) {
            return Type.LONG;
        }
        return Type.DOUBLE;
    }
}
