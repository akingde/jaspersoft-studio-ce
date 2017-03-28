/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Widget to provide an expression for an image element, it provide an additional button
 * to open the image selection dialog
 */
public class SPImageExpression extends SPExpression {
	
	private class WImageExpression extends WTextExpression{

		private Button browseImageButton;
		
		public WImageExpression(Composite parent, int style, int linesNum) {
			super(parent, style, linesNum);
		}
		
		@Override
		protected void createButtonControl() {
			browseImageButton = new Button(this, SWT.FLAT);
			browseImageButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("/icons/resources/folder-16.gif"));
			browseImageButton.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					ImageSelectionDialog isd = new ImageSelectionDialog(UIUtils.getShell());
					isd.configureDialog(section.getSelectedElement().getJasperConfiguration());
					if (isd.open() == Dialog.OK) {
						JRDesignExpression exp = isd.getFileExpression();
						textExpression.setText(exp.getText());
					}
				}
				
			});
			super.createButtonControl();
		}
		
		@Override
		protected void configureWidgetsLayoutData(int showMode) {
			int heightHint = UIUtil.getCharHeight(textExpression);
			if (showMode == LABEL_ON_LEFT) {
				// Configuration with label on left
				FormData fd_label = new FormData();
				fd_label.top = new FormAttachment(0, 3);
				fd_label.left = new FormAttachment(0);
				label.setLayoutData(fd_label);

				FormData fd_btnEditExpression = new FormData();
				fd_btnEditExpression.top = new FormAttachment(0);
				fd_btnEditExpression.right = new FormAttachment(100);
				btnEditExpression.setLayoutData(fd_btnEditExpression);

				FormData fd_textExpression = new FormData();
				fd_textExpression.bottom = new FormAttachment(100);
				fd_textExpression.top = new FormAttachment(label, -3, SWT.TOP);
				fd_textExpression.right = new FormAttachment(btnEditExpression, -5, SWT.LEFT);
				fd_textExpression.left = new FormAttachment(label, 5);
				fd_textExpression.height = heightHint;
				textExpression.setLayoutData(fd_textExpression);
			} else if (showMode == LABEL_ON_TOP) {
				// Configuration with label on top
				FormData fd_label = new FormData();
				fd_label.left = new FormAttachment(0);
				fd_label.right = new FormAttachment(100);
				fd_label.top = new FormAttachment(0);
				label.setLayoutData(fd_label);

				FormData fd_btnEditExpression = new FormData();
				fd_btnEditExpression.top = new FormAttachment(label, 5);
				fd_btnEditExpression.right = new FormAttachment(label, 0, SWT.RIGHT);
				btnEditExpression.setLayoutData(fd_btnEditExpression);

				FormData fd_textExpression = new FormData();
				fd_textExpression.top = new FormAttachment(label, 5);
				fd_textExpression.right = new FormAttachment(btnEditExpression, -5);
				fd_textExpression.bottom = new FormAttachment(100);
				fd_textExpression.left = new FormAttachment(0);
				fd_textExpression.height = heightHint;
				textExpression.setLayoutData(fd_textExpression);
			} else {
				// Standard configuration
				final FormData fd_textExpression = new FormData();
				fd_textExpression.bottom = new FormAttachment(100);
				fd_textExpression.top = new FormAttachment(0);
				fd_textExpression.left = new FormAttachment(0);
				fd_textExpression.right = new FormAttachment(browseImageButton, -5);
				fd_textExpression.height = heightHint;
				textExpression.setLayoutData(fd_textExpression);

				FormData fd_btnBrowse = new FormData();
				fd_btnBrowse.right = new FormAttachment(100);
				fd_btnBrowse.top = new FormAttachment(0);
				fd_btnBrowse.right = new FormAttachment(btnEditExpression, -5);
				browseImageButton.setLayoutData(fd_btnBrowse);

				FormData fd_btnEditExpression = new FormData();
				fd_btnEditExpression.right = new FormAttachment(100);
				fd_btnEditExpression.top = new FormAttachment(0);
				btnEditExpression.setLayoutData(fd_btnEditExpression);
				fd_textExpression.width = textExpression.getBounds().width /2;
				addControlListener(new ControlAdapter() {
					@Override
					public void controlResized(ControlEvent e) {
						fd_textExpression.width = textExpression.getBounds().width /2;
						layout();
					}
				});
			}
		}
		
	}
	
	
	public SPImageExpression(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	@Override
	protected void createComponent(Composite parent) {
		expr = new WImageExpression(parent, SWT.NONE, 1);
		expr.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr.getExpression();
				section.changeProperty(pDescriptor.getId(), exp != null ? exp.clone() : null);
			}
		});
		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			expr.setLayoutData(gd);
		}
		expr.getTextControl().addFocusListener(focusListener);
		autocomplete = new CustomAutoCompleteField(expr.getTextControl(), new TextContentAdapter(),
				InputHistoryCache.get(getHistoryKey()));
	}

}
