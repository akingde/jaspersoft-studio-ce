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
package com.jaspersoft.studio.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.UUID;

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

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static File createTempDir() throws IOException {
		final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
		File newTempDir;
		final int maxAttempts = 9;
		int attemptCount = 0;
		do {
			attemptCount++;
			if (attemptCount > maxAttempts) {
				throw new IOException("The highly improbable has occurred! Failed to "
						+ "create a unique temporary directory after " + maxAttempts + " attempts.");
			}
			String dirName = UUID.randomUUID().toString();
			newTempDir = new File(sysTempDir, dirName);
		} while (newTempDir.exists());

		if (newTempDir.mkdirs()) {
			return newTempDir;
		} else {
			throw new IOException("Failed to create temp dir named " + newTempDir.getAbsolutePath());
		}
	}

	/**
	 * Recursively delete file or directory
	 * 
	 * @param fileOrDir
	 *          the file or dir to delete
	 * @return true iff all files are successfully deleted
	 */
	public static boolean recursiveDelete(File fileOrDir) {
		if (fileOrDir.isDirectory()) {
			// recursively delete contents
			for (File innerFile : fileOrDir.listFiles()) {
				if (!recursiveDelete(innerFile)) {
					return false;
				}
			}
		}

		return fileOrDir.delete();
	}

	public static byte[] getBytes(File file) throws IOException {
		byte[] b = new byte[(int) file.length()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			in.read(b);
		} finally {
			if (in != null)
				in.close();
		}
		return b;
	}
}
