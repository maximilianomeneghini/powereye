package com.unlam.powereye.fragment

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.unlam.powereye.NavRootDirections
import com.unlam.powereye.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    var defaultTypePassword = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.buttonLogin.setOnClickListener {
            binding.root.findNavController().navigate(NavRootDirections.actionGlobalToMain())
        }

        defaultTypePassword = binding.etPassword.inputType

        binding.ibShowPassword.setOnClickListener {
            when(binding.etPassword.inputType) {
                defaultTypePassword -> binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT
                InputType.TYPE_CLASS_TEXT -> binding.etPassword.inputType = defaultTypePassword
            }
        }
        return binding.root
    }


}
