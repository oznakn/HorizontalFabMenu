package com.pinq.horizontalfabmenu;

import android.graphics.drawable.Drawable;

public interface HelperFab {
    public int getBackgroundColor();

    public int getFabSelectedColor();

    public int getFabRippleColor();

    public Drawable getDrawable();

    public void onClick();

    public enum Location {
        LEFT,
        RIGHT
    }
}
