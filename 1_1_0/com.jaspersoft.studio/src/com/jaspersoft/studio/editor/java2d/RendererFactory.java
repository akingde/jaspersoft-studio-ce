/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.core.runtime.Platform;
/*
 * A factory for creating Renderer objects.
 */
public final class RendererFactory {

	/** The image renderer. */
	private static ImageRenderer imageRenderer;

	/**
	 * A.
	 * 
	 * @return the image renderer
	 */
	public static synchronized ImageRenderer a() // getImageRenderer
	{
		if (imageRenderer == null)
			imageRenderer = createRenderer();
		return imageRenderer;
	}

	/**
	 * Creates a new Renderer object.
	 * 
	 * @return the image renderer
	 */
	private static ImageRenderer createRenderer() {
		try {
			if ((Platform.getOS().equals(Platform.OS_WIN32)) && (Platform.getOSArch().equals(Platform.ARCH_X86))) {
				return new Win32ImageRenderer();
			} 

			// else if ((Platform.getOS().equals(Platform.OS_LINUX)) && (Platform.getWS().equals(Platform.WS_GTK))) {
			// return new LinuxImageRenderer();
			// }
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
		} catch (UnsatisfiedLinkError e) {
			e.printStackTrace();
		}
		return new GenericImageRenderer();
	}
}
