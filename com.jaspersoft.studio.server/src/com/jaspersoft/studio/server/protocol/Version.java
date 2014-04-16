package com.jaspersoft.studio.server.protocol;

import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo.ServerEdition;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;

public class Version {
	public static boolean isPro(ServerInfo si) {
		return si.getEdition() == ServerEdition.PRO;
	}

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
		return si.getVersion().startsWith("5.");
	}

	public static boolean isGreaterThan(ServerInfo si, String version) {
		return si.getVersion().compareTo(version) >= 0;
	}
}
