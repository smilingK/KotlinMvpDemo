package com.chunze.baselibrary.utils

import android.text.InputFilter
import android.widget.EditText
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException


/**
 * Created by Administrator on 2018/3/1.
 */
object StringUtils {

    /**
     * 验证手机号码格式
     *
     * @return
     */
    fun isMobile(mobile: String): Boolean {
        val p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")
        val m = p.matcher(mobile)
        return m.matches()
    }

    /**
     * 验证是否是邮箱格式
     *
     * @return
     */
    fun isEmail(email: String): Boolean {
        val str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
        val p = Pattern.compile(str)
        val m = p.matcher(email)
        return m.matches()
    }



    /**
     * 禁止EditText输入空格和特殊字符
     *
     * @param editText
     */
    fun filterEditTextInhibitInput(editText: EditText) {
        val filter = InputFilter { source, _, _, _, _, _ ->
            when {
                " " == source -> ""
                isChinese(source.toString()) -> ""
                else -> stringFilter(source.toString())
            }
        }
        editText.filters = arrayOf(filter)
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true, 否则返回false
     */
    private fun isChinese(str: String): Boolean {
        val pattern = Pattern.compile("[\u0391-\uFFE5]+$")
        return pattern.matcher(str).matches()
    }

    /**
     * 设置过滤字符函数(过滤掉不需要的字符)
     */
    @Throws(PatternSyntaxException::class)
    private fun stringFilter(str: String): String {

        val regEx = "[/\\:*?<>|\"\n\t]"

        val p = Pattern.compile(regEx)

        val m = p.matcher(str)

        return m.replaceAll("")

    }


}