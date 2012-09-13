/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview;

import java.io.InputStream;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IToolBarManager;
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

import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.toolbar.ATopToolBarManager;
import com.jaspersoft.studio.editor.preview.toolbar.TopToolBarManagerJRPrint;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.ViewsFactory;
import com.jaspersoft.studio.editor.preview.view.control.VSimpleErrorPreview;
import com.jaspersoft.studio.editor.preview.view.report.IJRPrintable;
import com.jaspersoft.studio.utils.Console;

public class PreviewJRPrint extends ABasicEditor {
	private boolean hideParameters = true;

	public void setHideParameters(boolean hideParameters) {
		this.hideParameters = hideParameters;
	}

	public boolean isHideParameters() {
		return hideParameters;
	}

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
		loadJRPrint(getEditorInput());
		// getSite().getPage().addPartListener(new IPartListener2() {
		//
		// public void partVisible(IWorkbenchPartReference partRef) {
		// if (console != null) {
		// IEditorPart ceditor = getSite().getPage().getActiveEditor();
		// if (partRef.getPart(false).getClass().equals(ceditor.getClass())) {
		// if (ceditor instanceof JrxmlEditor && ((JrxmlEditor) ceditor).getActivePage() == JrxmlEditor.PAGE_PREVIEW) {
		// console.showConsole();
		// }
		// if (ceditor instanceof PreviewJRPrint)
		// console.showConsole();
		// }
		// }
		// }
		//
		// public void partOpened(IWorkbenchPartReference partRef) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// public void partInputChanged(IWorkbenchPartReference partRef) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// public void partHidden(IWorkbenchPartReference partRef) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// public void partDeactivated(IWorkbenchPartReference partRef) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// public void partClosed(IWorkbenchPartReference partRef) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// // if (console != null)
		// // console.showConsole();
		// }
		//
		// public void partActivated(IWorkbenchPartReference partRef) {
		// // if (console != null)
		// // console.showConsole();
		// }
		// });
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
			Statistics stats = new Statistics();
			if (file.getFileExtension().equals(".jrpxml")) {
				setJasperPrint(stats, JRPrintXmlLoader.load(in));
			} else {
				Object obj = JRLoader.loadObject(in);
				if (obj instanceof JasperPrint)
					setJasperPrint(stats, (JasperPrint) obj);
			}
		} catch (Exception e) {
			throw new PartInitException("Invalid Input", e);
		}
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(final Statistics stats, JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				if (getDefaultViewer() instanceof IJRPrintable) {
					JasperPrint jrprint = getJasperPrint();
					if (jrprint != null) {
						getRightContainer().switchView(stats, getDefaultViewerKey());
					} else {
						// errorPreview.setclear("Document is empty");
						getRightContainer().switchView(stats, errorPreview);
					}
				}
			}
		});
	}

	private String currentViewer;

	public String getDefaultViewerKey() {
		if (currentViewer == null)
			currentViewer = ViewsFactory.VIEWER_JAVA;
		return currentViewer;
	}

	public APreview getDefaultViewer() {
		return getRightContainer().getViewer(getDefaultViewerKey());
	}

	private MultiPageContainer rightContainer;

	public MultiPageContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new MultiPageContainer() {
				public void switchView(Statistics stats, String key) {
					currentViewer = key;
					APreview view = pmap.get(key);
					if (!switchRightView(view, stats, this))
						return;
					super.switchView(stats, key);
				}

				@Override
				public void switchView(String key) {
					currentViewer = key;
					super.switchView(key);
				}

				@Override
				public void switchView(Statistics stats, APreview view) {
					if (!switchRightView(view, stats, this))
						return;
					super.switchView(stats, view);
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

	protected boolean switchRightView(APreview view, Statistics stats, MultiPageContainer container) {
		if (view instanceof IJRPrintable) {
			try {
				((IJRPrintable) view).setJRPRint(stats, jasperPrint);
				console.setStatistics(stats);
			} catch (Exception e) {
				container.switchView(stats, errorPreview);

				console.addError(e);
				return false;
			}
		}
		return true;
	}

	@Override
	public void dispose() {
		super.dispose();
		getRightContainer().dispose();
	}

	protected TopToolBarManagerJRPrint topToolBarManager;

	protected TopToolBarManagerJRPrint getTopToolBarManager(Composite container) {
		if (topToolBarManager == null)
			topToolBarManager = new TopToolBarManagerJRPrint(this, container);
		return topToolBarManager;
	}

	protected ATopToolBarManager topToolBarManager1;

	protected ATopToolBarManager getTopToolBarManager1(Composite container) {
		if (topToolBarManager1 == null)
			topToolBarManager1 = new ATopToolBarManager(this, container) {

				@Override
				protected void fillToolbar(IToolBarManager tbManager) {

				}
			};
		return topToolBarManager1;
	}

	private VSimpleErrorPreview errorPreview;

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		getTopToolBarManager1(container);
		getTopToolBarManager(container);

		Composite rcmp = createRight(container);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		rcmp.setLayoutData(gd);
	}

	protected Composite createRight(Composite parent) {
		// CSashForm rightSash = new CSashForm(parent, SWT.VERTICAL);

		rightComposite = new Composite(parent, SWT.BORDER);

		StackLayout stacklayoutView = new StackLayout();
		rightComposite.setLayout(stacklayoutView);

		getRightContainer().populate(rightComposite, ViewsFactory.createPreviews(rightComposite, jrContext));

		errorPreview = new VSimpleErrorPreview(rightComposite, jrContext);

		return rightComposite;
	}

	@Override
	public void setFocus() {
		if (topToolBarManager1 != null)
			topToolBarManager1.setFocus();
	}

	private boolean notRunning = true;

	public void setNotRunning(boolean norun) {
		this.notRunning = norun;

		if (topToolBarManager1 != null) {
			topToolBarManager1.refreshToolbar();
			if (norun)
				topToolBarManager1.setEnabled(true);
		}

		if (topToolBarManager != null) {
			topToolBarManager.refreshToolbar();
			if (norun)
				topToolBarManager.setEnabled(true);
		}
	}

	public boolean isNotRunning() {
		return notRunning;
	}

	private Console console;
	protected Composite rightComposite;

	public Console getConsole() {
		if (console == null) {
			console = Console.showConsole(getEditorInput().getName());
			// console.addErrorPreview(errorPreview);
		}
		return console;
	}

}