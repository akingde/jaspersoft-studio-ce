/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.ui.editor.locale;

import java.io.IOException;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.essiembre.eclipse.rbe.RBEPlugin;
import com.essiembre.eclipse.rbe.ui.UIUtils;
import com.essiembre.eclipse.rbe.ui.editor.ResourceBundleEditor;
import com.essiembre.eclipse.rbe.ui.editor.resources.ResourceManager;
import com.essiembre.eclipse.rbe.ui.widgets.LocaleSelector;

/**
 * Page for adding a new locale (new localized properties file).
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: nl_carnage $ $Revision: 1.8 $ $Date: 2007/09/11 16:24:27 $
 */
public class NewLocalePage extends Composite {

    private Font fontBoldBig = UIUtils.createFont(this, SWT.BOLD, 5);
    private Font fontBold = UIUtils.createFont(this, SWT.BOLD, 1);
    
    /**
     * Constructor.
     * @param parent parent component.
     * @param resourceManager resource manager 
     */
    public NewLocalePage(
            final Composite parent, 
            final ResourceManager resourceManager,
            final ResourceBundleEditor editor) {
        super(parent, SWT.NONE);
        
        setLayout(new GridLayout());

        Composite block = new Composite(this, SWT.NONE);
        block.setLayout(new GridLayout());
        
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        block.setLayoutData(gridData);
        
        // Title label
        Label label = new Label(block, SWT.NONE);
        label.setText(RBEPlugin.getString("editor.new.title")); //$NON-NLS-1$
        label.setFont(fontBoldBig);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        label.setLayoutData(gridData);

        // Locale selector
        final LocaleSelector localeSelector = 
                new LocaleSelector(block);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        localeSelector.setLayoutData(gridData);
        
        // Create button
        Button createButton = new Button(block, SWT.NULL);
        createButton.setText(RBEPlugin.getString(
                "editor.new.create")); //$NON-NLS-1$
        createButton.setFont(fontBold);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        createButton.setLayoutData(gridData);
        createButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                final Locale locale = localeSelector.getSelectedLocale();
                try {
                    // Create the new file
                    try {
                        //TODO add "newPropertiesFile" method to seGroup.
                        final IFile file = 
                                resourceManager.createPropertiesFile(locale);
                        Display.getDefault().asyncExec(new Runnable() {
                            public void run() {
                                editor.addResource(file, locale);
                            }
                        });
//                        
//                        final IWorkbenchPage page = PlatformUI.getWorkbench()
//                                .getActiveWorkbenchWindow().getActivePage();
//                        // Open new editor with new locale
//                        getShell().getDisplay().asyncExec(new Runnable() {
//                            public void run() {
//                                try {
//                                    IDE.openEditor(page, file, true);
//                                } catch (PartInitException e) {
//                                    UIUtils.showErrorDialog(getShell(), e,
//                                     "error.newfile.cannotCreate");//$NON-NLS-1$
//                                }
//                            }
//                        });
//                        // Close active editor (prior adding locale)
//                        page.closeEditor(page.getActiveEditor(), true);
                    } catch (NullPointerException e) {
                        UIUtils.showErrorDialog(getShell(), e, 
                                "error.newfile.cannotCreate"); //$NON-NLS-1$
                        throw e;
                    }
                } catch (CoreException e) {
                    UIUtils.showErrorDialog(getShell(), e, 
                            "error.newfile.cannotCreate"); //$NON-NLS-1$
                } catch (IOException e) {
                    UIUtils.showErrorDialog(getShell(), e, 
                            "error.newfile.cannotCreate"); //$NON-NLS-1$
                }
            }
        });
        this.layout();
    }

    /**
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        fontBold.dispose();
        fontBoldBig.dispose();
        super.dispose();
    }
}
