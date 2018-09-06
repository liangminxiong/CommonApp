package com.project.wisdomfirecontrol.firecontrol.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*可滑动*/
public class HackyViewPager extends ViewPager {


	private static final String TAG = "HackyViewPager";

	public HackyViewPager(Context context) {
		super(context);
	}
	
	public HackyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
		
			return false;
		}catch(ArrayIndexOutOfBoundsException e ){
			
			return false;
		}
	}

}
