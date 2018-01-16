/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.render;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractRuntimeObjectModelTest {

    private AbstractRuntimeObjectModel runtimeObjectModel = new AbstractRuntimeObjectModel() {};

    @Test
    public void testResolveProperty() {
        assertEquals(0, runtimeObjectModel.resolveProperty(Collections.EMPTY_LIST, "size"));
        assertNull(runtimeObjectModel.resolveProperty(null, null));
        int[] ints = new int[] {1, 2, 3};
        assertEquals(ints.length, runtimeObjectModel.resolveProperty(ints, "length"));
        Integer[] testArray = new Integer[] {1, 2, 3};
        assertEquals(testArray.length, runtimeObjectModel.resolveProperty(testArray, "length"));
        assertEquals(2, runtimeObjectModel.resolveProperty(testArray, 1));
        assertNull(runtimeObjectModel.resolveProperty(testArray, 3));
        assertNull(runtimeObjectModel.resolveProperty(testArray, -1));
        List<Integer> testList = Arrays.asList(testArray);
        assertEquals(2, runtimeObjectModel.resolveProperty(testList, 1));
        assertNull(runtimeObjectModel.resolveProperty(testList, 3));
        assertNull(runtimeObjectModel.resolveProperty(testList, -1));
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("one", 1);
            put("two", 2);
        }};
        assertEquals(1, runtimeObjectModel.resolveProperty(map, "one"));
        assertNull(runtimeObjectModel.resolveProperty(map, null));
        assertNull(runtimeObjectModel.resolveProperty(map, ""));
        Map<Integer, String> stringMap = new HashMap<Integer, String>(){{
            put(1, "one");
            put(2, "two");
        }};
        assertEquals("one", runtimeObjectModel.resolveProperty(stringMap, 1));
        assertEquals("two", runtimeObjectModel.resolveProperty(stringMap, 2));
    }

}
