/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.editor.jrexpressions.javaJRExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arguments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.Arguments#getExprLst <em>Expr Lst</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getArguments()
 * @model
 * @generated
 */
public interface Arguments extends EObject
{
  /**
   * Returns the value of the '<em><b>Expr Lst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr Lst</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr Lst</em>' containment reference.
   * @see #setExprLst(ExpressionList)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getArguments_ExprLst()
   * @model containment="true"
   * @generated
   */
  ExpressionList getExprLst();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.Arguments#getExprLst <em>Expr Lst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr Lst</em>' containment reference.
   * @see #getExprLst()
   * @generated
   */
  void setExprLst(ExpressionList value);

} // Arguments
