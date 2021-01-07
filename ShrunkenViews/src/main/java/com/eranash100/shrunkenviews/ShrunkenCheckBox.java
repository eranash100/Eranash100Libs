package com.eranash100.shrunkenviews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CheckBox;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;

public class ShrunkenCheckBox extends CheckBox
{
    private float clickedStatePercentage; //The clicked state scale percentage.
    private int durationInMillis; //The duration(IN MILLIS) of the scale animation (The actual time until the view get to his clicked state).

    /**
     * A constructor for dynamic declaration.
     *
     * @param context Context in purpose to initiate the view.
     */
    public ShrunkenCheckBox(Context context)
    {
        super(context);
        init(null);
    }

    /**
     * A constructor for dynamic declaration with params.
     *
     * @param context                Context in purpose to initiate the view.
     * @param clickedStatePercentage The scale percentage of clicked state.
     * @param durationInMillis       The duration of scale animation.
     */
    public ShrunkenCheckBox(Context context, int clickedStatePercentage, int durationInMillis)
    {
        super(context);
        this.clickedStatePercentage = ((float) clickedStatePercentage) / 100;
        this.durationInMillis = durationInMillis;
    }

    /**
     * A default constructor for declaring in XML files.
     *
     * @param context Context in purpose to initiate the view.
     * @param attrs   The attributes that given in the XML declaration.
     */
    public ShrunkenCheckBox(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    /**
     * A default constructor for declaring in XML files.
     *
     * @param context      Context in purpose to initiate the view.
     * @param attrs        The attributes that given in the XML declaration.
     * @param defStyleAttr The style attributes that given in the XML declaration.
     */
    public ShrunkenCheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * A default constructor for declaring in XML files.
     *
     * @param context      Context in purpose to initiate the view.
     * @param attrs        The attributes that given in the XML declaration.
     * @param defStyleAttr The style attributes that given in the XML declaration.
     * @param defStyleRes  The style resources that given in the XML declaration.
     */
    public ShrunkenCheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    /**
     * An init method that call when the view initiating and initiate the class members.
     *
     * @param attrs The attributes that given in the XML declaration.
     */
    private void init(@Nullable AttributeSet attrs)
    {
        //Setting the default values
        this.clickedStatePercentage = 0.9f;
        this.durationInMillis = 100;
        if (attrs != null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ShrunkenCheckBox);
            this.clickedStatePercentage = typedArray.getInteger(R.styleable.ShrunkenCheckBox_cb_clicked_state_percent, 90);
            this.clickedStatePercentage /= 100;
            this.durationInMillis = typedArray.getInteger(R.styleable.ShrunkenCheckBox_cb_duration_in_millis, 100);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(this,
                        "scaleX", this.clickedStatePercentage
                );
                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(this,
                        "scaleY", this.clickedStatePercentage
                );
                scaleDownX.setDuration(this.durationInMillis);
                scaleDownY.setDuration(this.durationInMillis);

                AnimatorSet scaleDown = new AnimatorSet();
                scaleDown.play(scaleDownX).with(scaleDownY);

                scaleDown.start();
                break;

            case MotionEvent.ACTION_UP:
                ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(
                        this, "scaleX", 1f);
                ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
                        this, "scaleY", 1f);
                scaleDownX2.setDuration(this.durationInMillis);
                scaleDownY2.setDuration(this.durationInMillis);

                AnimatorSet scaleDown2 = new AnimatorSet();
                scaleDown2.play(scaleDownX2).with(scaleDownY2);

                scaleDown2.start();
                break;
        }
        return super.onTouchEvent(event);
    }

    public int getClickedStatePercentage()
    {
        return (int) (clickedStatePercentage * 100);
    }

    public int getDurationInMillis()
    {
        return durationInMillis;
    }

    public void setClickedStatePercentage(@IntRange(from = 0, to = 100) int clickedStatePercentage)
    {
        this.clickedStatePercentage = ((float) clickedStatePercentage) / 100;
    }

    public void setDurationInMillis(@IntRange(from = 1) int durationInMillis)
    {
        this.durationInMillis = durationInMillis;
    }
}
