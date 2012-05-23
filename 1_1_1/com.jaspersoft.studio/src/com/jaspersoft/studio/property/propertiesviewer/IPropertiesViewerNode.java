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

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.FilteredTree;

/**
 * This interface allows to define a page for a complex properties viewer organized
 * like the Preferences dialog of Eclipse with a tree on the left and a set of
 * stacked pages on the right.
 * 
 * @author mrabbi
 *
 * @see {@link TreePropertiesViewerPanel}
 */
public interface IPropertiesViewerNode {
	
	/**
	 * Returns the unique ID of this item node.
	 * 
	 * @return the element identifier
	 */
	public String getId();
	
	/**
	 * Returns the human readable name of this set of properties.
	 * 
	 * @return a human readable string
	 */
	public String getName();
	
	/**
	 * Returns the category ID for this node.
	 * This attribute is used to build the hierarchy of the nodes.
	 * It represents the ID of the parent node. 
	 * It is <code>null</code> in case the item is a root node.
	 * 
	 * @return the category identifier
	 */
	public String getCategory();
	
	/**
	 * Returns the actual control related to the node item.
	 * It will be shown in the main are of the panel/dialog. 
	 * 
	 * @return the current node control
	 */
	public Control getControl();
	
	/**
	 * Creates the control to show when the item is selected in the tree viewer.
	 * This method should be invoked only once, otherwise it will raise an exception.
	 *  
	 * @param parent the parent composite
	 * @throws IllegalStateException if the control has already been created
	 * @see #getControl()
	 */
	public void createControl(Composite parent) throws IllegalStateException;
	
	/**
	 * Returns a collection of keywords associated to the current node item.
	 * The search operation performed using the {@link FilteredTree} can benefit
	 * from this method.
	 * <p>
	 * If not needed it MUST return an empty collection.
	 * 
	 * @return the collection of keywords associated to the item node
	 */
    public Collection<String> getNodeKeywords();
    
    /**
     * Requests the node update.
     * A common behavior can be the control update depending on the internal status.
     */
    public void update();
}
