/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getCol <em>Col</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getDb <em>Db</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getWhereEntry <em>Where Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col</em>' containment reference.
   * @see #setCol(Column)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Col()
   * @model containment="true"
   * @generated
   */
  Column getCol();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getCol <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col</em>' containment reference.
   * @see #getCol()
   * @generated
   */
  void setCol(Column value);

  /**
   * Returns the value of the '<em><b>Db</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Db</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Db</em>' containment reference.
   * @see #setDb(Database)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Db()
   * @model containment="true"
   * @generated
   */
  Database getDb();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getDb <em>Db</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Db</em>' containment reference.
   * @see #getDb()
   * @generated
   */
  void setDb(Database value);

  /**
   * Returns the value of the '<em><b>Where Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Where Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Where Entry</em>' containment reference.
   * @see #setWhereEntry(WhereEntry)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_WhereEntry()
   * @model containment="true"
   * @generated
   */
  WhereEntry getWhereEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getWhereEntry <em>Where Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Where Entry</em>' containment reference.
   * @see #getWhereEntry()
   * @generated
   */
  void setWhereEntry(WhereEntry value);

} // Model
