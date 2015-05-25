/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.debug;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.scriptlets.DefaultScriptletFactory;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactoryContext;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * @author Veaceslav Chicu (schicu@tibco.com)
 */
public final class TraceGovernorFactory implements ScriptletFactory {

	private static final TraceGovernorFactory INSTANCE = new TraceGovernorFactory();

	private TraceGovernorFactory() {
	}

	/**
	 * 
	 */
	public static TraceGovernorFactory getInstance() {
		return INSTANCE;
	}

	/**
	 *
	 */
	public List<JRAbstractScriptlet> getScriplets(ScriptletFactoryContext context) throws JRException {
		List<JRAbstractScriptlet> scriptlets = new ArrayList<JRAbstractScriptlet>();

		scriptlets.add(new TraceGovernor(context));

		return scriptlets;
	}

	/**
	 *
	 */
	protected JRAbstractScriptlet getScriptlet(String scriptletClassName) throws JRException {
		JRAbstractScriptlet scriptlet = null;

		try {
			Class<?> scriptletClass = JRClassLoader.loadClassForName(scriptletClassName);
			scriptlet = (JRAbstractScriptlet) scriptletClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new JRException(DefaultScriptletFactory.EXCEPTION_MESSAGE_KEY_CLASS_LOADING_ERROR,
					new Object[] { scriptletClassName }, e);
		} catch (Exception e) {
			throw new JRException(DefaultScriptletFactory.EXCEPTION_MESSAGE_KEY_INSTANCE_LOADING_ERROR,
					new Object[] { scriptletClassName }, e);
		}

		return scriptlet;
	}

}
