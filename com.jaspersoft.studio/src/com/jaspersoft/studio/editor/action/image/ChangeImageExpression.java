/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.action.image;

import java.util.Iterator;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;
import com.jaspersoft.studio.model.image.MImage;

public class ChangeImageExpression  extends SelectionAction implements IGlobalAction {

	private MImage imageModel;
	
	private final static String ID = "ActionImageChangeExpression";
	
	public ChangeImageExpression(IWorkbenchPart part) {
		super(part);
		setId(ID);
		loadImageModel();
	}
	
	private void loadImageModel(){
		if ((getSelection() != null) && (getSelection() instanceof StructuredSelection)) {
			StructuredSelection ss = (StructuredSelection) getSelection();
			for (Iterator<Object> it = ss.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof EditPart)
					obj = ((EditPart) obj).getModel();
				if (obj instanceof MImage) {
					imageModel = (MImage) obj;
				}
			}
		}
	}

	@Override
	protected boolean calculateEnabled() {
		return (imageModel != null);
	}
	
	@Override
	protected void setSelection(ISelection selection) {
		super.setSelection(selection);
		loadImageModel();
	}
	
	@Override
	public void run() {
		if (imageModel != null){
			ImageSelectionDialog d=new ImageSelectionDialog(UIUtils.getShell());
			d.configureDialog(imageModel.getJasperConfiguration());
			if(d.open()==Window.OK) {
				JRDesignExpression imageExpression = d.getImageExpression();
				if(imageExpression==null){
					// No image selected => remove property
					imageModel.setPropertyValue(JRDesignImage.PROPERTY_EXPRESSION, "");
				}
				else {
					imageModel.setPropertyValue(JRDesignImage.PROPERTY_EXPRESSION, imageExpression.getText());
				}
			}
		}
	}

}
