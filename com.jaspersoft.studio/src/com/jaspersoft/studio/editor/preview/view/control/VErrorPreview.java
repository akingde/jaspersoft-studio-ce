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
package com.jaspersoft.studio.editor.preview.view.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.Console;
import com.jaspersoft.studio.utils.ErrorUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class VErrorPreview extends APreview {

	private static final String NL = System.getProperty("line.separator");

	public VErrorPreview(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	private Label compilationTime;
	private Label fillingTime;
	private Label exportTime;
	private Label totalPages;
	private Label fillSize;
	private Label recordCount;
	private Section statsComposite;
	private Section textSection;
	private Section errorSection;
	private Text tmessage;
	private Text terror;

	@Override
	public Control createControl(final Composite parent) {
		final ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new GridLayout());
		sc.setContent(composite);
		sc.setExpandVertical(true);
		sc.setExpandHorizontal(true);

		createMessages(composite);

		createErrors(composite);

		createStatistics(composite);

		parent.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = parent.getClientArea();
				sc.setMinSize(parent.computeSize(r.width, SWT.DEFAULT));
			}
		});
		return sc;
	}

	protected void createMessages(Composite composite) {
		textSection = new Section(composite, ExpandableComposite.TREE_NODE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 100;
		textSection.setLayoutData(gd);
		textSection.setLayout(new GridLayout());
		textSection.setText("Messages");
		textSection.setExpanded(false);
		UIUtils.setBold(textSection);

		tmessage = new Text(textSection, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
		tmessage.setLayoutData(new GridData(GridData.FILL_BOTH));
		textSection.setClient(tmessage);
		textSection.setVisible(false);
	}

	protected void createErrors(Composite composite) {
		errorSection = new Section(composite, ExpandableComposite.TREE_NODE | ExpandableComposite.TITLE_BAR);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 100;
		errorSection.setLayoutData(gd);
		errorSection.setLayout(new GridLayout());
		errorSection.setText("Errors");
		errorSection.setExpanded(true);
		UIUtils.setBold(errorSection);
		errorSection.setTitleBarForeground(Console.REDCOLOR);

		terror = new Text(errorSection, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
		terror.setLayoutData(new GridData(GridData.FILL_BOTH));
		errorSection.setClient(terror);
		errorSection.setVisible(false);
	}

	private void createStatistics(Composite parent) {
		statsComposite = new Section(parent, ExpandableComposite.TREE_NODE);
		statsComposite.setText("Execution Statistics");
		statsComposite.setExpanded(true);
		UIUtils.setBold(statsComposite);

		Composite composite = new Composite(statsComposite, SWT.NONE);
		statsComposite.setClient(composite);
		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = 20;
		composite.setLayout(layout);

		new Label(composite, SWT.NONE).setText("Compilation Time");

		compilationTime = new Label(composite, SWT.BOLD);
		UIUtils.setBold(compilationTime);

		new Label(composite, SWT.NONE).setText("Filling Time");

		fillingTime = new Label(composite, SWT.BOLD);
		UIUtils.setBold(fillingTime);

		new Label(composite, SWT.NONE).setText("Export Time");

		exportTime = new Label(composite, SWT.BOLD);
		UIUtils.setBold(exportTime);

		new Label(composite, SWT.NONE).setText("Total Pages");

		totalPages = new Label(composite, SWT.BOLD);
		UIUtils.setBold(totalPages);

		new Label(composite, SWT.NONE).setText("Processed Records Count");

		recordCount = new Label(composite, SWT.BOLD);
		UIUtils.setBold(recordCount);

		new Label(composite, SWT.NONE).setText("Fill Size");

		fillSize = new Label(composite, SWT.BOLD);
		UIUtils.setBold(fillSize);

		statsComposite.setVisible(false);
	}

	public void setStats(Statistics stats) {
		statsComposite.setVisible(stats != null);
		if (stats != null) {
			compilationTime.setText(stats.getDuration(ReportControler.ST_COMPILATIONTIME) / 1000 + "s");
			fillingTime.setText(stats.getDuration(ReportControler.ST_FILLINGTIME) / 1000 + "s");
			exportTime.setText(stats.getDuration(ReportControler.ST_EXPORTTIME) / 1000 + "s");

			totalPages.setText(Misc.nvl(stats.getValue(ReportControler.ST_PAGECOUNT), "0") + " pages");
			recordCount.setText(Misc.nvl(stats.getValue(ReportControler.ST_RECORDCOUNTER), "0") + " records");
			fillSize.setText(Misc.nvl(stats.getValue(ReportControler.ST_REPORTSIZE), "0") + " bytes");
		}
		statsComposite.getShell().layout(true);
	}

	public void setMessage(String msg) {
		textSection.setVisible(msg != null);
		tmessage.setText(msg);
	}

	public void addMessage(String msg) {
		textSection.setVisible(msg != null);

		tmessage.setText(tmessage.getText() + msg + "\n");
		textSection.setText("Console: " + msg);
	}

	public void addError(Throwable t) {
		errorSection.setVisible(t != null);
		if (t != null) {
			String msg = terror.getText() + ErrorUtil.getStackTrace(t) + NL;
			terror.setText(terror.getText() + msg + "\n");
			errorSection.setText("Errors: 1");
		} else
			terror.setText("");
	}

	public void clear() {
		tmessage.setText("");
		setStats(null);
		addError(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
