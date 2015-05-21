/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.PivotColumns;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.UnpivotInClauseArg;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unpivot In Clause Arg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.UnpivotInClauseArgImpl#getPcols <em>Pcols</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.UnpivotInClauseArgImpl#getCfuls <em>Cfuls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnpivotInClauseArgImpl extends UnpivotInClauseArgsImpl implements UnpivotInClauseArg
{
  /**
   * The cached value of the '{@link #getPcols() <em>Pcols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPcols()
   * @generated
   * @ordered
   */
  protected PivotColumns pcols;

  /**
   * The cached value of the '{@link #getCfuls() <em>Cfuls</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCfuls()
   * @generated
   * @ordered
   */
  protected PivotColumns cfuls;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UnpivotInClauseArgImpl()
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
    return SqlPackage.Literals.UNPIVOT_IN_CLAUSE_ARG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotColumns getPcols()
  {
    return pcols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPcols(PivotColumns newPcols, NotificationChain msgs)
  {
    PivotColumns oldPcols = pcols;
    pcols = newPcols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS, oldPcols, newPcols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPcols(PivotColumns newPcols)
  {
    if (newPcols != pcols)
    {
      NotificationChain msgs = null;
      if (pcols != null)
        msgs = ((InternalEObject)pcols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS, null, msgs);
      if (newPcols != null)
        msgs = ((InternalEObject)newPcols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS, null, msgs);
      msgs = basicSetPcols(newPcols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS, newPcols, newPcols));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotColumns getCfuls()
  {
    return cfuls;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCfuls(PivotColumns newCfuls, NotificationChain msgs)
  {
    PivotColumns oldCfuls = cfuls;
    cfuls = newCfuls;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS, oldCfuls, newCfuls);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCfuls(PivotColumns newCfuls)
  {
    if (newCfuls != cfuls)
    {
      NotificationChain msgs = null;
      if (cfuls != null)
        msgs = ((InternalEObject)cfuls).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS, null, msgs);
      if (newCfuls != null)
        msgs = ((InternalEObject)newCfuls).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS, null, msgs);
      msgs = basicSetCfuls(newCfuls, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS, newCfuls, newCfuls));
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
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS:
        return basicSetPcols(null, msgs);
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS:
        return basicSetCfuls(null, msgs);
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
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS:
        return getPcols();
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS:
        return getCfuls();
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
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS:
        setPcols((PivotColumns)newValue);
        return;
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS:
        setCfuls((PivotColumns)newValue);
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
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS:
        setPcols((PivotColumns)null);
        return;
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS:
        setCfuls((PivotColumns)null);
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
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__PCOLS:
        return pcols != null;
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG__CFULS:
        return cfuls != null;
    }
    return super.eIsSet(featureID);
  }

} //UnpivotInClauseArgImpl
