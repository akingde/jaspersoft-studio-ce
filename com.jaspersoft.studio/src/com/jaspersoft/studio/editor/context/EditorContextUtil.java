package com.jaspersoft.studio.editor.context;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.AbstractJRXMLEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.KeyValue;
import net.sf.jasperreports.eclipse.util.Misc;

public class EditorContextUtil {
	public static AEditorContext getEditorContext(IFile f, JasperReportsConfiguration jConf) {
		String ctx = null;
		try {
			QualifiedName key = new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(),
					AEditorContext.EDITOR_CONTEXT);
			if (f != null) {
				ctx = f.getPersistentProperty(key);
				IContainer c = f.getParent();
				while (c != null && Misc.isNullOrEmpty(ctx)) {
					ctx = c.getPersistentProperty(key);
					c = c.getParent();
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		AEditorContext ec = JaspersoftStudioPlugin.getExtensionManager().getEditorContext(ctx);
		ec.init(f, jConf);
		return ec;
	}

	public static Control createSwitch(Composite cmp, AbstractJRXMLEditor editor) {
		ToolBar toolBar = new ToolBar(cmp, SWT.FLAT);
		toolBar.setBackgroundMode(SWT.INHERIT_FORCE);
		ToolBarManager tbManager = new ToolBarManager(toolBar);

		tbManager.add(new ContextSwitchAction(editor, toolBar, tbManager));
		tbManager.update(true);
		toolBar.pack();
		return toolBar;
	}

	public static class ContextSwitchAction extends Action implements IMenuCreator {
		private AbstractJRXMLEditor editor;
		private ToolBar toolBar;
		private ToolBarManager tbManager;

		public ContextSwitchAction(AbstractJRXMLEditor editor, ToolBar toolBar, ToolBarManager tbManager) {
			super();
			this.toolBar = toolBar;
			this.tbManager = tbManager;
			setText("Project");
			UIUtils.getDisplay().asyncExec(() -> setToolBarText(editor.getJrContext().getEditorContext().getName()));
			setMenuCreator(this);
			this.editor = editor;
		}

		private void setToolBarText(String text) {
			setText(Misc.nvl(text, AEditorContext.NAME));
			tbManager.update(true);
			toolBar.pack();
			toolBar.getParent().update();
			toolBar.getParent().layout(true);
		}

		@Override
		public void runWithEvent(Event event) {
			Point point = ((ToolItem) event.widget).getParent().toDisplay(new Point(event.x, event.y));
			menu = getMenu(((ToolItem) event.widget).getParent());
			menu.setLocation(point.x, point.y);
			menu.setVisible(true);
		}

		private Menu menu;

		@Override
		public void dispose() {
			if (menu != null)
				menu.dispose();
		}

		@Override
		public Menu getMenu(Control parent) {
			if (menu == null) {
				menu = new Menu(parent);

				for (KeyValue<String, String> item : JaspersoftStudioPlugin.getExtensionManager().getEditorContexts()) {
					MenuItem mi = new MenuItem(menu, SWT.PUSH);
					mi.setText(item.value);
					mi.setData("name", item.key);
					mi.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String n = (String) mi.getData("name");
							editor.changeContext(n);
							setToolBarText(mi.getText());
						}
					});
				}
			}
			return menu;
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

	}
}
