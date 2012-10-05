/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.property.combomenu;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.messages.Messages;

/**
 * Class that describe the content of a Combo-popup action
 * @author Orlandin Marco
 *
 */
public class ComboItem {
	
		/**
		 * The action image
		 */
		private ImageDescriptor image;
		
		/**
		 * The action textual value
		 */
		private String label;
		
		/**
		 * True if the textual value should be printed directly or must be translated using the Messages class
		 */
		private boolean printDirectly;
		
		/**
		 * Order of the item in the list
		 */
		private int order;
		
		/**
		 * Item that this action represent
		 */
		private Object item;
		
		/**
		 * Value to return when this item is selected
		 */
		private Object value;
		
		/**
		 * 
		 * @param label The action textual value
		 * @param printDirectly True if the textual value should be printed directly or must be translated using the Messages class
		 * @param order Order of the item in the list
		 * @param item Item that this action represent
		 * @param value Value to return when this item is selected
		 */
		public ComboItem(String label, boolean printDirectly, int order, Object item, Object value){
			this.label = label;
			this.printDirectly = printDirectly;
			this.order = order;
			this.item = item;
			this.value = value;
			image = null;
		}
		
		/**
		 * @param label The action textual value
		 * @param printDirectly True if the textual value should be printed directly or must be translated using the Messages class
		 * @param image The image of the item
		 * @param order Order of the item in the list
		 * @param item Item that this action represent
		 * @param value Value to return when this item is selected
		 */
		public ComboItem(String label, boolean printDirectly, ImageDescriptor image, int order, Object item, Object value){
			this(label, printDirectly, order, item, value);
			this.image = image;
		}
		
		public String getText(){
			if (printDirectly) return label;
			else return Messages.getString(label);
		}
		
		public  int getOrder(){
			return order;
		}
		
		public ImageDescriptor getImage(){
			return image;
		}
		
		public Object getItem(){
			return item;
		}
		
		public Object getValue(){
			return value;
		}
		
		public void setOrder(int newPosition){
			order = newPosition;
		}
}
