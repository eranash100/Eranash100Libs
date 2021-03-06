package com.eranash100.tools;

import android.graphics.Rect;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public class Point
{
    public static final int MATH_OPERATION_ADD = 10;
    public static final int MATH_OPERATION_SUBTRACT = 11;
    public static final int MATH_OPERATION_MULTIPLY = 12;
    public static final int MATH_OPERATION_DIVIDE = 13;
    public static final int MATH_OPERATION_MODULE = 14;
    private static final String STRING_FORMAT_REGEX = ":";

    public int x, y;

    public Point()
    {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point point)
    {
        this.set(point.x, point.y);
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void copyValues(Point otherPoint)
    {
        this.set(otherPoint.x, otherPoint.y);
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public boolean isEquals(Point otherPoint)
    {
        return (this.x == otherPoint.x && this.y == otherPoint.y);
    }

    public Point getCopy()
    {
        return new Point(this);
    }

    public boolean isXInBounds(int leftBound, int rightBound)
    {
        return (this.x >= leftBound && this.x <= rightBound);
    }

    public boolean isYInBounds(int bottomBound, int topBound)
    {
        return (this.y >= bottomBound && this.y <= topBound);
    }

    public boolean isInBounds(Point topLeft, Point bottomRight)
    {
        if(!this.isXInBounds(topLeft.x, bottomRight.x))
            return false;
        if(!this.isYInBounds(topLeft.y, bottomRight.y))
            return false;
        return true;
    }

    public boolean isInRect(Rect rect)
    {
        return this.isInBounds(new Point(rect.left, rect.top), new Point(rect.right, rect.bottom));
    }

    public boolean isIn2DArrayBounds(Object[][] arr)
    {
        if(!this.isXInBounds(0, arr.length - 1))
            return false;
        if(!this.isYInBounds(0, arr[this.x].length - 1))
            return false;
        return true;
    }

    public boolean isInsideCircle(Point circleCenter, int circleRadius)
    {
        return (this.getDistanceTo(circleCenter) <= circleRadius);
    }

    /**
     * √((x1 - x2)^2  +  (y1 - y2)^2)
     */
    public double getDistanceTo(Point target)
    {
        Point difPoint = this.getCopy().mathOperation(target, MATH_OPERATION_SUBTRACT);
        double sum = Math.pow(difPoint.x, 2) + Math.pow(difPoint.y, 2);
        return Math.sqrt(sum);
    }

    public Point mathOperation(Point otherPoint, @IntRange(from = 10, to = 14) int operation)
    {
        switch (operation)
        {
            case MATH_OPERATION_ADD:
                this.x += otherPoint.x;
                this.y += otherPoint.y;
                break;
            case MATH_OPERATION_SUBTRACT:
                this.x -= otherPoint.x;
                this.y -= otherPoint.y;
                break;
            case MATH_OPERATION_MULTIPLY:
                this.x *= otherPoint.x;
                this.y *= otherPoint.y;
                break;
            case MATH_OPERATION_DIVIDE:
                this.x /= otherPoint.x;
                this.y /= otherPoint.y;
                break;
            case MATH_OPERATION_MODULE:
                this.x %= otherPoint.x;
                this.y %= otherPoint.y;
                break;
        }
        return this;
    }

    public String getAsStringFormat()
    {
        return this.x + STRING_FORMAT_REGEX + this.y;
    }

    public void setFromStringFormat(String pointAsStringFormat)
    {
        String[] strPointParts = pointAsStringFormat.split(STRING_FORMAT_REGEX);
        this.set(Integer.parseInt(strPointParts[0]), Integer.parseInt(strPointParts[1]));
    }

    public static Point mathOperation(
            @NonNull Point point1
            , @NonNull Point point2
            , @IntRange(from = 10, to = 13) int operation)
    {
        return point1.getCopy().mathOperation(point2, operation);
    }

    /**
     * √((x1 - x2)^2  +  (y1 - y2)^2)
     */
    public static double getDistance(
            @NonNull Point point1
            , @NonNull Point point2)
    {
        return point1.getDistanceTo(point2);
    }

    public static boolean isPointInsideCircle(Point point, Point circleCenter, int radius)
    {
        return (point.getDistanceTo(circleCenter) <= radius);
    }

    public static Point getFromStringFormat(String pointAsStringFormat)
    {
        String[] strPointParts = pointAsStringFormat.split(STRING_FORMAT_REGEX);
        return new Point(Integer.parseInt(strPointParts[0]), Integer.parseInt(strPointParts[1]));
    }

    public String toString()
    {
        return ("(" + this.x + "," + this.y + ")");
    }
}

