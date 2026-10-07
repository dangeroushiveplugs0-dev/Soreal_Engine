package com.soreal.engine
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import android.widget.Toast

class SorealEditorOverlay(context:Context, private val onImport:()->Unit):LinearLayout(context){
 private val accent=Color.rgb(210,220,235)
 private val selected=Color.rgb(55,65,82)
 private val panel=Color.argb(225,22,25,32)
 private val toolButtons=mutableListOf<TextView>()
 private var selectedTool=-1
 init{
  orientation=VERTICAL;setPadding(dp(8),dp(8),dp(8),dp(10));isClickable=false
  val top=LinearLayout(context).apply{gravity=Gravity.CENTER_VERTICAL;setPadding(dp(8),0,dp(4),0);background=rounded(panel,14)}
  top.addView(label("SOREAL",14f,accent,true).apply{layoutParams=LayoutParams(0,dp(42),1f)})
  top.addView(iconButton("＋","Add"))
  top.addView(iconButton("↥","Import").apply{setOnClickListener{onImport()}})
  top.addView(iconButton("⋮","More"))
  addView(top,LayoutParams(-1,dp(42)).apply{bottomMargin=dp(6)})
  addView(Space(context),LayoutParams(-1,0,1f))
  val bottom=LinearLayout(context).apply{gravity=Gravity.CENTER;setPadding(dp(4),dp(4),dp(4),dp(4));background=rounded(panel,16)}
  val tools=listOf("Select","Move","Rotate","Scale","Animate","Physics")
  val symbols=listOf("•","↕","⟳","□","◆","◈")
  tools.forEachIndexed{i,name->
   val button=toolButton(symbols[i],name,i)
   toolButtons.add(button)
   bottom.addView(button,LayoutParams(0,dp(52),1f))
  }
  addView(bottom,LayoutParams(-1,dp(60)))
 }
 private fun toolButton(symbol:String,description:String,index:Int)=TextView(context).apply{
  text=symbol;textSize=20f;setTextColor(accent);gravity=Gravity.CENTER;contentDescription=description
  isClickable=true;background=rounded(Color.TRANSPARENT,12)
  setOnClickListener{
   if(selectedTool==index){
    selectedTool=-1
    background=rounded(Color.TRANSPARENT,12)
    Toast.makeText(context,description+" unselected",Toast.LENGTH_SHORT).show()
   }else{
    if(selectedTool>=0)toolButtons[selectedTool].background=rounded(Color.TRANSPARENT,12)
    selectedTool=index
    background=rounded(selected,12)
    Toast.makeText(context,description,Toast.LENGTH_SHORT).show()
   }
  }
 }
 private fun iconButton(symbol:String,description:String)=TextView(context).apply{
  text=symbol;textSize=20f;setTextColor(accent);gravity=Gravity.CENTER;contentDescription=description
  isClickable=true;background=rounded(Color.TRANSPARENT,12)
 }
 private fun label(text:String,size:Float,color:Int,bold:Boolean)=TextView(context).apply{
  this.text=text;textSize=size;setTextColor(color);gravity=Gravity.CENTER_VERTICAL
  if(bold)typeface=android.graphics.Typeface.DEFAULT_BOLD
 }
 private fun rounded(color:Int,radius:Int)=GradientDrawable().apply{setColor(color);cornerRadius=dp(radius).toFloat()}
 private fun dp(v:Int)=(v*resources.displayMetrics.density).toInt()
}