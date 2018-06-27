/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hycun.session.boot.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public interface Session {

	String getId();

	String changeSessionId();

	<T> T getAttribute(String attributeName);

	default <T> T getRequiredAttribute(String name) {
		T result = getAttribute(name);
		if (result == null) {
			throw new IllegalArgumentException(
					"Required attribute '" + name + "' is missing.");
		}
		return result;
	}

	default <T> T getAttributeOrDefault(String name, T defaultValue) {
		T result = getAttribute(name);
		return result == null ? defaultValue : result;
	}

	Set<String> getAttributeNames();

	void setAttribute(String attributeName, Object attributeValue);

	void removeAttribute(String attributeName);

	Instant getCreationTime();

	void setLastAccessedTime(Instant lastAccessedTime);

	Instant getLastAccessedTime();

	void setMaxInactiveInterval(Duration interval);

	Duration getMaxInactiveInterval();

	boolean isExpired();

}
