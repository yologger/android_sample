package com.example.navigation_component.transition.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation_component.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("YOLO", "FirstFragment: onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("YOLO", "FirstFragment: onCreateView()")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("YOLO", "FirstFragment: onViewCreated()")
        fragment_first_btn_second.setOnClickListener {
            var action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(name = "Ronaldo")
            findNavController().navigate(action)
        }
    }

    override fun onPause() {
        Log.d("YOLO", "FirstFragment: onPause()")
        super.onPause()
    }

    override fun onResume() {
        Log.d("YOLO", "FirstFragment: onResume()")
        super.onResume()
    }

    override fun onDestroyView() {
        Log.d("YOLO", "FirstFragment: onDestroyView()")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("YOLO", "FirstFragment: onDestroy()")
        super.onDestroy()
    }
}