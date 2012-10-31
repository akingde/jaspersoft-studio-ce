/**
 */
package com.jaspersoft.studio.editor.jrexpressions.javaJRExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Creation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.ObjectCreation#getConstructorInvocation <em>Constructor Invocation</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getObjectCreation()
 * @model
 * @generated
 */
public interface ObjectCreation extends JasperReportsExpression
{
  /**
   * Returns the value of the '<em><b>Constructor Invocation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constructor Invocation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constructor Invocation</em>' containment reference.
   * @see #setConstructorInvocation(MethodInvocation)
   * @see com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage#getObjectCreation_ConstructorInvocation()
   * @model containment="true"
   * @generated
   */
  MethodInvocation getConstructorInvocation();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.ObjectCreation#getConstructorInvocation <em>Constructor Invocation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constructor Invocation</em>' containment reference.
   * @see #getConstructorInvocation()
   * @generated
   */
  void setConstructorInvocation(MethodInvocation value);

} // ObjectCreation
