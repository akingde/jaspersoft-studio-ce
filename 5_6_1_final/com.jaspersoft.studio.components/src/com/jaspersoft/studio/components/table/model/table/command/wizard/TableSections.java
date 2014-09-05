/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.table.command.wizard;

public class TableSections {
	
	private boolean columnHeader;
	
	private boolean columnFooter;
	
	private boolean tableHeader;
	
	private boolean tableFooter;
	
	private boolean groupHeader;
	
	private boolean groupFooter;
	
	public TableSections(boolean tableHeader, boolean tableFooter, boolean columnHeader, boolean columnFooter, boolean groupHeader, boolean groupFooter){
		this.columnHeader = columnHeader;
		this.columnFooter = columnFooter;
		this.tableHeader = tableHeader;
		this.tableFooter = tableFooter;
		this.groupHeader = groupHeader;
		this.groupFooter = groupFooter;
	}
	
	public boolean isColumnHeader(){
		return columnHeader;
	}
	
	public boolean isColumnFooter(){
		return columnFooter;
	}
	
	public boolean isTableHeader(){
		return tableHeader;
	}
	
	public boolean isTableFooter(){
		return tableFooter;
	}
	
	public boolean isGroupHeader(){
		return groupHeader;
	}
	
	public boolean isGroupFooter(){
		return groupFooter;
	}

}
