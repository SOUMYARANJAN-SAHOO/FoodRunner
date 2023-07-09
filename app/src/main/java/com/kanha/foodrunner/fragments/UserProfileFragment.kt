package com.kanha.foodrunner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kanha.foodrunner.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {

    private lateinit var binding : FragmentUserProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserProfileBinding.bind(view)


    }
}