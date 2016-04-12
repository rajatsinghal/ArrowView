package com.example.pruthvi.readytoupdate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class ArrowView extends View {

	private int arrow_dist_change_max;
	private int arrow_dist_change_per_frame;
	private int arrow_distance = 0;
	private int arrow_left = 0;
	private int arrow_top = 0;
	private int arrow_right = 0;
	private int arrow_bottom = 0;
	private boolean arrow_direction_up = true;

	Drawable original_arrow_drawable;
	public boolean initialization_completed = false;

	private int top_arrow_src = R.drawable.tutorial_arrow_top;
	//private int bottom_arrow_src = R.drawable.tutorial_arrow_down;

	public ArrowView(Context context) {
		super(context);
		init(context);
	}

	public ArrowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ArrowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	void init(Context context) {
		original_arrow_drawable = getResources().getDrawable(top_arrow_src);
		arrow_dist_change_max = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
		arrow_dist_change_per_frame = arrow_dist_change_max / 5;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (!initialization_completed) {
			arrow_left = getLeft();
			arrow_top = getTop();
			arrow_right = getRight();
			arrow_bottom = getBottom();

			Log.i("Brood", "init Left: " + arrow_left);
			Log.i("Brood", "init Right: " + arrow_right);
			Log.i("Brood", "init Top: " + arrow_top);
			Log.i("Brood", "init Bottom: " + arrow_bottom);

			initialization_completed = true;
		}

		if (arrow_direction_up)
			arrow_distance -= arrow_dist_change_per_frame;
		else
			arrow_distance += arrow_dist_change_per_frame;

		if (arrow_distance == -arrow_dist_change_max || arrow_distance == arrow_dist_change_max) { //If reached to topmost or bottommost change direction
			arrow_direction_up = !arrow_direction_up;
		}

		Log.i("Brood", "arrow_distance: " + arrow_distance);
		arrow_distance = 0;
		//original_arrow_drawable.setBounds(arrow_left, arrow_top + arrow_distance, arrow_right, arrow_bottom + arrow_distance);
		original_arrow_drawable.draw(canvas);
		//invalidate();

		super.onDraw(canvas);
	}

}