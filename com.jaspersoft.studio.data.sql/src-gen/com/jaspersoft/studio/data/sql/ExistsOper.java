/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exists Oper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ExistsOper#getOp <em>Op</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ExistsOper#getSubquery <em>Subquery</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ExistsOper#getOpList <em>Op List</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getExistsOper()
 * @model
 * @generated
 */
public interface ExistsOper extends EObject
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getExistsOper_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ExistsOper#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Subquery</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subquery</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subquery</em>' containment reference.
   * @see #setSubquery(SubQueryOperand)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getExistsOper_Subquery()
   * @model containment="true"
   * @generated
   */
  SubQueryOperand getSubquery();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ExistsOper#getSubquery <em>Subquery</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subquery</em>' containment reference.
   * @see #getSubquery()
   * @generated
   */
  void setSubquery(SubQueryOperand value);

  /**
   * Returns the value of the '<em><b>Op List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op List</em>' containment reference.
   * @see #setOpList(OperandListGroup)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getExistsOper_OpList()
   * @model containment="true"
   * @generated
   */
  OperandListGroup getOpList();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ExistsOper#getOpList <em>Op List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op List</em>' containment reference.
   * @see #getOpList()
   * @generated
   */
  void setOpList(OperandListGroup value);

} // ExistsOper
