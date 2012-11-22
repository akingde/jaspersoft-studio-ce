/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 */
package com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.JavaJRExpressionPackage;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.MethodInvocation;
import com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.ObjectCreation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.editor.jrexpressions.javaJRExpression.impl.ObjectCreationImpl#getConstructorInvocation <em>Constructor Invocation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectCreationImpl extends JasperReportsExpressionImpl implements ObjectCreation
{
  /**
   * The cached value of the '{@link #getConstructorInvocation() <em>Constructor Invocation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstructorInvocation()
   * @generated
   * @ordered
   */
  protected MethodInvocation constructorInvocation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ObjectCreationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return JavaJRExpressionPackage.Literals.OBJECT_CREATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MethodInvocation getConstructorInvocation()
  {
    return constructorInvocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstructorInvocation(MethodInvocation newConstructorInvocation, NotificationChain msgs)
  {
    MethodInvocation oldConstructorInvocation = constructorInvocation;
    constructorInvocation = newConstructorInvocation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION, oldConstructorInvocation, newConstructorInvocation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstructorInvocation(MethodInvocation newConstructorInvocation)
  {
    if (newConstructorInvocation != constructorInvocation)
    {
      NotificationChain msgs = null;
      if (constructorInvocation != null)
        msgs = ((InternalEObject)constructorInvocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION, null, msgs);
      if (newConstructorInvocation != null)
        msgs = ((InternalEObject)newConstructorInvocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION, null, msgs);
      msgs = basicSetConstructorInvocation(newConstructorInvocation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION, newConstructorInvocation, newConstructorInvocation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION:
        return basicSetConstructorInvocation(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION:
        return getConstructorInvocation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION:
        setConstructorInvocation((MethodInvocation)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION:
        setConstructorInvocation((MethodInvocation)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case JavaJRExpressionPackage.OBJECT_CREATION__CONSTRUCTOR_INVOCATION:
        return constructorInvocation != null;
    }
    return super.eIsSet(featureID);
  }

} //ObjectCreationImpl
