package com.example.m2_layout.customViewGroup

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.m2_layout.R
import com.example.m2_layout.databinding.BogdanCustomViewgroupBinding

class BogdanCustomView(context: Context,attrs:AttributeSet):RelativeLayout(context,attrs) {
        private val binding:BogdanCustomViewgroupBinding
    init {
      val view =  inflate(context, R.layout.bogdan_custom_viewgroup,this)
        binding = BogdanCustomViewgroupBinding.bind(view)
    }


    fun setUpperText(str:String){
        binding.upperText.text = str
    }
    fun setLowerText(str:String){
        binding.lowerText.text = str
    }

}