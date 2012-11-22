/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
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
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.scoping;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRClassGenerator;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

/**
 * Custom local scope provider that provides implicit imports related to JasperReports.
 * <p>
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see JRClassGenerator
 *
 */
public class JRImportedNamespaceAwareLocalScopeProvider extends	ImportedNamespaceAwareLocalScopeProvider {
	
	// List of JR imports
	private List<ImportNormalizer> jrImports;
	
	@Override
	  protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		if(jrImports==null){
		    jrImports=new ArrayList<ImportNormalizer>();
		    // import net.sf.jasperreports.engine.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("net","sf","jasperreports","engine"),
		  	      true, ignoreCase));
		    // import net.sf.jasperreports.engine.fill.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("net","sf","jasperreports","engine","fill"),
		  	      true, ignoreCase));
		    // import java.lang.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("java","lang"),
		  	      true, ignoreCase));
		    // import java.util.*
		    jrImports.add(new ImportNormalizer(
		      QualifiedName.create("java","util"),
		      true, ignoreCase));
		    // import java.math.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("java","math"),
		  	      true, ignoreCase));
		    // import java.text.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("java","text"),
		  	      true, ignoreCase));
		    // import java.io.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("java","io"),
		  	      true, ignoreCase));
		    // import java.net.*
		    jrImports.add(new ImportNormalizer(
		  	      QualifiedName.create("java","net"),
		  	      true, ignoreCase));
		}
	    return jrImports;
	  }
}
