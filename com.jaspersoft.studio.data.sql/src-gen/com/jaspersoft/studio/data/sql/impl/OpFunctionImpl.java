/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.FunctionAnalytical;
import com.jaspersoft.studio.data.sql.OpFunction;
import com.jaspersoft.studio.data.sql.OpFunctionArg;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Op Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OpFunctionImpl#getFname <em>Fname</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OpFunctionImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OpFunctionImpl#getFan <em>Fan</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OpFunctionImpl extends MinimalEObjectImpl.Container implements OpFunction
{
  /**
   * The default value of the '{@link #getFname() <em>Fname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFname()
   * @generated
   * @ordered
   */
  protected static final String FNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFname() <em>Fname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFname()
   * @generated
   * @ordered
   */
  protected String fname = FNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected OpFunctionArg args;

  /**
   * The cached value of the '{@link #getFan() <em>Fan</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFan()
   * @generated
   * @ordered
   */
  protected FunctionAnalytical fan;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OpFunctionImpl()
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
    return SqlPackage.Literals.OP_FUNCTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFname()
  {
    return fname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFname(String newFname)
  {
    String oldFname = fname;
    fname = newFname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.OP_FUNCTION__FNAME, oldFname, fname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpFunctionArg getArgs()
  {
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArgs(OpFunctionArg newArgs, NotificationChain msgs)
  {
    OpFunctionArg oldArgs = args;
    args = newArgs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.OP_FUNCTION__ARGS, oldArgs, newArgs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArgs(OpFunctionArg newArgs)
  {
    if (newArgs != args)
    {
      NotificationChain msgs = null;
      if (args != null)
        msgs = ((InternalEObject)args).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OP_FUNCTION__ARGS, null, msgs);
      if (newArgs != null)
        msgs = ((InternalEObject)newArgs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OP_FUNCTION__ARGS, null, msgs);
      msgs = basicSetArgs(newArgs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.OP_FUNCTION__ARGS, newArgs, newArgs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionAnalytical getFan()
  {
    return fan;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFan(FunctionAnalytical newFan, NotificationChain msgs)
  {
    FunctionAnalytical oldFan = fan;
    fan = newFan;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.OP_FUNCTION__FAN, oldFan, newFan);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFan(FunctionAnalytical newFan)
  {
    if (newFan != fan)
    {
      NotificationChain msgs = null;
      if (fan != null)
        msgs = ((InternalEObject)fan).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OP_FUNCTION__FAN, null, msgs);
      if (newFan != null)
        msgs = ((InternalEObject)newFan).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OP_FUNCTION__FAN, null, msgs);
      msgs = basicSetFan(newFan, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.OP_FUNCTION__FAN, newFan, newFan));
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
      case SqlPackage.OP_FUNCTION__ARGS:
        return basicSetArgs(null, msgs);
      case SqlPackage.OP_FUNCTION__FAN:
        return basicSetFan(null, msgs);
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
      case SqlPackage.OP_FUNCTION__FNAME:
        return getFname();
      case SqlPackage.OP_FUNCTION__ARGS:
        return getArgs();
      case SqlPackage.OP_FUNCTION__FAN:
        return getFan();
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
      case SqlPackage.OP_FUNCTION__FNAME:
        setFname((String)newValue);
        return;
      case SqlPackage.OP_FUNCTION__ARGS:
        setArgs((OpFunctionArg)newValue);
        return;
      case SqlPackage.OP_FUNCTION__FAN:
        setFan((FunctionAnalytical)newValue);
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
      case SqlPackage.OP_FUNCTION__FNAME:
        setFname(FNAME_EDEFAULT);
        return;
      case SqlPackage.OP_FUNCTION__ARGS:
        setArgs((OpFunctionArg)null);
        return;
      case SqlPackage.OP_FUNCTION__FAN:
        setFan((FunctionAnalytical)null);
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
      case SqlPackage.OP_FUNCTION__FNAME:
        return FNAME_EDEFAULT == null ? fname != null : !FNAME_EDEFAULT.equals(fname);
      case SqlPackage.OP_FUNCTION__ARGS:
        return args != null;
      case SqlPackage.OP_FUNCTION__FAN:
        return fan != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (fname: ");
    result.append(fname);
    result.append(')');
    return result.toString();
  }

} //OpFunctionImpl
