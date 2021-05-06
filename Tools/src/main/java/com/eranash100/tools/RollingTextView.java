package com.eranash100.tools;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RollingTextView extends androidx.appcompat.widget.AppCompatTextView implements ValueAnimator.AnimatorUpdateListener
{
    private int startValue;
    private int endValue;
    private int durationInMillis;
    private int startDelayInMillis;
    private boolean startWhenCreated;
    private boolean withCommas;
    private String textBeforeTheNumber;
    private String textAfterTheNumber;
    private ValueAnimator valueAnimator;

    public RollingTextView(Context context) {
        super(context);
        this.init((AttributeSet)null);
    }

    public RollingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs);
    }

    public RollingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs);
    }

    private void init(@Nullable AttributeSet attributeSet) {

        TypedArray typedArray = this.getContext().obtainStyledAttributes(attributeSet, R.styleable.RollingTextView);
        this.startValue = typedArray.getInteger(R.styleable.RollingTextView_start_value, 0);
        this.endValue = typedArray.getInteger(R.styleable.RollingTextView_end_value, 100);
        this.durationInMillis = typedArray.getInteger(R.styleable.RollingTextView_duration_in_millis, 1500);
        this.startDelayInMillis = typedArray.getInteger(R.styleable.RollingTextView_start_delay_in_millis, 0);
        this.startWhenCreated = typedArray.getBoolean(R.styleable.RollingTextView_start_when_created, true);
        this.withCommas = typedArray.getBoolean(R.styleable.RollingTextView_with_commas, false);
        this.textBeforeTheNumber = typedArray.getString(R.styleable.RollingTextView_textBeforeTheNumber) != null ? typedArray.getString(R.styleable.RollingTextView_textBeforeTheNumber) : "";
        this.textAfterTheNumber = typedArray.getString(R.styleable.RollingTextView_textAfterTheNumber) != null ? typedArray.getString(R.styleable.RollingTextView_textAfterTheNumber) : "";
        this.valueAnimator = new ValueAnimator();
        this.valueAnimator.setDuration((long)this.durationInMillis);
        this.valueAnimator.setStartDelay((long)this.startDelayInMillis);
        this.valueAnimator.setInterpolator(new AccelerateInterpolator());
        this.valueAnimator.addUpdateListener(this);
        this.valueAnimator.setFloatValues(new float[]{0.0F, 1.0F});
        if (this.startWhenCreated) {
            this.startRollingAnimation();
        }

    }

    public int getStartValue() {
        return this.startValue;
    }

    public int getEndValue() {
        return this.endValue;
    }

    public int getDurationInMillis() {
        return this.durationInMillis;
    }

    public int getStartDelayInMillis() {
        return this.startDelayInMillis;
    }

    public boolean isStartWhenCreated() {
        return this.startWhenCreated;
    }

    public boolean isWithCommas() {
        return this.withCommas;
    }

    public String getTextBeforeTheNumber() {
        return this.textBeforeTheNumber;
    }

    public String getTextAfterTheNumber() {
        return this.textAfterTheNumber;
    }

    public void startRollingAnimation() {
        this.valueAnimator.start();
    }

    public void setStartValue(int value) {
        this.startValue = value;
    }

    public void setEndValue(int value) {
        this.endValue = value;
    }

    public void setDurationInMillis(int duration) {
        this.durationInMillis = duration;
    }

    public void setStartDelayInMillis(int delay) {
        this.startDelayInMillis = delay;
    }

    public void setStartWhenCreated(boolean start) {
        this.startWhenCreated = start;
    }

    public void setWithCommas(boolean with) {
        this.withCommas = with;
    }

    public void setTextBeforeTheNumber(String str) {
        this.textBeforeTheNumber = str;
    }

    public void setTextAfterTheNumber(String str) {
        this.textAfterTheNumber = str;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        int finalAnimatedValue = (int)(this.startValue + ((float)((this.endValue - this.startValue) * animatedFraction)));
        if (this.withCommas) {
            this.setText(this.textBeforeTheNumber + this.getNumberInString(finalAnimatedValue) + this.textAfterTheNumber);
        } else {
            this.setText(this.textBeforeTheNumber + finalAnimatedValue + this.textAfterTheNumber);
        }

    }

    private String getNumberInString(int number) {
        if (number <= 999) {
            return number + "";
        } else if (number <= 9999) {
            return (number + "").charAt(0) + "," + (number + "").substring(1);
        } else if (number <= 99999) {
            return (number + "").substring(0, 2) + "," + (number + "").substring(2);
        } else {
            return number <= 999999 ? (number + "").substring(0, 3) + "," + (number + "").substring(3) : (number + "").charAt(0) + "," + (number + "").substring(1, 4) + "," + (number + "").substring(4);
        }
    }
}
