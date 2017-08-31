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
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getWq <em>Wq</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Wq</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wq</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wq</em>' containment reference.
   * @see #setWq(WithQuery)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Wq()
   * @model containment="true"
   * @generated
   */
  WithQuery getWq();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getWq <em>Wq</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wq</em>' containment reference.
   * @see #getWq()
   * @generated
   */
  void setWq(WithQuery value);

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
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Query()
   * @model containment="true"
   * @generated
   */
  SelectQuery getQuery();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getQuery <em>Query</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Query</em>' containment reference.
   * @see #getQuery()
   * @generated
   */
  void setQuery(SelectQuery value);

} // Model
