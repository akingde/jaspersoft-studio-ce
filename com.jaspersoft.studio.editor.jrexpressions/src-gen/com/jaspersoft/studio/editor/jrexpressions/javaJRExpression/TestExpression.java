/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.editor.jrexpressions.javaJRExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getTrueStatement <em>True Statement</em>}</li>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getFalseStatement <em>False Statement</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getTestExpression()
 * @model
 * @generated
 */
public interface TestExpression extends JasperReportsExpression
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(JasperReportsExpression)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getTestExpression_Condition()
   * @model containment="true"
   * @generated
   */
  JasperReportsExpression getCondition();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(JasperReportsExpression value);

  /**
   * Returns the value of the '<em><b>True Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>True Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>True Statement</em>' containment reference.
   * @see #setTrueStatement(JasperReportsExpression)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getTestExpression_TrueStatement()
   * @model containment="true"
   * @generated
   */
  JasperReportsExpression getTrueStatement();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getTrueStatement <em>True Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>True Statement</em>' containment reference.
   * @see #getTrueStatement()
   * @generated
   */
  void setTrueStatement(JasperReportsExpression value);

  /**
   * Returns the value of the '<em><b>False Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>False Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>False Statement</em>' containment reference.
   * @see #setFalseStatement(JasperReportsExpression)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getTestExpression_FalseStatement()
   * @model containment="true"
   * @generated
   */
  JasperReportsExpression getFalseStatement();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.TestExpression#getFalseStatement <em>False Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>False Statement</em>' containment reference.
   * @see #getFalseStatement()
   * @generated
   */
  void setFalseStatement(JasperReportsExpression value);

} // TestExpression
