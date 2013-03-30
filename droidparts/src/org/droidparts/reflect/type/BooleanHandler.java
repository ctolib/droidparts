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
package org.droidparts.reflect.type;

import org.droidparts.reflect.util.TypeHelper;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class BooleanHandler extends AbstractHandler<Boolean> {

	@Override
	public boolean canHandle(Class<?> cls) {
		return TypeHelper.isBoolean(cls);
	}

	@Override
	public String getDBColumnType() {
		return INTEGER;
	}

	@Override
	public Boolean readFromJSON(Class<?> cls, JSONObject obj, String key)
			throws JSONException {
		try {
			return obj.getBoolean(key);
		} catch (JSONException e) {
			// XXX
			return obj.getInt(key) == 1;
		}
	}

	@Override
	public void putToJSONObject(JSONObject obj, String key, Object val)
			throws JSONException {
		obj.put(key, (Boolean) val);
	}

	@Override
	public void putToContentValues(ContentValues cv, String key, Object val) {
		cv.put(key, (Boolean) val);
	}

	@Override
	public Boolean readFromCursor(Class<?> cls, Cursor cursor, int columnIndex) {
		return (cursor.getInt(columnIndex) == 1);
	}

}
