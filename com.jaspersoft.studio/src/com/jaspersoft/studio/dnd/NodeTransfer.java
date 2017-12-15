/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.jaspersoft.studio.model.ANode;

/**
 * Class for serializing noode with serializable objects to/from a byte array
 */
public class NodeTransfer extends ByteArrayTransfer {
	private static NodeTransfer instance = new NodeTransfer();
	private static final String TYPE_NAME = "node-transfer-format";
	private static final int TYPEID = registerType(TYPE_NAME);

	public static NodeTransfer getInstance() {
		return instance;
	}

	private NodeTransfer() {
	}

	protected ANode[] fromByteArray(byte[] bytes) {
		try {
			if (bytes == null)
				return null;
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));

			/* read number of gadgets */
			int n = in.readInt();
			/* read gadgets */
			ANode[] gadgets = new ANode[n];
			for (int i = 0; i < n; i++) {
				ANode gadget = readGadget(null, in);
				if (gadget == null) {
					return null;
				}
				gadgets[i] = gadget;
			}
			return gadgets;
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * Method declared on Transfer.
	 */
	protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}

	/*
	 * Method declared on Transfer.
	 */
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

	/*
	 * Method declared on Transfer.
	 */
	protected void javaToNative(Object object, TransferData transferData) {
		byte[] bytes = toByteArray((Object[]) object);
		if (bytes != null)
			super.javaToNative(bytes, transferData);
	}

	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
		return fromByteArray(bytes);
	}

	private ANode readGadget(ANode parent, ObjectInputStream dataIn) throws IOException {
		try {
			return (ANode) dataIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected byte[] toByteArray(Object[] nodes) {
		byte[] bytes = null;
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(byteOut);

			/* write number of markers */
			out.writeInt(nodes.length);

			/* write markers */
			for (int i = 0; i < nodes.length; i++){
				if (nodes[i] instanceof ANode) {
					writeNode((ANode) nodes[i], out);
				}
			}
			out.close();
			bytes = byteOut.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			// when in doubt send nothing
		} finally {
			FileUtils.closeStream(byteOut);
		}
		return bytes;
	}

	private void writeNode(ANode node, ObjectOutputStream dataOut) {
		try {
			dataOut.writeObject(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
