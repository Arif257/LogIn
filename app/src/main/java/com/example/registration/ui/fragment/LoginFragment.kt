package com.example.registration.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.registration.R
import com.example.registration.data.SharedPref
import com.example.registration.model.request.SignInReq
import com.example.registration.model.response.SignInRes
import com.example.registration.ui.viewmodel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var loginFragmentViewModel: LoginFragmentViewModel
    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // sharedPref = SharedPref()
        sharedPref.languageSet(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        init(rootView)
        return rootView
    }

    private fun init(rootView: View) {

        loginFragmentViewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
        rootView.btnLogin.setOnClickListener(View.OnClickListener {
            login(rootView)
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {}
            }
    }


    private fun login(rootView: View) {
        with(loginFragmentViewModel) {
            signIn(
                SignInReq(
                    rootView.email.text.toString(),
                    rootView.password.text.toString()
                )
            ).observe(viewLifecycleOwner) { any ->
                try {
                    if (any is SignInRes) {
                        Toast.makeText(rootView.context, "Login successfully", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(rootView.context, "something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}