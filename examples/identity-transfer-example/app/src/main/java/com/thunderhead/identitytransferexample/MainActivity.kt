package com.thunderhead.identitytransferexample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_button_example_identity_transfer.setOnClickListener {
            val uri = activity_main_edittext_example_identity_transfer.text.toString()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
        }
    }
}
