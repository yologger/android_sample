package com.yologger.fragment_lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentA : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TEST", "[FragmentA] onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST", "[FragmentA] onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("TEST", "[FragmentA] onCreateView()")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TEST", "[FragmentA] onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "[FragmentA] onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "[FragmentA] onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "[FragmentA] onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TEST", "[FragmentA] onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TEST", "[FragmentA] onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "[FragmentA] onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("TEST", "[FragmentA] onDetach()")
    }
}