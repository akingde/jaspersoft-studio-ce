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
package com.jaspersoft.studio.custom.adapter;
public class Pair{
		
		private String packageName;
		
		private String className;
		
		public Pair(String packageName, String className){
			this.packageName = packageName;
			this.className = className;
		}
		
		public String getPackageName() {
			return packageName;
		}
		
		public String getClassName() {
			return className;
		}
		
		@Override
		public String toString() {
			return packageName + "." + className;
		}
	}