/**
 */
package com.jaspersoft.studio.data.sql;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scalar Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSostr <em>Sostr</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodbl <em>Sodbl</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodate <em>Sodate</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSotime <em>Sotime</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodt <em>Sodt</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSoUInt <em>So UInt</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSoint <em>Soint</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand()
 * @model
 * @generated
 */
public interface ScalarOperand extends OperandList
{
  /**
   * Returns the value of the '<em><b>Sostr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sostr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sostr</em>' attribute.
   * @see #setSostr(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Sostr()
   * @model
   * @generated
   */
  String getSostr();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSostr <em>Sostr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sostr</em>' attribute.
   * @see #getSostr()
   * @generated
   */
  void setSostr(String value);

  /**
   * Returns the value of the '<em><b>Sodbl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sodbl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sodbl</em>' attribute.
   * @see #setSodbl(BigDecimal)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Sodbl()
   * @model
   * @generated
   */
  BigDecimal getSodbl();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodbl <em>Sodbl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sodbl</em>' attribute.
   * @see #getSodbl()
   * @generated
   */
  void setSodbl(BigDecimal value);

  /**
   * Returns the value of the '<em><b>Sodate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sodate</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sodate</em>' attribute.
   * @see #setSodate(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Sodate()
   * @model
   * @generated
   */
  String getSodate();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodate <em>Sodate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sodate</em>' attribute.
   * @see #getSodate()
   * @generated
   */
  void setSodate(String value);

  /**
   * Returns the value of the '<em><b>Sotime</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sotime</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sotime</em>' attribute.
   * @see #setSotime(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Sotime()
   * @model
   * @generated
   */
  String getSotime();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSotime <em>Sotime</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sotime</em>' attribute.
   * @see #getSotime()
   * @generated
   */
  void setSotime(String value);

  /**
   * Returns the value of the '<em><b>Sodt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sodt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sodt</em>' attribute.
   * @see #setSodt(String)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Sodt()
   * @model
   * @generated
   */
  String getSodt();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSodt <em>Sodt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sodt</em>' attribute.
   * @see #getSodt()
   * @generated
   */
  void setSodt(String value);

  /**
   * Returns the value of the '<em><b>So UInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>So UInt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>So UInt</em>' attribute.
   * @see #setSoUInt(Integer)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_SoUInt()
   * @model
   * @generated
   */
  Integer getSoUInt();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSoUInt <em>So UInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>So UInt</em>' attribute.
   * @see #getSoUInt()
   * @generated
   */
  void setSoUInt(Integer value);

  /**
   * Returns the value of the '<em><b>Soint</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Soint</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Soint</em>' attribute.
   * @see #setSoint(Integer)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getScalarOperand_Soint()
   * @model
   * @generated
   */
  Integer getSoint();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.ScalarOperand#getSoint <em>Soint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Soint</em>' attribute.
   * @see #getSoint()
   * @generated
   */
  void setSoint(Integer value);

} // ScalarOperand
