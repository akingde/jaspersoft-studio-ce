package com.jaspersoft.studio.server.ic;

import com.jaspersoft.studio.server.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.type.NamedValueEnum;

public enum ICTypes implements NamedValueEnum<String> {

	BOOLEAN(Messages.ICTypes_0), VALUE(Messages.ICTypes_1), SINGLE_LOV(Messages.ICTypes_2), MULTI_LOV(
			Messages.ICTypes_3), MULTI_LOV_CHECKBOX(
					Messages.ICTypes_6), SINGLE_LOV_RADIO(Messages.ICTypes_7), SINGLE_QUERY(
							Messages.ICTypes_4), MULTI_QUERY(Messages.ICTypes_5), MULTI_QUERY_CHECKBOX(
									Messages.ICTypes_8), SINGLE_QUERY_RADIO(Messages.ICTypes_9);

	private String value;

	private ICTypes(String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public String getValue() {
		return name();
	}

	public static String valueOfLabel(String label) {
		return Misc.nvl(valueOf(label), VALUE).name();
	}

	public static String getLabel(String v) {
		try {
			return Misc.nvl(valueOf(v), VALUE).value;
		} catch (IllegalArgumentException e) {
			// nothing to do
		}
		return VALUE.value;
	}

	public static String[] getLabels() {
		return LABELS;
	}

	private static final String[] LABELS = new String[] { Messages.ICTypes_0, Messages.ICTypes_1, Messages.ICTypes_2,
			Messages.ICTypes_3, Messages.ICTypes_4, Messages.ICTypes_5 };

}
