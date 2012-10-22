/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.propertiesviewer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Generic node that should be extended by all the other kind of nodes.
 * 
 * @author mrabbi
 *
 */
public class PropertiesViewerNode implements IPropertiesViewerNode {

	protected Control control;
	
	// Attributes
	private String id;
	private String category;
	private String name;
	private Collection<String> keywords;

	public PropertiesViewerNode(String id, String name){
		this(id,name,null,null);
	}
	
	public PropertiesViewerNode(String id, String name, String category){
		this(id,name,category,null);
	}
	
	public PropertiesViewerNode(String id, String name, Collection<String> keywords){
		this(id,name,null,keywords);
	}
	
	public PropertiesViewerNode(String id, String name, String category, Collection<String> keywords){
		// Sanity checks - Must have ID and NAME
		Assert.isNotNull(id);
		Assert.isNotNull(name);
		
		this.id=id;
		this.name=name;
		this.category=category;
		this.keywords=keywords;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public Control getControl() {
		return control;
	}

	public void createControl(Composite parent) {
		checkControl();
		control=new Composite(parent,SWT.NONE);
	}
	
	public Collection<String> getNodeKeywords() {
		if (keywords==null){
			keywords=new ArrayList<String>();
		}
		return keywords;
	}
	
	public void update(){
		// DO NOTHING - should be overridden by subclasses
		// that want to perform custom update operations on
		// the main control.
	}
	
	/**
	 * Verifies if the control has already been created.
	 * If so, it throws an {@link IllegalStateException}.
	 */
	protected void checkControl(){
		if(control!=null){
			throw new IllegalStateException(
					MessageFormat.format("The control associated to the current node with name '{0}' and id '{1}' was already created.", new Object[]{name,id}));
		}
	}

	@Override
	public String getHelpContextID() {
		return null;
	}

}
