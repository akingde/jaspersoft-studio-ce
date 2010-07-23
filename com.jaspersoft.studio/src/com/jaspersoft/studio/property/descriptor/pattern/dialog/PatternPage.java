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
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public class PatternPage extends WizardPage implements PropertyChangeListener {

	private String value;
	private List list;
	private Map<String, APattern> map;
	private Text patternText;
	private Label sampleLabel;
	private APattern pattern;
	private Label descriptionLabel;
	private StackLayout stackLayout;
	private Composite configComposite;

	public String getValue() {
		return value;
	}

	/**
	 * Displays the help
	 */
	public void performHelp() {
		;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void setValue(String value) {
		this.value = value;
	}

	public APattern getPattern() {
		if (pattern == null)
			pattern = new CustomPattern(configComposite, value, null, null);
		return pattern;
	}

	protected PatternPage(String pageName) {
		super(pageName);
		setTitle("Format Pattern");
		setDescription("Specify format pattern, in java style.");

	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		list = new List(composite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.verticalSpan = 3;
		gd.heightHint = 200;
		gd.widthHint = 100;
		list.setLayoutData(gd);

		Group patternGroup = new Group(composite, SWT.NONE);
		patternGroup.setText(" Pattern ");
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 200;
		patternGroup.setLayoutData(gd);
		patternGroup.setLayout(new GridLayout(1, true));

		patternText = new Text(patternGroup, SWT.SINGLE | SWT.BORDER);
		patternText.setText(getValue() != null ? getValue() : "");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		patternText.setLayoutData(gd);

		sampleLabel = new Label(patternGroup, SWT.BORDER | SWT.FLAT | SWT.CENTER);
		sampleLabel.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		sampleLabel.setLayoutData(gd);

		configComposite = new Composite(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 200;
		gd.heightHint = 200;
		configComposite.setLayoutData(gd);
		stackLayout = new StackLayout();
		configComposite.setLayout(stackLayout);

		createPatterns(configComposite);
		for (String key : map.keySet())
			list.add(key);

		descriptionLabel = new Label(composite, SWT.WRAP);
		gd = new GridData(GridData.FILL);
		gd.horizontalSpan = 2;
		gd.widthHint = 300;
		gd.heightHint = 30;
		descriptionLabel.setLayoutData(gd);

		list.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] sel = list.getSelection();
				if (sel.length > 0) {
					APattern p = map.get(sel[0]);

					descriptionLabel.setText(p.getDescription() != null ? p.getDescription() : "");
					descriptionLabel.pack();

					stackLayout.topControl = p.getControl();
					configComposite.layout();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				e.getSource();
			}
		});
		patternText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				APattern pa = getPattern();
				pa.setPattern(patternText.getText());
				processFormat(pa);
			}
		});
	}

	public void createPatterns(Composite parent) {
		map = new HashMap<String, APattern>();
		map.put("Date", new DatePattern(parent));
		map.put("Time", new TimePattern(parent));
		map.put("Number", new NumericPattern(parent));
		map.put("Currency", new CurrencyPattern(parent));
		map.put("Percentage", new PercentagePattern(parent));
		map.put("Scientific", new ScientificPattern(parent));

		for (String key : map.keySet()) {
			map.get(key).getPropertyChangeSupport().addPropertyChangeListener(this);
		}
	}

	private void processFormat(APattern p) {
		try {
			if (p.getFormatter() != null && p.getSample() != null) {

				setErrorMessage(null);
				Format formatter = p.getFormatter();
				if (formatter instanceof SimpleDateFormat) {
					((SimpleDateFormat) formatter).applyPattern(p.getPattern());
				} else if (formatter instanceof DecimalFormat) {
					((DecimalFormat) formatter).applyPattern(p.getPattern());
				}
				sampleLabel.setText(formatter.format(p.getSample()));
				setValue(p.getPattern());
			}
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		APattern p = (APattern) evt.getSource();
		APattern pa = getPattern();
		pa.setPattern(p.getPattern());
		pa.setFormatter(p.getFormatter());
		pa.setSample(p.getSample());
		patternText.setText(p.getPattern());
		processFormat(p);

	}
}
