/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operand List Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OperandListGroup#getOpGroup <em>Op Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperandListGroup()
 * @model
 * @generated
 */
public interface OperandListGroup extends EObject
{
  /**
   * Returns the value of the '<em><b>Op Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op Group</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op Group</em>' containment reference.
   * @see #setOpGroup(OperandList)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOperandListGroup_OpGroup()
   * @model containment="true"
   * @generated
   */
  OperandList getOpGroup();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.OperandListGroup#getOpGroup <em>Op Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op Group</em>' containment reference.
   * @see #getOpGroup()
   * @generated
   */
  void setOpGroup(OperandList value);

} // OperandListGroup
