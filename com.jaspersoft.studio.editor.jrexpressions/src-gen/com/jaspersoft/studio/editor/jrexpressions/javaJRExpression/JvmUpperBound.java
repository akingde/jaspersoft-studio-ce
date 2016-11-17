/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.editor.jrexpressions.javaJRExpression;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jvm Upper Bound</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JvmUpperBound#getTypeReference <em>Type Reference</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getJvmUpperBound()
 * @model
 * @generated
 */
public interface JvmUpperBound extends EObject
{
  /**
   * Returns the value of the '<em><b>Type Reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Reference</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Reference</em>' containment reference.
   * @see #setTypeReference(JvmTypeReference)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getJvmUpperBound_TypeReference()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getTypeReference();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JvmUpperBound#getTypeReference <em>Type Reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Reference</em>' containment reference.
   * @see #getTypeReference()
   * @generated
   */
  void setTypeReference(JvmTypeReference value);

} // JvmUpperBound
