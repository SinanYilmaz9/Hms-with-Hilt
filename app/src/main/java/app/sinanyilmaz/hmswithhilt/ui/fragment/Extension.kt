package app.sinanyilmaz.hmswithhilt.ui.fragment

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import app.sinanyilmaz.hmswithhilt.R
import com.google.android.material.snackbar.Snackbar


fun showResult(activity: FragmentActivity, view: View, snackText: String) {
    activity.runOnUiThread {
        val snackBar = Snackbar.make(view, snackText, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.BLUE)

        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.purple_700))

        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(activity, R.color.white))
        snackBar.show()
    }
}
