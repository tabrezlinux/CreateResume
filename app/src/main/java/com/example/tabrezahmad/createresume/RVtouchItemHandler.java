package com.example.tabrezahmad.createresume;

import android.view.View;

/**
 * Created by Me on 24-Jul-18.
 */

// TOUCH EVENTS HANDLER
public class RVtouchItemHandler {

    // interface on touch item click events
    public interface onItemTouchListener {

        void onItemClick();
        void onItemLongClick();

        void onItemDrag();
        void onItemDrop();

    }

    // interface on context menu click events
    public interface onItemContextMenuClickListener{

        void onItemContextMenuCreate();

        void onItemContextMenuClick();

        void onItemContexMenuCancel();


    }

}
