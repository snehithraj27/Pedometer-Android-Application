package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper
{
  private TintInfo mBackgroundTint;
  private final AppCompatDrawableManager mDrawableManager;
  private TintInfo mInternalBackgroundTint;
  private TintInfo mTmpInfo;
  private final View mView;
  
  AppCompatBackgroundHelper(View paramView, AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    this.mView = paramView;
    this.mDrawableManager = paramAppCompatDrawableManager;
  }
  
  private void compatTintDrawableUsingFrameworkTint(@NonNull Drawable paramDrawable)
  {
    if (this.mTmpInfo == null) {
      this.mTmpInfo = new TintInfo();
    }
    TintInfo localTintInfo = this.mTmpInfo;
    localTintInfo.clear();
    Object localObject = ViewCompat.getBackgroundTintList(this.mView);
    if (localObject != null)
    {
      localTintInfo.mHasTintList = true;
      localTintInfo.mTintList = ((ColorStateList)localObject);
    }
    localObject = ViewCompat.getBackgroundTintMode(this.mView);
    if (localObject != null)
    {
      localTintInfo.mHasTintMode = true;
      localTintInfo.mTintMode = ((PorterDuff.Mode)localObject);
    }
    if ((localTintInfo.mHasTintList) || (localTintInfo.mHasTintMode)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, localTintInfo, this.mView.getDrawableState());
    }
  }
  
  private boolean shouldCompatTintUsingFrameworkTint(@NonNull Drawable paramDrawable)
  {
    return (Build.VERSION.SDK_INT == 21) && ((paramDrawable instanceof GradientDrawable));
  }
  
  void applySupportBackgroundTint()
  {
    Drawable localDrawable = this.mView.getBackground();
    if (localDrawable != null)
    {
      if (this.mBackgroundTint == null) {
        break label35;
      }
      AppCompatDrawableManager.tintDrawable(localDrawable, this.mBackgroundTint, this.mView.getDrawableState());
    }
    label35:
    do
    {
      return;
      if (this.mInternalBackgroundTint != null)
      {
        AppCompatDrawableManager.tintDrawable(localDrawable, this.mInternalBackgroundTint, this.mView.getDrawableState());
        return;
      }
    } while (!shouldCompatTintUsingFrameworkTint(localDrawable));
    compatTintDrawableUsingFrameworkTint(localDrawable);
  }
  
  ColorStateList getSupportBackgroundTintList()
  {
    if (this.mBackgroundTint != null) {
      return this.mBackgroundTint.mTintList;
    }
    return null;
  }
  
  PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (this.mBackgroundTint != null) {
      return this.mBackgroundTint.mTintMode;
    }
    return null;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = this.mView.getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ViewBackgroundHelper, paramInt, 0);
    try
    {
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_android_background))
      {
        ColorStateList localColorStateList = this.mDrawableManager.getTintList(this.mView.getContext(), paramAttributeSet.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1));
        if (localColorStateList != null) {
          setInternalBackgroundTint(localColorStateList);
        }
      }
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
        ViewCompat.setBackgroundTintList(this.mView, paramAttributeSet.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
      }
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
        ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
      }
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  void onSetBackgroundDrawable(Drawable paramDrawable)
  {
    setInternalBackgroundTint(null);
  }
  
  void onSetBackgroundResource(int paramInt)
  {
    if (this.mDrawableManager != null) {}
    for (ColorStateList localColorStateList = this.mDrawableManager.getTintList(this.mView.getContext(), paramInt);; localColorStateList = null)
    {
      setInternalBackgroundTint(localColorStateList);
      return;
    }
  }
  
  void setInternalBackgroundTint(ColorStateList paramColorStateList)
  {
    if (paramColorStateList != null)
    {
      if (this.mInternalBackgroundTint == null) {
        this.mInternalBackgroundTint = new TintInfo();
      }
      this.mInternalBackgroundTint.mTintList = paramColorStateList;
      this.mInternalBackgroundTint.mHasTintList = true;
    }
    for (;;)
    {
      applySupportBackgroundTint();
      return;
      this.mInternalBackgroundTint = null;
    }
  }
  
  void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (this.mBackgroundTint == null) {
      this.mBackgroundTint = new TintInfo();
    }
    this.mBackgroundTint.mTintList = paramColorStateList;
    this.mBackgroundTint.mHasTintList = true;
    applySupportBackgroundTint();
  }
  
  void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (this.mBackgroundTint == null) {
      this.mBackgroundTint = new TintInfo();
    }
    this.mBackgroundTint.mTintMode = paramMode;
    this.mBackgroundTint.mHasTintMode = true;
    applySupportBackgroundTint();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/android/support/v7/widget/AppCompatBackgroundHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */