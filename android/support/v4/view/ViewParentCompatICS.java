package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

class ViewParentCompatICS
{
  public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return paramViewParent.requestSendAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
}


/* Location:              /Users/snehithraj/Desktop/a/dex2jar-2.0/classes-dex2jar.jar!/android/support/v4/view/ViewParentCompatICS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */