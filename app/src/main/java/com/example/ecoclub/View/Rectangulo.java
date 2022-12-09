package com.example.ecoclub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Rectangulo extends View {

    //private Paint color;

    public Rectangulo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //this.color = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(239, 205, 140);
        /*
        color.setColor(Color.GRAY);
        //recibiendo el ancho y alto
        int ancho = getWidth();
        int alto = getHeight();

        canvas.drawRect(0,0,ancho,alto, color);
        */
    }
}
