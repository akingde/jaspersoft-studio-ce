package com.jaspersoft.studio.server.protocol.restv2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.protocol.IConnection;

public abstract class ARestV2ConnectionJersey extends ARestV2Connection {
	protected WebTarget target;
	protected JSSApacheConnector connector;

	protected <T> T toObj(Response res, Class<T> clazz, IProgressMonitor monitor) throws IOException {
		T r = null;
		try {
			switch (res.getStatus()) {
			case 200:
			case 201:
				r = res.readEntity(checkClazz(res, clazz));
			case 204:
				break;
			default:
				eh.handleException(res, monitor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
		return r;
	}

	protected <T> Class<T> checkClazz(Response res, Class<T> clazz) {
		if (clazz == null) {
			String type = res.getHeaderString("Content-Type");
			int sind = type.indexOf(".");
			int eind = type.indexOf("+");
			if (sind >= 0 && eind >= 0) {
				type = type.substring(sind + 1, eind);
				clazz = (Class<T>) WsTypes.INST().getType(type);
			}
		}
		return clazz;
	}

	protected <T> T toObj(Response res, GenericType<T> type, IProgressMonitor monitor) throws IOException {
		T r = null;
		try {
			switch (res.getStatus()) {
			case 200:
			case 201:
				r = res.readEntity(type);
			case 204:
				break;
			default:
				eh.handleException(res, monitor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
		return r;
	}

	protected void readFile(Response res, File f, IProgressMonitor monitor) throws IOException {
		try {
			switch (res.getStatus()) {
			case 200:
			case 201:
				InputStream in = res.readEntity(InputStream.class);
				if (in != null) {
					OutputStream out = new FileOutputStream(f);
					try {
						IOUtils.copy(in, out);
					} finally {
						FileUtils.closeStream(out);
						FileUtils.closeStream(in);
					}
				}
			case 204:
				break;
			default:
				eh.handleException(res, monitor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
	}

	protected byte[] readFile(Response res, IProgressMonitor monitor) throws IOException {
		byte[] b = null;
		try {
			switch (res.getStatus()) {
			case 200:
			case 201:
				InputStream in = res.readEntity(InputStream.class);
				if (in != null) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					try {
						byte[] data = new byte[1000];
						int bytesRead;
						while ((bytesRead = in.read(data)) != -1)
							out.write(data, 0, bytesRead);

						b = out.toByteArray();
					} finally {
						FileUtils.closeStream(out);
						FileUtils.closeStream(in);
					}
				}
			case 204:
				break;
			default:
				eh.handleException(res, monitor);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			res.close();
		}
		return b;
	}

	protected void writeFile(Response res, InputStream in, IProgressMonitor monitor) throws IOException {
		try {
			switch (res.getStatus()) {
			case 200:
			case 201:
				res.readEntity(String.class);
			case 204:
				break;
			default:
				eh.handleException(res, monitor);
			}
		} finally {
			FileUtils.closeStream(in);
			res.close();
		}
	}

	public ARestV2ConnectionJersey() {
		setParent(this);
	}

	protected IConnection parent;

	@Override
	public void setParent(IConnection parent) {
		this.parent = parent;
	}
}
