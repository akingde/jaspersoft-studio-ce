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
package com.jaspersoft.cassandra;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.cassandra.connection.CassandraConnection;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraFieldsProvider {

	@SuppressWarnings("unchecked")
	public static JRField[] getFields(CassandraConnection connection,
			JRDataset reportDataset, Map<String, Object> parameters)
			throws JRException, UnsupportedOperationException {
		String query = "";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		if (reportDataset.getQuery() == null
				|| reportDataset.getQuery().getText() == null
				|| reportDataset.getQuery().getText().length() == 0) {
			return new JRField[0];
		}
		try {
			StringBuffer queryBuf = new StringBuffer("");
			JRQueryChunk[] chunks = reportDataset.getQuery().getChunks();
			List<Object> queryParams = new ArrayList<Object>();
			List<Class<?>> queryParamsClass = new ArrayList<Class<?>>();
			for (int index = 0; index < chunks.length; ++index) {
				switch (chunks[index].getType()) {
				case JRQueryChunk.TYPE_TEXT:
					queryBuf.append(chunks[index].getText());
					break;
				case JRQueryChunk.TYPE_PARAMETER_CLAUSE: {
					// adding a parameter...
					String paramName = chunks[index].getText();

					if (!parameters.containsKey(paramName)) {
						throw new IllegalArgumentException("The parameter '"
								+ paramName + "' is not defined.");
					}

					Object defValue = parameters.get(paramName);
					if (defValue == null) {
						throw new IllegalArgumentException("Please set a "
								+ "default value for the parameter '"
								+ paramName + "'");
					}
					queryBuf.append(defValue.toString());
					break;
				}
				case JRQueryChunk.TYPE_PARAMETER: {
					// adding a parameter...
					String paramName = chunks[index].getText();

					if (!parameters.containsKey(paramName)) {
						throw new IllegalArgumentException("The parameter '"
								+ paramName + "' is not defined.");
					}

					Object defValue = parameters.get(paramName);
					queryBuf.append("?");
					queryParams.add(defValue);
					queryParamsClass.add(findParameterClass(paramName,
							reportDataset));
					break;
				}
				case JRQueryChunk.TYPE_CLAUSE_TOKENS: {
					String[] tokens = chunks[index].getTokens();
					if (tokens.length == 3) {
						String clauseText = "";
						clauseText = tokens[1].trim() + " " + tokens[0].trim()
								+ " (";
						String paramName = tokens[2].trim();
						if (parameters.containsKey(paramName)) {
							Object defValue = parameters.get(paramName);
							if (defValue == null) {
								clauseText = "0 = 0";
							} else {
								List<Object> items = new ArrayList<Object>();
								if (defValue.getClass().isArray()) {
									items = Arrays.asList((Object[]) defValue);
								} else if (defValue instanceof Collection) {
									items.addAll((Collection<Object>) defValue);
								} else {
									items.add(defValue);
								}

								Iterator<Object> iter = items.iterator();
								if (iter.hasNext()) {
									Object itemVal = iter.next();
									queryBuf.append("?");
									queryParams.add(itemVal);
									queryParamsClass.add(null);
									while (iter.hasNext()) {
										itemVal = iter.next();
										clauseText += ",?";
										queryParams.add(itemVal);
										queryParamsClass.add(null);
									}
									clauseText += ")";
								} else {
									clauseText = "0 = 0";
								}
							}
							queryBuf.append(clauseText);
						} else {
							throw new IllegalArgumentException(
									"The parameter '" + paramName
											+ "' is not defined.");
						}
					} else {
						throw new IllegalArgumentException(
								"Invalid $X{} clause");
					}
					break;
				}
				}
			}

			query = queryBuf.toString();
			prepareStatement = connection.getSqlConnection().prepareStatement(
					query);
			for (int parametersCounter = 0; parametersCounter < queryParams
					.size(); parametersCounter++) {
				Class<?> parameterType = (queryParams.get(parametersCounter) == null) ? queryParamsClass
						.get(parametersCounter) : queryParams.get(
						parametersCounter).getClass();
				if (java.lang.Boolean.class.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.BIT);
					} else {
						prepareStatement.setBoolean(parametersCounter + 1,
								((Boolean) queryParams.get(parametersCounter))
										.booleanValue());
					}
				} else if (java.lang.Byte.class.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.TINYINT);
					} else {
						prepareStatement.setByte(parametersCounter + 1,
								((Byte) queryParams.get(parametersCounter))
										.byteValue());
					}
				} else if (java.lang.Double.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.DOUBLE);
					} else {
						prepareStatement.setDouble(parametersCounter + 1,
								((Double) queryParams.get(parametersCounter))
										.doubleValue());
					}
				} else if (java.lang.Float.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.FLOAT);
					} else {
						prepareStatement.setFloat(parametersCounter + 1,
								((Float) queryParams.get(parametersCounter))
										.floatValue());
					}
				} else if (java.lang.Integer.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.INTEGER);
					} else {
						prepareStatement.setInt(parametersCounter + 1,
								((Integer) queryParams.get(parametersCounter))
										.intValue());
					}
				} else if (java.lang.Long.class.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.BIGINT);
					} else {
						prepareStatement.setLong(parametersCounter + 1,
								((Long) queryParams.get(parametersCounter))
										.longValue());
					}
				} else if (java.lang.Short.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.SMALLINT);
					} else {
						prepareStatement.setShort(parametersCounter + 1,
								((Short) queryParams.get(parametersCounter))
										.shortValue());
					}
				} else if (java.math.BigDecimal.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.DECIMAL);
					} else {
						prepareStatement
								.setBigDecimal(parametersCounter + 1,
										(BigDecimal) queryParams
												.get(parametersCounter));
					}
				} else if (java.lang.String.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.VARCHAR);
					} else {
						prepareStatement.setString(parametersCounter + 1,
								queryParams.get(parametersCounter).toString());
					}
				} else if (java.sql.Timestamp.class
						.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.TIMESTAMP);
					} else {
						prepareStatement.setTimestamp(parametersCounter + 1,
								(java.sql.Timestamp) queryParams
										.get(parametersCounter));
					}
				} else if (java.sql.Time.class.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.TIME);
					} else {
						prepareStatement.setTime(parametersCounter + 1,
								(java.sql.Time) queryParams
										.get(parametersCounter));
					}
				} else if (java.util.Date.class.isAssignableFrom(parameterType)) {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.DATE);
					} else {
						prepareStatement.setDate(
								parametersCounter + 1,
								new java.sql.Date(((java.util.Date) queryParams
										.get(parametersCounter)).getTime()));
					}
				} else {
					if (queryParams.get(parametersCounter) == null) {
						prepareStatement.setNull(parametersCounter + 1,
								Types.JAVA_OBJECT);
					} else {
						prepareStatement.setObject(parametersCounter + 1,
								queryParams.get(parametersCounter));
					}
				}
			}

			try {
				prepareStatement.setFetchSize(0);
			} catch (Exception e) {
			}

			resultSet = prepareStatement.executeQuery();
			resultSet.next();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			List<JRDesignField> columns = new ArrayList<JRDesignField>();
			for (int index = 1; index <= resultSetMetaData.getColumnCount(); ++index) {
				JRDesignField field = new JRDesignField();
				field.setName(resultSetMetaData.getColumnLabel(index));
				field.setValueClassName(CassandraConnection.getJdbcTypeClass(
						resultSetMetaData, index));
				field.setDescription(null);
				columns.add(field);
			}

			JRField[] finalFields = new JRField[columns.size()];
			for (int i = 0; i < finalFields.length; ++i) {
				finalFields[i] = (JRField) columns.get(i);
			}
			return finalFields;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new JRException(ex.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
			throw new JRException(t.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			if (prepareStatement != null)
				try {
					prepareStatement.close();
				} catch (Exception e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
				}
		}
	}

	private static Class<?> findParameterClass(String parameterName,
			JRDataset reportDataset) {
		JRParameter[] parameters = reportDataset.getParameters();
		for (JRParameter parameter : parameters) {
			if (parameter.getName().equals(parameterName)) {
				try {
					return parameter.getValueClass();
				} catch (Throwable t) {
				}
			}
		}
		return null;
	}
}
