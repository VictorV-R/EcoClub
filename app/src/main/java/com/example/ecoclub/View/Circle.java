package com.example.ecoclub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Circle extends View {

    private Paint pincelCirculo;
    private Color color;

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.pincelCirculo = new Paint();
        this.color = Color.valueOf(Color.rgb(239, 205, 140));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pincelCirculo.setColor(color.toArgb());
        //recibiendo el ancho y alto
        int ancho = getWidth();
        int alto = getHeight();
        canvas.drawCircle(ancho/2,alto/2,ancho/2, pincelCirculo);
    }
}
