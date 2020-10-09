package com.unlam.powereye.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import androidx.appcompat.app.AlertDialog.Builder
import com.unlam.powereye.R

class DialogUtil {
    companion object {
        fun mostrarConfirmacion(
            context: Context,
            title: Int,
            message: Int,
            si: (dialogInterface: DialogInterface, value: Int) -> Unit,
            no: (dialogInterface: DialogInterface, value: Int) -> Unit
        ) {
            Builder(context).setTitle(context.getString(title))
                .setMessage(context.getString(message))
                .setPositiveButton(context.getString(R.string.si).toUpperCase(), si)
                .setNegativeButton(context.getString(R.string.no).toUpperCase(), no).create().show()
        }

        fun mostrarConfirmacion(
            context: Context,
            title: Int,
            message: String,
            si: (dialogInterface: DialogInterface, value: Int) -> Unit,
            no: (dialogInterface: DialogInterface, value: Int) -> Unit
        ) {
            Builder(context).setTitle(context.getString(title))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.si).toUpperCase(), si)
                .setNegativeButton(context.getString(R.string.no).toUpperCase(), no).create().show()
        }

        fun crearDialogoProcesando(context: Context): Dialog {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_procesando)
            return dialog
        }

        fun mostrarMensaje(
            context: Context,
            title: Int,
            message: String,
            cancelable: Boolean,
            aceptar: (dialogInterface: DialogInterface, value: Int) -> Unit
        ) {
            Builder(context).setTitle(context.getString(title)).setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(context.getString(R.string.aceptar).toUpperCase(), aceptar)
                .create().show()
        }

    }
}