package com.jaspersoft.studio.components.commonstyles;

import java.util.List;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;

import com.jaspersoft.studio.editor.style.TemplateStyle;


public class ImportExportDialog extends FormDialog {

	private Table table;
	
	private Composite body;
	
	private CommonViewProvider styleProvider;
	
	private Text pathText;
	
	private class TemplateStyleLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
		public void createColumns(TableViewer viewer) {
			String[] titles = {"", "Style Preview","Style Name"};
			for (int i = 0; i < titles.length; i++) {
				TableColumn column = new TableViewerColumn(viewer, SWT.NONE).getColumn();
				column.setText(titles[i]);
				column.setResizable(i>0);
			}
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 1 && element instanceof TemplateStyle){
				TemplateStyle style = (TemplateStyle) element;
				return styleProvider.generatePreviewFigure(style);
			} 
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (columnIndex == 2 && element instanceof TemplateStyle){
				TemplateStyle style = (TemplateStyle) element;
				return style.getDescription();
			}
			return null;
		}	
		
	}
	
	private class StyleContentProvider implements IStructuredContentProvider {

		public StyleContentProvider() {
			super();
		}

		public void dispose() {}

		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof CommonViewProvider){
				List<TemplateStyle> availableStyles = ((CommonViewProvider)inputElement).getStylesList();
				return availableStyles.toArray();
			} 
			return new Object[0];
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}


	}
	
	public ImportExportDialog(Shell shell, CommonViewProvider styleProvider) {
		super(shell);
		this.styleProvider = styleProvider;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("bubba");
		setShellStyle(getShellStyle() | SWT.MIN | SWT.MAX | SWT.RESIZE);
	}
	
	private String selectPathDialog(Shell shell){
		FileDialog dialog = new FileDialog (shell, SWT.SAVE);
		String [] filterNames = new String [] {"XML files"};
		String [] filterExtensions = new String [] {"*.xml;"};
		String filterPath = "/";
		String platform = SWT.getPlatform();
		if (platform.equals("win32") || platform.equals("wpf")) {
			filterNames = new String [] {"XML files"};
			filterExtensions = new String [] {"*.xml;"};
			filterPath = "c:\\";
		}
		dialog.setFilterNames (filterNames);
		dialog.setFilterExtensions (filterExtensions);
		dialog.setFilterPath (filterPath);
		dialog.setFileName ("MyTemplates");
		return dialog.open();
	}
	
	@Override
	protected void createFormContent(final IManagedForm mform) {
		body = mform.getForm().getBody();
		body.setLayout(new GridLayout(1, true));
		body.setBackground(body.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		table = new Table(body, SWT.CHECK | SWT.BORDER | SWT.HIDE_SELECTION | SWT.NO_FOCUS );
		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 400;
		tableData.widthHint = 600;
		table.setLayoutData(tableData);
		table.setHeaderVisible (true);
		
		CheckboxTableViewer entriesViewer = new CheckboxTableViewer(table);
		TemplateStyleLabelProvider labelProvider = new TemplateStyleLabelProvider();
		labelProvider.createColumns(entriesViewer);
		entriesViewer.setLabelProvider(labelProvider);
		entriesViewer.setContentProvider(new StyleContentProvider());
		
		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnPixelData(24,false));
		tableLayout.addColumnData(new ColumnWeightData(20, 50, true));
		tableLayout.addColumnData(new ColumnWeightData(50, 50, true));
		table.setLayout(tableLayout);
		entriesViewer.setInput(styleProvider);


		Composite pathComposite = new Composite(body, SWT.NONE);
		pathComposite.setLayout(new GridLayout(3,false));
		GridData pathData = new GridData();
		pathData.grabExcessHorizontalSpace = true;
		pathData.horizontalAlignment = SWT.FILL;
		pathComposite.setLayoutData(pathData);
		Label pathLabel = new Label(pathComposite, SWT.NONE);
		pathLabel.setText("Destination file:");
		pathText = new Text(pathComposite, SWT.BORDER);
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.FILL;
		pathText.setLayoutData(textData);
		Button browseButton = new Button(pathComposite, SWT.NONE);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = selectPathDialog(getShell());
				if (path!=null) pathText.setText(path);
			}
		});
	}

	
}
