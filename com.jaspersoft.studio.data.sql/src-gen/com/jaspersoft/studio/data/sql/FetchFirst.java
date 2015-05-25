/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fetch First</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.FetchFirst#getFetchFirst <em>Fetch First</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.FetchFirst#getRow <em>Row</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getFetchFirst()
 * @model
 * @generated
 */
public interface FetchFirst extends EObject
{
  /**
   * Returns the value of the '<em><b>Fetch First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fetch First</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fetch First</em>' containment reference.
   * @see #setFetchFirst(IntegerValue)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFetchFirst_FetchFirst()
   * @model containment="true"
   * @generated
   */
  IntegerValue getFetchFirst();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FetchFirst#getFetchFirst <em>Fetch First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fetch First</em>' containment reference.
   * @see #getFetchFirst()
   * @generated
   */
  void setFetchFirst(IntegerValue value);

  /**
   * Returns the value of the '<em><b>Row</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Row</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Row</em>' attribute.
   * @see #setRow(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getFetchFirst_Row()
   * @model
   * @generated
   */
  String getRow();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.FetchFirst#getRow <em>Row</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Row</em>' attribute.
   * @see #getRow()
   * @generated
   */
  void setRow(String value);

} // FetchFirst
