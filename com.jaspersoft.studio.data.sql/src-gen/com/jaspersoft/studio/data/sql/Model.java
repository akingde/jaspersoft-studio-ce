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
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getTbl <em>Tbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getWhereEntry <em>Where Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getGroupByEntry <em>Group By Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getHavingEntry <em>Having Entry</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.Model#getOrderByEntry <em>Order By Entry</em>}</li>
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
   * @see #setCol(Columns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Col()
   * @model containment="true"
   * @generated
   */
  Columns getCol();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getCol <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col</em>' containment reference.
   * @see #getCol()
   * @generated
   */
  void setCol(Columns value);

  /**
   * Returns the value of the '<em><b>Tbl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tbl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tbl</em>' containment reference.
   * @see #setTbl(Tables)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_Tbl()
   * @model containment="true"
   * @generated
   */
  Tables getTbl();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getTbl <em>Tbl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tbl</em>' containment reference.
   * @see #getTbl()
   * @generated
   */
  void setTbl(Tables value);

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

  /**
   * Returns the value of the '<em><b>Group By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group By Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group By Entry</em>' containment reference.
   * @see #setGroupByEntry(GroupByColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_GroupByEntry()
   * @model containment="true"
   * @generated
   */
  GroupByColumns getGroupByEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getGroupByEntry <em>Group By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group By Entry</em>' containment reference.
   * @see #getGroupByEntry()
   * @generated
   */
  void setGroupByEntry(GroupByColumns value);

  /**
   * Returns the value of the '<em><b>Having Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Having Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Having Entry</em>' containment reference.
   * @see #setHavingEntry(HavingEntry)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_HavingEntry()
   * @model containment="true"
   * @generated
   */
  HavingEntry getHavingEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getHavingEntry <em>Having Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Having Entry</em>' containment reference.
   * @see #getHavingEntry()
   * @generated
   */
  void setHavingEntry(HavingEntry value);

  /**
   * Returns the value of the '<em><b>Order By Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Order By Entry</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Order By Entry</em>' containment reference.
   * @see #setOrderByEntry(OrderByColumns)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getModel_OrderByEntry()
   * @model containment="true"
   * @generated
   */
  OrderByColumns getOrderByEntry();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Model#getOrderByEntry <em>Order By Entry</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order By Entry</em>' containment reference.
   * @see #getOrderByEntry()
   * @generated
   */
  void setOrderByEntry(OrderByColumns value);

} // Model
