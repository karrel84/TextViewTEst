package com.karrel.textviewtest

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val text = "asdfasdfasdfasdfasdfasd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text2.text = ", 20"
        text3.text = ", 20"

        text1.text = text
        text1.viewTreeObserver.addOnGlobalLayoutListener (object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {


                text1.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val viewWidth = text1.width
                val realWidth = getTextWidth()

                println("MainActivity > viewTreeObserver > viewWidth : ${viewWidth}, realWidth : $realWidth")

                if(viewWidth > realWidth ) {

                    val lp = text3.layoutParams as ConstraintLayout.LayoutParams
                    lp.marginStart = realWidth
                    text3.layoutParams = lp
                    text3.isVisible = true
                }else{
                    text2.isVisible = true
                }
            }

        })
    }

    private fun getTextWidth(): Int {
        val bounds = Rect()
        text1.paint.getTextBounds(text, 0, text.length, bounds)

        return bounds.width()
    }
}
