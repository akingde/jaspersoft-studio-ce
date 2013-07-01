/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Database#getDbName <em>Db Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getDatabase()
 * @model
 * @generated
 */
public interface Database extends Schema
{
  /**
   * Returns the value of the '<em><b>Db Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Db Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Db Name</em>' attribute.
   * @see #setDbName(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getDatabase_DbName()
   * @model
   * @generated
   */
  String getDbName();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Database#getDbName <em>Db Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Db Name</em>' attribute.
   * @see #getDbName()
   * @generated
   */
  void setDbName(String value);

} // Database
