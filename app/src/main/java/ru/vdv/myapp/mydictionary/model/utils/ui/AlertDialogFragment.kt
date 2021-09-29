package ru.vdv.myapp.mydictionary.model.utils.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class AlertDialogFragment : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        var alertDialog = getStubAlertDialog(context!!)
        val args = arguments
        if (args != null) {
            val title = args.getString(TITLE_EXTRA)
            val message = args.getString(MESSAGE_EXTRA)
            alertDialog = getAlertDialog(context, title, message)
        }
        return alertDialog
    }

    companion object {

        private const val TITLE_EXTRA = "AlertDialogFragmentTitle"
        private const val MESSAGE_EXTRA = "AlertDialogFragmentMessage"
        const val TAG = "AlertDialogFragment"

        fun newInstance(title: String?, message: String?): AlertDialogFragment {
            val dialogFragment = AlertDialogFragment()
            val args = Bundle()
            args.putString(TITLE_EXTRA, title)
            args.putString(MESSAGE_EXTRA, message)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}