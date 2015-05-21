/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.AnalyticExprArg;
import com.jaspersoft.studio.data.sql.DbObjectName;
import com.jaspersoft.studio.data.sql.Operands;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analytic Expr Arg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.AnalyticExprArgImpl#getCe <em>Ce</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.AnalyticExprArgImpl#getColAlias <em>Col Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalyticExprArgImpl extends AnalyticExprArgsImpl implements AnalyticExprArg
{
  /**
   * The cached value of the '{@link #getCe() <em>Ce</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCe()
   * @generated
   * @ordered
   */
  protected Operands ce;

  /**
   * The cached value of the '{@link #getColAlias() <em>Col Alias</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColAlias()
   * @generated
   * @ordered
   */
  protected DbObjectName colAlias;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnalyticExprArgImpl()
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
    return SqlPackage.Literals.ANALYTIC_EXPR_ARG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operands getCe()
  {
    return ce;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCe(Operands newCe, NotificationChain msgs)
  {
    Operands oldCe = ce;
    ce = newCe;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_EXPR_ARG__CE, oldCe, newCe);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCe(Operands newCe)
  {
    if (newCe != ce)
    {
      NotificationChain msgs = null;
      if (ce != null)
        msgs = ((InternalEObject)ce).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_EXPR_ARG__CE, null, msgs);
      if (newCe != null)
        msgs = ((InternalEObject)newCe).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_EXPR_ARG__CE, null, msgs);
      msgs = basicSetCe(newCe, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_EXPR_ARG__CE, newCe, newCe));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbObjectName getColAlias()
  {
    return colAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetColAlias(DbObjectName newColAlias, NotificationChain msgs)
  {
    DbObjectName oldColAlias = colAlias;
    colAlias = newColAlias;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS, oldColAlias, newColAlias);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColAlias(DbObjectName newColAlias)
  {
    if (newColAlias != colAlias)
    {
      NotificationChain msgs = null;
      if (colAlias != null)
        msgs = ((InternalEObject)colAlias).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS, null, msgs);
      if (newColAlias != null)
        msgs = ((InternalEObject)newColAlias).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS, null, msgs);
      msgs = basicSetColAlias(newColAlias, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS, newColAlias, newColAlias));
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
      case SqlPackage.ANALYTIC_EXPR_ARG__CE:
        return basicSetCe(null, msgs);
      case SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS:
        return basicSetColAlias(null, msgs);
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
      case SqlPackage.ANALYTIC_EXPR_ARG__CE:
        return getCe();
      case SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS:
        return getColAlias();
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
      case SqlPackage.ANALYTIC_EXPR_ARG__CE:
        setCe((Operands)newValue);
        return;
      case SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS:
        setColAlias((DbObjectName)newValue);
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
      case SqlPackage.ANALYTIC_EXPR_ARG__CE:
        setCe((Operands)null);
        return;
      case SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS:
        setColAlias((DbObjectName)null);
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
      case SqlPackage.ANALYTIC_EXPR_ARG__CE:
        return ce != null;
      case SqlPackage.ANALYTIC_EXPR_ARG__COL_ALIAS:
        return colAlias != null;
    }
    return super.eIsSet(featureID);
  }

} //AnalyticExprArgImpl
