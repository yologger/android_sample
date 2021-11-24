package com.example.navigation_component.transition.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigation_component.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    val args: SecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("YOLO", "SecondFragment: onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("YOLO", "SecondFragment: onCreateView()")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("YOLO", "${args.name}")

        fragment_second_btn_third.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thridFragment)
        }

        fragment_second_btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onPause() {
        Log.d("YOLO", "SecondFragment: onPause()")
        super.onPause()
    }

    override fun onResume() {
        Log.d("YOLO", "SecondFragment: onResume()")
        super.onResume()
    }

    override fun onDestroyView() {
        Log.d("YOLO","SecondFragment: onDestroyView()")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("YOLO","SecondFragment: onDestroy()")
        super.onDestroy()
    }
}