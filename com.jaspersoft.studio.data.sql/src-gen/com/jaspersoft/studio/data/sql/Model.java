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
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getQuery <em>Query</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getOrderByEntry <em>Order By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getLim <em>Lim</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getOffset <em>Offset</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getFetchFirst <em>Fetch First</em>}</li>
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

  /**
   * Returns the value of the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Order By Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Order By Entry</em>' containment reference.
   * @see #setOrderByEntry(OrOrderByColumn)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_OrderByEntry()
   * @model containment="true"
   * @generated
   */
  OrOrderByColumn getOrderByEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getOrderByEntry <em>Order By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order By Entry</em>' containment reference.
   * @see #getOrderByEntry()
   * @generated
   */
  void setOrderByEntry(OrOrderByColumn value);

  /**
   * Returns the value of the '<em><b>Lim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lim</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lim</em>' containment reference.
   * @see #setLim(Limit)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Lim()
   * @model containment="true"
   * @generated
   */
  Limit getLim();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getLim <em>Lim</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lim</em>' containment reference.
   * @see #getLim()
   * @generated
   */
  void setLim(Limit value);

  /**
   * Returns the value of the '<em><b>Offset</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Offset</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Offset</em>' containment reference.
   * @see #setOffset(Offset)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Offset()
   * @model containment="true"
   * @generated
   */
  Offset getOffset();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getOffset <em>Offset</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Offset</em>' containment reference.
   * @see #getOffset()
   * @generated
   */
  void setOffset(Offset value);

  /**
   * Returns the value of the '<em><b>Fetch First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fetch First</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fetch First</em>' containment reference.
   * @see #setFetchFirst(FetchFirst)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_FetchFirst()
   * @model containment="true"
   * @generated
   */
  FetchFirst getFetchFirst();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getFetchFirst <em>Fetch First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fetch First</em>' containment reference.
   * @see #getFetchFirst()
   * @generated
   */
  void setFetchFirst(FetchFirst value);

} // Model
