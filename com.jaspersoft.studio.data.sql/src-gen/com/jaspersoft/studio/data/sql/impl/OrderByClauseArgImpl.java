/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.AnalyticExprArg;
import com.jaspersoft.studio.data.sql.OrderByClauseArg;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order By Clause Arg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OrderByClauseArgImpl#getCol <em>Col</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByClauseArgImpl extends OrderByClauseArgsImpl implements OrderByClauseArg
{
  /**
   * The cached value of the '{@link #getCol() <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCol()
   * @generated
   * @ordered
   */
  protected AnalyticExprArg col;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OrderByClauseArgImpl()
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
    return SqlPackage.Literals.ORDER_BY_CLAUSE_ARG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticExprArg getCol()
  {
    return col;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCol(AnalyticExprArg newCol, NotificationChain msgs)
  {
    AnalyticExprArg oldCol = col;
    col = newCol;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ORDER_BY_CLAUSE_ARG__COL, oldCol, newCol);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCol(AnalyticExprArg newCol)
  {
    if (newCol != col)
    {
      NotificationChain msgs = null;
      if (col != null)
        msgs = ((InternalEObject)col).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ORDER_BY_CLAUSE_ARG__COL, null, msgs);
      if (newCol != null)
        msgs = ((InternalEObject)newCol).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ORDER_BY_CLAUSE_ARG__COL, null, msgs);
      msgs = basicSetCol(newCol, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ORDER_BY_CLAUSE_ARG__COL, newCol, newCol));
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
      case SqlPackage.ORDER_BY_CLAUSE_ARG__COL:
        return basicSetCol(null, msgs);
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
      case SqlPackage.ORDER_BY_CLAUSE_ARG__COL:
        return getCol();
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
      case SqlPackage.ORDER_BY_CLAUSE_ARG__COL:
        setCol((AnalyticExprArg)newValue);
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
      case SqlPackage.ORDER_BY_CLAUSE_ARG__COL:
        setCol((AnalyticExprArg)null);
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
      case SqlPackage.ORDER_BY_CLAUSE_ARG__COL:
        return col != null;
    }
    return super.eIsSet(featureID);
  }

} //OrderByClauseArgImpl
