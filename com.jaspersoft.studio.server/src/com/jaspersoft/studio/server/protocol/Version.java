package com.jaspersoft.studio.server.protocol;

import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;

public class Version {
	public static String setJRVersion(ServerInfo si) {
		for (String av : JRXmlWriterHelper.getVersionsSet()) {
			if (av.equals(si.getVersion()))
				return av;
		}
		return JRXmlWriterHelper.LAST_VERSION;
	}

	public static boolean isEstimated(ServerInfo si) {
		for (String av : JRXmlWriterHelper.getVersionsSet()) {
			if (av.equals(si.getVersion()))
				return false;
		}
		return true;
	}

	public static boolean isDateRangeSupported(ServerInfo si) {
		if (si.getVersion().startsWith("5."))
			return true;
		return false;
	}
}
