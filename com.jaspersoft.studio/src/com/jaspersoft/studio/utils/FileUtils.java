package com.jaspersoft.studio.utils;

import java.io.IOException;

public class FileUtils {
	public static String findRelativePath(String base, String path) throws IOException {
		if (base == null)
			throw new IOException("NullOriginalPath");

		if (path == null)
			throw new IOException("NullRelativePath");

		//
		// remove ./ if present
		//
		if (path.startsWith("./"))
			path = path.substring(2);

		//
		// remove any .. reference by taking off the last section/ of
		// the original path
		//
		if (path.startsWith("../")) {
			int slash = base.lastIndexOf('/');
			base = base.substring(0, slash);
			path = path.substring(3);
		}

		int slash = base.lastIndexOf('/');

		if (slash < 0)
			return path;

		String dir = base.substring(0, slash + 1);
		return dir + path;
	}
}
