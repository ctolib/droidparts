/**
 * Copyright 2013 Alex Yanchenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.droidparts.reflect.util;

import java.util.HashMap;
import java.util.HashSet;

import org.droidparts.reflect.type.AbstractHandler;
import org.droidparts.reflect.type.BooleanHandler;
import org.droidparts.reflect.type.ByteArrayHandler;
import org.droidparts.reflect.type.ByteHandler;
import org.droidparts.reflect.type.CharacterHandler;
import org.droidparts.reflect.type.DateHandler;
import org.droidparts.reflect.type.DoubleHandler;
import org.droidparts.reflect.type.EnumHandler;
import org.droidparts.reflect.type.FloatHandler;
import org.droidparts.reflect.type.IntegerHandler;
import org.droidparts.reflect.type.LongHandler;
import org.droidparts.reflect.type.ShortHandler;
import org.droidparts.reflect.type.StringHandler;
import org.droidparts.reflect.type.UUIDHandler;
import org.droidparts.reflect.type.UriHandler;

public class TypeHandlerRegistry {

	private static final HashSet<AbstractHandler<?>> handlers = new HashSet<AbstractHandler<?>>();

	private static final HashMap<Class<?>, AbstractHandler<?>> map = new HashMap<Class<?>, AbstractHandler<?>>();

	static {
		handlers.add(new BooleanHandler());
		handlers.add(new ByteHandler());
		handlers.add(new CharacterHandler());
		handlers.add(new DoubleHandler());
		handlers.add(new FloatHandler());
		handlers.add(new IntegerHandler());
		handlers.add(new LongHandler());
		handlers.add(new ShortHandler());
		handlers.add(new StringHandler());
		handlers.add(new EnumHandler());
		handlers.add(new DateHandler());
		handlers.add(new UUIDHandler());
		handlers.add(new UriHandler());
		handlers.add(new ByteArrayHandler());
	}

	@SuppressWarnings("unchecked")
	public static <T> AbstractHandler<T> get(Class<T> cls) {
		AbstractHandler<?> handler = map.get(cls);
		if (handler == null) {
			for (AbstractHandler<?> h : handlers) {
				if (h.canHandle(cls)) {
					handler = h;
					map.put(cls, handler);
					break;
				}
			}
		}
		return (AbstractHandler<T>) handler;
	}

	private TypeHandlerRegistry() {
	}

}