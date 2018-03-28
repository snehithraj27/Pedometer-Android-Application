package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

class AppCompatSeekBarHelper
  extends AppCompatProgressBarHelper
{
  private static final int[] TINT_ATTRS = { 16843074 };
  private final SeekBar mView;
  
  AppCompatSeekBarHelper(SeekBar paramSeekBar, AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    super(paramSeekBar, paramAppCompatDrawableManager);
    this.mView = paramSeekBar;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    super.loadFromAttributes(paramAttributeSet, paramInt);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(0);
    if (localDrawable != null) {
      this.mView.setThumb(localDrawable);
    }
    paramAttributeSet.recycle();
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/android/support/v7/widget/AppCompatSeekBarHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */