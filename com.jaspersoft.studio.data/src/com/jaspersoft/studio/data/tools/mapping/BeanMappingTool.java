package com.jaspersoft.studio.data.tools.mapping;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.apache.commons.beanutils.PropertyUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.data.IMappingTool;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClassType;

public class BeanMappingTool implements IMappingTool {
	private JRDesignDataset dataset;
	private Composite control;
	private org.eclipse.swt.widgets.List methods;
	private PropertyDescriptor[] methodsarray;

	public String getName() {
		return Messages.BeanMappingTool_toolname;
	}

	public Control getControl() {
		return control;
	}

	public Control createControl(Composite parent) {
		control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(3, false));

		Label label = new Label(control, SWT.NONE);
		label.setText(Messages.BeanMappingTool_labeltitle);

		final ClassType classType = new ClassType(control);
		classType.addListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				try {
					errMsg.setText(""); //$NON-NLS-1$
					Class<?> clazz = Class.forName(classType.getClassType());

					methodsarray = PropertyUtils.getPropertyDescriptors(clazz);

					String[] strm = new String[methodsarray.length];
					for (int i = 0; i < methodsarray.length; i++) {
						strm[i] = methodsarray[i].getName();
					}

					methods.setItems(strm);
				} catch (ClassNotFoundException e1) {
					errMsg.setText(Messages.BeanMappingTool_errormessage
							+ e1.getMessage());
				}
			}
		});

		methods = new org.eclipse.swt.widgets.List(control, SWT.MULTI
				| SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL);
		methods.setItems(new String[] {});
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		methods.setLayoutData(gd);

		Button gfbtn = new Button(control, SWT.PUSH);
		gfbtn.setText(Messages.BeanMappingTool_selectfieldstitle);
		gfbtn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int[] items = methods.getSelectionIndices();
				if (methodsarray != null && items != null) {
					// hmm, aici ar trebui sa am eu un hook ceva
					List<JRDesignField> flist = new ArrayList<JRDesignField>();
					for (int i = 0; i < items.length; i++) {
						JRDesignField f = new JRDesignField();
						f.setName(methodsarray[items[i]].getName());
						Class<?> propertyType = methodsarray[items[i]]
								.getPropertyType();
						if (propertyType != null)
							f.setValueClass(propertyType);
						else
							f.setValueClass(Object.class);
						String description = methodsarray[items[i]]
								.getShortDescription();
						if (description != null)
							f.setDescription(description);
						flist.add(f);
					}
					fsetter.setFields(flist);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		errMsg = new Label(control, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		errMsg.setLayoutData(gd);

		return control;
	}

	public void setFields(List<JRDesignField> fields) {

	}

	public void setJRDataset(JRDesignDataset dataset) {
		this.dataset = dataset;
	}

	public JRDesignDataset getJRDataset() {
		return dataset;
	}

	private IFieldSetter fsetter;
	private Label errMsg;

	public void setFields(IFieldSetter fsetter) {
		this.fsetter = fsetter;
	}

}
