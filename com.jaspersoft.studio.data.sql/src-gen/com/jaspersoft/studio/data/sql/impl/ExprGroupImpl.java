/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ExprGroup;
import com.jaspersoft.studio.data.sql.OrExpr;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expr Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ExprGroupImpl#getIsnot <em>Isnot</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ExprGroupImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExprGroupImpl extends MinimalEObjectImpl.Container implements ExprGroup
{
  /**
   * The default value of the '{@link #getIsnot() <em>Isnot</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsnot()
   * @generated
   * @ordered
   */
  protected static final String ISNOT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIsnot() <em>Isnot</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsnot()
   * @generated
   * @ordered
   */
  protected String isnot = ISNOT_EDEFAULT;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected OrExpr expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExprGroupImpl()
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
    return SqlPackage.Literals.EXPR_GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIsnot()
  {
    return isnot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIsnot(String newIsnot)
  {
    String oldIsnot = isnot;
    isnot = newIsnot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EXPR_GROUP__ISNOT, oldIsnot, isnot));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrExpr getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(OrExpr newExpr, NotificationChain msgs)
  {
    OrExpr oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.EXPR_GROUP__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(OrExpr newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXPR_GROUP__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXPR_GROUP__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EXPR_GROUP__EXPR, newExpr, newExpr));
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
      case SqlPackage.EXPR_GROUP__EXPR:
        return basicSetExpr(null, msgs);
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
      case SqlPackage.EXPR_GROUP__ISNOT:
        return getIsnot();
      case SqlPackage.EXPR_GROUP__EXPR:
        return getExpr();
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
      case SqlPackage.EXPR_GROUP__ISNOT:
        setIsnot((String)newValue);
        return;
      case SqlPackage.EXPR_GROUP__EXPR:
        setExpr((OrExpr)newValue);
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
      case SqlPackage.EXPR_GROUP__ISNOT:
        setIsnot(ISNOT_EDEFAULT);
        return;
      case SqlPackage.EXPR_GROUP__EXPR:
        setExpr((OrExpr)null);
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
      case SqlPackage.EXPR_GROUP__ISNOT:
        return ISNOT_EDEFAULT == null ? isnot != null : !ISNOT_EDEFAULT.equals(isnot);
      case SqlPackage.EXPR_GROUP__EXPR:
        return expr != null;
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
    result.append(" (isnot: ");
    result.append(isnot);
    result.append(')');
    return result.toString();
  }

} //ExprGroupImpl
