package com.jaspersoft.studio.data.tools.mapping;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.IMappingTool;

public class BeanMappingTool implements IMappingTool {
	private JRDesignDataset dataset;
	private Composite control;

	@Override
	public String getName() {
		return "Java Bean";
	}

	@Override
	public Control getControl() {
		return control;
	}

	@Override
	public Control createControl(Composite parent) {
		control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(2, false));

		Label label = new Label(control, SWT.NONE);
		label.setText("Java Bean Class Name");
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		Text classname = new Text(control, SWT.BORDER);
		classname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btn = new Button(control, SWT.PUSH);
		btn.setText("...");
		btn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		org.eclipse.swt.widgets.List methods = new org.eclipse.swt.widgets.List(
				control, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
		methods.setItems(new String[] { "test" });
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		methods.setLayoutData(gd);

		Button gfbtn = new Button(control, SWT.PUSH);
		gfbtn.setText("Get Selected Fields");
		gfbtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return control;
	}

	@Override
	public List<JRDesignField> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJRDataset(JRDesignDataset dataset) {
		this.dataset = dataset;
	}

	@Override
	public JRDesignDataset getJRDataset() {
		return dataset;
	}

}
