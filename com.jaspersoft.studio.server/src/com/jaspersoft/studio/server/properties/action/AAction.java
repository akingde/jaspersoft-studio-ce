/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.action;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.server.properties.ASection;

public abstract class AAction extends Action {
	protected Set<ASection> sections = new HashSet<ASection>();

	public AAction(String name) {
		super(name);
	}

	public void addSection(ASection section) {
		sections.add(section);
	}

	public void removeSection(ASection section) {
		sections.remove(section);
	}
}
