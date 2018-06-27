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

package com.hycun.session.boot.demo.adapter;


import com.hycun.session.boot.demo.Session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.time.Duration;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Set;

public class HttpSessionAdapter<S extends Session> implements HttpSession {

	private S session;
	private ServletContext servletContext;

	public HttpSessionAdapter(S session,ServletContext servletContext) {
		if (session == null) {
			throw new IllegalArgumentException("session cannot be null");
		}
		if (servletContext == null) {
			throw new IllegalArgumentException("servletContext cannot be null");
		}
		this.session = session;
		this.servletContext = servletContext;
	}


	@Override
	public long getCreationTime() {
		return this.session.getCreationTime().toEpochMilli();
	}

	@Override
	public String getId() {
		return this.session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return this.session.getLastAccessedTime().toEpochMilli();
	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		this.session.setMaxInactiveInterval(Duration.ofSeconds(interval));
	}

	@Override
	public int getMaxInactiveInterval() {
		return (int)this.session.getMaxInactiveInterval().getSeconds();
	}

	@Deprecated
	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return this.session.getAttribute(name);
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(this.session.getAttributeNames());
	}

	@Override
	public String[] getValueNames() {
		Set<String> attrs = this.session.getAttributeNames();
		return attrs.toArray(new String[0]);
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.session.setAttribute(name,value);
	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name,value);
	}

	@Override
	public void removeAttribute(String name) {
		this.session.removeAttribute(name);
	}

	@Override
	public void removeValue(String name) {
		removeAttribute(name);
	}

	@Override
	public void invalidate() {

	}

	@Override
	public boolean isNew() {
		return false;
	}
}
