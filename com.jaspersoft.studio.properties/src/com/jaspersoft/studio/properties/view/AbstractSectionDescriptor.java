/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.properties.internal.InputType;
import com.jaspersoft.studio.properties.internal.TabbedPropertyRegistryClassSectionFilter;

/**
 * An abstract implementation of a section descriptor for the tabbed property
 * view.
 * 
 * @author Anthony Hunter
 * @since 3.4
 */
public abstract class AbstractSectionDescriptor implements ISectionDescriptor {

	private TabbedPropertyRegistryClassSectionFilter classFilter;

	/**
	 * Constructor for AbstractSectionDescriptor.
	 */
	public AbstractSectionDescriptor() {
		super();
		classFilter = new TabbedPropertyRegistryClassSectionFilter(null);
	}

	/**
	 * Constructor for AbstractSectionDescriptor.
	 * 
	 * @param typeMapper
	 *            the type mapper for the section.
	 */
	public AbstractSectionDescriptor(ITypeMapper typeMapper) {
		super();
		classFilter = new TabbedPropertyRegistryClassSectionFilter(typeMapper);
	}

	/*
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISectionDescriptor#appliesTo(org
	 * .eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		return classFilter.appliesToSelection(this, selection);
	}

	/*
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISectionDescriptor#getAfterSection
	 * ()
	 */
	public String getAfterSection() {
		return TOP;
	}

	/*
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISectionDescriptor#getEnablesFor()
	 */
	public int getEnablesFor() {
		return ENABLES_FOR_ANY;
	}

	/*
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISectionDescriptor#getFilter()
	 */
	public IFilter getFilter() {
		return null;
	}

	public List<InputType> getInputTypes() {
		return new ArrayList<InputType>();
	}
}
