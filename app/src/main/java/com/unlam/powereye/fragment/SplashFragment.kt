package com.unlam.powereye.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.unlam.powereye.R


class SplashFragment : Fragment() {

    lateinit var v: View

    companion object {
        const val SPLASH_TIME_OUT = 2000
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_splash, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            v.findNavController().navigate(
                R.id.action_global_to_login,
                null,
                NavOptions.Builder().setPopUpTo(R.id.fragment_splash, true).build()
            )
        }, SPLASH_TIME_OUT.toLong())
    }

}
