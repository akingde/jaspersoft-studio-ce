/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.channels.FileChannel;
import java.util.Properties;
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

	public static File createTempFile(String prefix, String sufix) throws IOException {
		File f = File.createTempFile(prefix, sufix);
		f.deleteOnExit();
		return f;
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

	public static String readFileAsAString(File file) throws IOException {
		return new String(getBytesFromFile(file));
	}

	/**
	 * Returns the contents of the file in a byte array.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IllegalArgumentException("File is too large! (larger or equal to 2G)");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static File fileRenamed(File file, String strFilename, String ext) {
		return fileRenamed(file, strFilename, ext, true);
	}

	public static File fileRenamed(File file, String strFilename, String ext, boolean showWarning) {
		String fname = strFilename + ext;
		file.renameTo(new File(fname));
		if (showWarning)
			UIUtils.showWarning("Attention! file type is different, so it was renamed to:\n " + fname);
		return new File(fname);
	}

	public static String readInputStreamAsString(InputStream in) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(in);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result = bis.read();
		while (result != -1) {
			byte b = (byte) result;
			buf.write(b);
			result = bis.read();
		}
		return buf.toString();
	}

	public static void writeFile(File f, String content) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			fw.write(content);
		} finally {
			if (fw != null)
				fw.close();
		}
	}

	public static String getPropertyAsString(Properties prop) {
		StringBuffer str = new StringBuffer();
		for (String key : prop.stringPropertyNames()) {
			str.append(key).append("=").append(prop.getProperty(key)).append("\n");
		}
		return str.toString();
	}

	public static Properties load(String propertiesString) throws IOException {
		Properties properties = new Properties();
		if (propertiesString != null)
			properties.load(new StringReader(propertiesString));
		return properties;
	}
}
