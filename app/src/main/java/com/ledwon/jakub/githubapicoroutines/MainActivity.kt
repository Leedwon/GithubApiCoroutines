package com.ledwon.jakub.githubapicoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.findNavController
import com.ledwon.jakub.githubapicoroutines.common.DeferrableString
import com.ledwon.jakub.githubapicoroutines.view.query.QueryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setToolbarTitle(title: DeferrableString) {
        toolbar.title = title.get(this)
    }
}
