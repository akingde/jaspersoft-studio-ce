/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.restv2.DiffFields;
import com.jaspersoft.studio.swt.widgets.WTimeZone;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.eclipse.util.Misc;

public class TimeZoneProperty {

	public static void addTimeZone(AMResource res, Composite composite) {
		final ResourceDescriptor rd = res.getValue();
		if (res.isSupported(Feature.TIMEZONE)) {
			UIUtil.createLabel(composite, "Time Zone");
			final WTimeZone tzone = new WTimeZone(composite, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			// gd.horizontalSpan = 2;
			tzone.setLayoutData(gd);
			String stz = DiffFields.getSoapValue(rd, DiffFields.TIMEZONE);
			if (!Misc.isNullOrEmpty(stz))
				tzone.setSelection(TimeZone.getTimeZone(stz));
			tzone.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TimeZone timeZone = tzone.getTimeZone();
					String v = timeZone != null ? timeZone.getID() : null;
					DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, v);
				}
			});
			tzone.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					TimeZone timeZone = tzone.getTimeZone();
					String v = timeZone != null ? timeZone.getID() : null;
					DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, v);
				}
			});
		}
	}

}
