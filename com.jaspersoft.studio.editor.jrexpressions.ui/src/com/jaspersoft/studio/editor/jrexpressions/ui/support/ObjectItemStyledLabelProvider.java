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
package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import net.sf.jasperreports.expressions.annotations.JRExprFunctionBean;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;

import com.jaspersoft.studio.editor.expression.ExpObject;
import com.jaspersoft.studio.editor.jrexpressions.ui.support.ObjectCategoryItem.Category;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * Styled label provider for the a tree containing object items.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ObjectItemStyledLabelProvider extends StyledCellLabelProvider {
	
	/** Styler for the parameters */
	public static final Styler PARAMETER_STYLER;
	/** Styler for the variables */
	public static final Styler VARIABLE_STYLER;
	/** Styler for the fields */
	public static final Styler FIELD_STYLER;
	/** Styler for the class types */
	public static final Styler CLASSTYPE_STYLER;
	
	private Category currentCategory;
	
	static {
		// Styling info
		JFaceResources.getColorRegistry().put("PARAMETER_RED_COLOR", new RGB(190, 39, 39));
		JFaceResources.getColorRegistry().put("VARIABLE_BLUE_COLOR", new RGB(41, 41, 255));
		JFaceResources.getColorRegistry().put("FIELD_GREEN_COLOR", new RGB(39, 144, 39));
		JFaceResources.getColorRegistry().put("GRAY_CLASS_TYPE", new RGB(143, 143, 143));
		PARAMETER_STYLER=StyledString.createColorRegistryStyler("PARAMETER_RED_COLOR", null);
		VARIABLE_STYLER=StyledString.createColorRegistryStyler("VARIABLE_BLUE_COLOR", null);
		FIELD_STYLER=StyledString.createColorRegistryStyler("FIELD_GREEN_COLOR", null);
		CLASSTYPE_STYLER=StyledString.createColorRegistryStyler("GRAY_CLASS_TYPE", null);
	}
	
	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		StyledString text = new StyledString();
		if(element instanceof ExpObject){
			ExpObject obj=(ExpObject)element;
			String name = obj.getName();					
			String classType = obj.getClassType();
			if(obj.getType()==ExpObject.TYPE_PARAM){
				text.append(name);
				text.append(" Parameter ", PARAMETER_STYLER);						
				text.append(classType.substring(classType.lastIndexOf(".")+1), CLASSTYPE_STYLER);
			}
			else if (obj.getType()==ExpObject.TYPE_VARIABLE){
				text.append(name);
				text.append(" Variable ", VARIABLE_STYLER );						
				text.append(classType.substring(classType.lastIndexOf(".")+1), CLASSTYPE_STYLER);
			}
			else if (obj.getType()==ExpObject.TYPE_FIELD){
				text.append(name);
				text.append(" Field ", FIELD_STYLER);						
				text.append(classType.substring(classType.lastIndexOf(".")+1), CLASSTYPE_STYLER);					
			}
		}
		else if (element instanceof JRExprFunctionBean){
			final Font boldFont = ResourceManager.getBoldFont(getViewer().getControl().getFont());				
			JRExprFunctionBean funct=(JRExprFunctionBean)element;
			text.append(funct.getName(), new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.font=boldFont;
				}
			});
			text.append(" returns ");
			String canonicalName = funct.getReturnType().getCanonicalName();
			text.append(canonicalName, CLASSTYPE_STYLER);
		}
		else if (element instanceof String){
			if(Category.RECENT_EXPRESSIONS.equals(currentCategory) || 
					Category.USER_DEFINED_EXPRESSIONS.equals(currentCategory)){
				if(((String) element).length()>80){
					text.append(element.toString().substring(0, Math.min(80, ((String) element).length())));
					text.append("...");
				}
				else{
					text.append(element.toString());
				}
			}
			else {
				final Font boldFont = ResourceManager.getBoldFont(getViewer().getControl().getFont());
				String methodFirm = element.toString();
				int lParanIdx=methodFirm.indexOf('(');
				int rParanIdx=methodFirm.indexOf(')');
				text.append(methodFirm.substring(0,lParanIdx), new Styler() {
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.font=boldFont;
					}
				});
				text.append(methodFirm.substring(lParanIdx,rParanIdx+1));
				text.append(methodFirm.substring(rParanIdx+1), CLASSTYPE_STYLER);
			}
		}
		
		cell.setText(text.toString());
		cell.setStyleRanges(text.getStyleRanges());
		super.update(cell);
	}

	public void setCategory(Category currentCategory) {
		this.currentCategory=currentCategory;
	}
	
}
