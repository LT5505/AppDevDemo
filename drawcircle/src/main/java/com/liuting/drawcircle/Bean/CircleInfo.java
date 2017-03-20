package com.liuting.drawcircle.Bean;

/**
 * Package:com.liuting.drawcircle.Bean
 * author:liuting
 * Date:2017/3/16
 * Desc:实心圆类
 */

public class CircleInfo {
    private float x;//圆心横坐标
    private float y;//圆心纵坐标
    private float radius;//半径
    private int color;//画笔的颜色

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
