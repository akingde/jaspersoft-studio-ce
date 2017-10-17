/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>With Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.WithQuery#getW <em>W</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.WithQuery#getWname <em>Wname</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.WithQuery#getWithCols <em>With Cols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.WithQuery#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getWithQuery()
 * @model
 * @generated
 */
public interface WithQuery extends EObject
{
  /**
   * Returns the value of the '<em><b>W</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>W</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>W</em>' attribute.
   * @see #setW(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWithQuery_W()
   * @model
   * @generated
   */
  String getW();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WithQuery#getW <em>W</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>W</em>' attribute.
   * @see #getW()
   * @generated
   */
  void setW(String value);

  /**
   * Returns the value of the '<em><b>Wname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wname</em>' attribute.
   * @see #setWname(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWithQuery_Wname()
   * @model
   * @generated
   */
  String getWname();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WithQuery#getWname <em>Wname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wname</em>' attribute.
   * @see #getWname()
   * @generated
   */
  void setWname(String value);

  /**
   * Returns the value of the '<em><b>With Cols</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>With Cols</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>With Cols</em>' containment reference.
   * @see #setWithCols(WithColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWithQuery_WithCols()
   * @model containment="true"
   * @generated
   */
  WithColumns getWithCols();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WithQuery#getWithCols <em>With Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>With Cols</em>' containment reference.
   * @see #getWithCols()
   * @generated
   */
  void setWithCols(WithColumns value);

  /**
   * Returns the value of the '<em><b>Query</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Query</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Query</em>' containment reference.
   * @see #setQuery(SelectQuery)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWithQuery_Query()
   * @model containment="true"
   * @generated
   */
  SelectQuery getQuery();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WithQuery#getQuery <em>Query</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Query</em>' containment reference.
   * @see #getQuery()
   * @generated
   */
  void setQuery(SelectQuery value);

} // WithQuery
