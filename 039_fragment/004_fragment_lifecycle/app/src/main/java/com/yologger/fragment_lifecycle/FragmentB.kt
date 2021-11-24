package com.yologger.fragment_lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentB : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST", "[FragmentB] onAttach()")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST", "[FragmentB] onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("TEST", "[FragmentB] onCreateView()")
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST", "[FragmentB] onViewCreated()")
        val textView = requireView().findViewById<TextView>(R.id.fragment_b_tv)
        textView.setText("Hello World")

        var supportFragmentManager = fragmentManager
        var childFragmentMgr = childFragmentManager
        var parentFragmentMgr = parentFragmentManager
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "[FragmentB] onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "[FragmentB] onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "[FragmentB] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "[FragmentB] onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TEST", "[FragmentB] onDestroyView()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("TEST", "[FragmentB] onDetach()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "[FragmentB] onDestroy()")
    }
}