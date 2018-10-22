package com.gorelov.anton.nytimes.common


import android.content.Context
import android.content.DialogInterface
import android.support.annotation.ArrayRes
import android.support.v7.app.AlertDialog

class DialogBuilder(context: Context) {

    private var dialog: AlertDialog.Builder = android.support.v7.app.AlertDialog.Builder(context);

    fun buildListDialog(@ArrayRes itemsArrayRes: Int,
                        listener: DialogInterface.OnClickListener): AlertDialog {
        return dialog
                .setSingleChoiceItems(itemsArrayRes, 0, listener)
                .create()
    }

}