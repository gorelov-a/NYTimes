package com.gorelov.anton.nytimes.common

import android.support.annotation.StringRes
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    override fun showToast(@StringRes stringId: Int) = Toast.makeText(baseContext, stringId, Toast.LENGTH_LONG).show()

}