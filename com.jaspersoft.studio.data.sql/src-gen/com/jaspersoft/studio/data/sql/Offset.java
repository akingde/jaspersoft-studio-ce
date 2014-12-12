/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Offset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.Offset#getOffset <em>Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOffset()
 * @model
 * @generated
 */
public interface Offset extends EObject
{
  /**
   * Returns the value of the '<em><b>Offset</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Offset</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Offset</em>' attribute.
   * @see #setOffset(int)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOffset_Offset()
   * @model
   * @generated
   */
  int getOffset();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.Offset#getOffset <em>Offset</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Offset</em>' attribute.
   * @see #getOffset()
   * @generated
   */
  void setOffset(int value);

} // Offset
