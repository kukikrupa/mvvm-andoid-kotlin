package com.mvvm_structure_demo.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AlertDialog
import com.kaopiz.kprogresshud.KProgressHUD
import com.mvvm_structure_demo.R
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * A utility class which contains methods with all the validation logic of whole app.
 */
object Common {

    var hud: KProgressHUD? = null
    var regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$"
    var pattern: Pattern = Pattern.compile(regex)

    // Request Codes
    const val PICK_IMAGE_GALLERY_REQUEST_CODE = 31
    const val PICK_IMAGE_CAMERA_REQUEST_CODE = 32

    fun showProgress(context: Context) {
        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setBackgroundColor(context.resources.getColor(R.color.design_default_color_primary))
            .setLabel("Please wait")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();
    }

    fun isEmpty(charSeq: String?): Boolean {
        return charSeq == null || charSeq.trim { it <= ' ' } == ""
    }

    fun getHTMLTextContent(charSeq: String?): Spanned? {
        return if (isEmpty(charSeq)) {
            if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml("", Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml("")
            }
        } else {
            if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml(charSeq, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(charSeq)
            }
        }
    }

    fun dismissProgress() {
        if (hud != null) {
            hud!!.dismiss()
        }
    }

    fun hasInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



    fun dateConverter(dateFromServer: String): String {
        var dateWeNeed = ""
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm a")
        val newDate: Date = spf.parse(dateFromServer)
        spf = SimpleDateFormat("dd/MM/yyyy")
        dateWeNeed = spf.format(newDate)
        return dateWeNeed
    }

    fun dateConverterPin(dateFromServer: String): String {
        var dateWeNeed = ""
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate: Date = spf.parse(dateFromServer)
        spf = SimpleDateFormat("dd/MM/yyyy")
        dateWeNeed = spf.format(newDate)
        return dateWeNeed
    }

    fun dateCalaneder(dateFromServer: String): String {
        var dateWeNeed = ""
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm a")
        val newDate: Date = spf.parse(dateFromServer)
        spf = SimpleDateFormat("dd/MM/yyyy")
        dateWeNeed = spf.format(newDate)
        return dateWeNeed
    }

    /**
     * A method to get the external directory of phone, to store the image file
     */
    fun getWorkingDirectory(): File {

        val wallpaperDirectory = File(
            Environment.getExternalStorageDirectory().toString() + "/" + "-----------------"
        )

        return wallpaperDirectory
    }



    fun getParamsRequestBody(params: HashMap<String, String>): HashMap<String, RequestBody> {
        val resultParams = HashMap<String, RequestBody>()

        for ((key, value) in params) {
            val body = RequestBody.create(MediaType.parse("text/plain"), value)
            resultParams.put(key, body)
        }
        return resultParams
    }

    fun isDateDDMMYYYY(date: String): Boolean {
        val matcher: Matcher = pattern.matcher(date)
        return matcher.matches()
    }


    fun getAge(dtStart: String): String? {
        val format = SimpleDateFormat("dd-mm-yyyy")
        try {
            val date = format.parse(dtStart)
            System.out.println("Date ->$date")
            val dob = Calendar.getInstance()
            val today = Calendar.getInstance()
            dob[date.year, date.month] = date.day
            var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
            if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
                age--
            }
            val ageInt = age -1900
            return ageInt.toString()

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return "0"
    }



}