/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.editor.preview.view.control.VParameters;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.swt.widgets.DRDateTime;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.types.date.DateRange;
import net.sf.jasperreports.types.date.RelativeDateRange;
import net.sf.jasperreports.types.date.TimestampRange;

public class DateInput extends com.jaspersoft.studio.editor.preview.input.DateInput {
	public DateInput() {
		super(true, true);
	}

	@Override
	public void createInput(Composite parent, IParameter param, Map<String, Object> params) {
		if (param instanceof PResourceDescriptor) {
			PResourceDescriptor p = (PResourceDescriptor) param;
			ServerProfile sp = WSClientHelper.getServerProfile(p.getWsClient());
			if (sp != null)
				setSupportDateRange(sp.isSupportsDateRanges());
			isNumeric = !p.getWsClient().isSupported(Feature.SEARCHREPOSITORY);
		}
		this.params = params;
		this.param = param;
		Class<?> valueClass = param.getValueClass();
		if (java.sql.Date.class.isAssignableFrom(valueClass)) {
			if (supportDateRange)
				createDateRange(parent, param, params);
			else
				createDate(parent, param, params);
		} else if (java.sql.Time.class.isAssignableFrom(valueClass)) {
			createTime(parent, param, params);
		} else if (java.sql.Timestamp.class.isAssignableFrom(valueClass)
				|| java.util.Date.class.isAssignableFrom(valueClass)) {
			if (supportDateRange)
				createTimestampRange(parent, param, params);
			else
				createTimestamp(parent, param, params);
		} else if (TimestampRange.class.isAssignableFrom(valueClass))
			createTimestampRange(parent, param, params);
		else if (DateRange.class.isAssignableFrom(valueClass))
			createDateRange(parent, param, params);
		date.setToolTipText(VParameters.createToolTip(param));
		date.addFocusListener(focusListener);
	}

	@Override
	protected void handleDateRangeChange(Class<? extends Date> clazz) {
		if (date.getSelection() != null) {
			Date d = date.getSelection();
			updateWithDate(d);
		} else {
			String exp = Misc.nvl(date.getText()).toUpperCase();
			Matcher matcher = Pattern.compile(RelativeDateRange.DATE_RANGE_REGEXP).matcher(exp.replaceAll(" ", ""));
			if (!matcher.matches() && date.getPattern() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(date.getPattern());
				try {
					updateWithDate(sdf.parse(exp));
					return;
				} catch (ParseException e) {
					// do nothing here
				}
			}
			String ntxt = date.getNullText();
			if (ntxt.equals(DRDateTime.NULLTEXT))
				updateModel(null);
			else
				updateModel(Misc.nvl(ntxt.replaceAll(" ", "")).toUpperCase());

		}
	}

	private void updateWithDate(Date d) {
		if (d != null)
			if (isNumeric)
				updateModel(d.getTime());
			else
				updateModel(d);
	}
}
