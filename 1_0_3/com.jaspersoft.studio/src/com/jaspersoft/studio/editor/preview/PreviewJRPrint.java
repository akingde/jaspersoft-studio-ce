/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview;

import java.io.InputStream;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.editor.preview.toolbar.TopToolBarManagerJRPrint;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.ViewsFactory;
import com.jaspersoft.studio.editor.preview.view.control.VErrorPreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;

public class PreviewJRPrint extends ABasicEditor {

	private JasperPrint jasperPrint;

	public PreviewJRPrint() {
		super(true);
	}

	public PreviewJRPrint(boolean listenresource) {
		super(listenresource);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		loadJRPrint(input);
	}

	protected void loadJRPrint(IEditorInput input) throws PartInitException {
		InputStream in = null;
		try {
			IFile file = null;
			if (input instanceof IFileEditorInput) {
				file = ((IFileEditorInput) input).getFile();
				in = file.getContents();
			} else {
				throw new PartInitException("Invalid Input: Must be IFileEditorInput or FileStoreEditorInput"); //$NON-NLS-1$
			}
			if (file.getFileExtension().equals(".jrpxml")) {
				setJasperPrint(JRPrintXmlLoader.load(in));
			} else {
				Object obj = JRLoader.loadObject(in);
				if (obj instanceof JasperPrint)
					setJasperPrint((JasperPrint) obj);
			}
		} catch (Exception e) {
			throw new PartInitException("Invalid Input", e);
		}
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				JasperPrint jrprint = getJasperPrint();
				if (jrprint != null) {
					getRightContainer().switchView(getDefaultViewer());
				} else {
					errorPreview.setMessage("Document is empty");
					getRightContainer().switchView(errorPreview);
				}
			}
		});
	}

	private String currentViewer;

	public String getDefaultViewer() {
		if (currentViewer == null)
			currentViewer = ViewsFactory.VIEWER_JAVA;
		return currentViewer;
	}

	private MultiPageContainer rightContainer;

	public MultiPageContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new MultiPageContainer() {
				public void switchView(String key) {
					APreview aPreview = pmap.get(key);
					if (aPreview instanceof IJRPrintable) {
						try {
							((IJRPrintable) aPreview).setJRPRint(jasperPrint);
						} catch (Exception e) {
							switchView(errorPreview);
							errorPreview.setError(null, e);
							return;
						}
						currentViewer = key;
					}
					super.switchView(key);

				}

				@Override
				public void switchView(APreview view) {
					super.switchView(view);
					topToolBarManager.contributeItems(view);
				}

				@Override
				public void dispose() {
					super.dispose();
					topToolBarManager.removeAll();
				}
			};
		}
		return rightContainer;
	}

	@Override
	public void dispose() {
		super.dispose();
		getRightContainer().dispose();
	}

	protected TopToolBarManagerJRPrint topToolBarManager;

	@Override
	public void createPartControl(Composite parent) {
		createRight(parent);
	}

	protected TopToolBarManagerJRPrint getTopToolBarManager(Composite container) {
		if (topToolBarManager == null)
			topToolBarManager = new TopToolBarManagerJRPrint(this, container);
		return topToolBarManager;
	}

	private VErrorPreview errorPreview;

	protected void createRight(Composite parent) {
		Composite container = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout(layout);

		getTopToolBarManager(container);

		Composite rightComposite = new Composite(container, SWT.NONE);
		rightComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		StackLayout stacklayoutView = new StackLayout();
		rightComposite.setLayout(stacklayoutView);

		getRightContainer().populate(rightComposite, ViewsFactory.createPreviews(rightComposite, ph));

		errorPreview = new VErrorPreview(rightComposite, ph);
	}

	@Override
	public void setFocus() {
		if (topToolBarManager != null)
			topToolBarManager.setFocus();
	}

}