package com.soreal.engine

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView

class SorealEditorOverlay(context: Context) : LinearLayout(context) {
    private val accent = Color.rgb(210, 220, 235)
    private val panel = Color.argb(225, 22, 25, 32)

    init {
        orientation = VERTICAL
        setPadding(dp(8), dp(8), dp(8), dp(10))
        isClickable = false

        val top = LinearLayout(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(8), 0, dp(4), 0)
            background = rounded(panel, 14)
        }
        top.addView(label("SOREAL", 14f, accent, true).apply {
            layoutParams = LayoutParams(0, dp(42), 1f)
        })
        top.addView(iconButton("＋", "Add"))
        top.addView(iconButton("↥", "Import"))
        top.addView(iconButton("⋮", "More"))
        addView(top, LayoutParams(-1, dp(42)).apply { bottomMargin = dp(6) })

        addView(Space(context), LayoutParams(-1, 0, 1f))

        val bottom = LinearLayout(context).apply {
            gravity = Gravity.CENTER
            setPadding(dp(4), dp(4), dp(4), dp(4))
            background = rounded(panel, 16)
        }
        val tools = listOf("Select", "Move", "Rotate", "Scale", "Animate", "Physics")
        val symbols = listOf("•", "↕", "⟳", "□", "◆", "◈")
        tools.forEachIndexed { i, name ->
            bottom.addView(
                iconButton(symbols[i], name),
                LayoutParams(0, dp(52), 1f)
            )
        }
        addView(bottom, LayoutParams(-1, dp(60)))
    }

    private fun iconButton(symbol: String, description: String): TextView =
        TextView(context).apply {
            text = symbol
            textSize = 20f
            setTextColor(accent)
            gravity = Gravity.CENTER
            contentDescription = description
            isClickable = true
            setOnClickListener { }
            background = rounded(Color.TRANSPARENT, 12)
            layoutParams = LayoutParams(dp(48), dp(48))
        }

    private fun label(text: String, size: Float, color: Int, bold: Boolean) =
        TextView(context).apply {
            this.text = text
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER_VERTICAL
            if (bold) typeface = android.graphics.Typeface.DEFAULT_BOLD
        }

    private fun rounded(color: Int, radius: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(radius).toFloat()
    }

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()
}
