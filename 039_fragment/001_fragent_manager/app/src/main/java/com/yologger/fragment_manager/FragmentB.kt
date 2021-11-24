package com.yologger.fragment_manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentB : Fragment() {

    lateinit var buttonFragmentA: Button
    lateinit var buttonFragmentBB: Button
    lateinit var buttonFragmentBC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rootView = requireView()
        buttonFragmentA = rootView.findViewById(R.id.fragment_b_btn_fragment_a)
        buttonFragmentBB = rootView.findViewById(R.id.fragment_b_btn_fragment_bb)
        buttonFragmentBC = rootView.findViewById(R.id.fragment_b_btn_fragment_bc)

        buttonFragmentA.setOnClickListener {
            val fragmentA = FragmentA()
            // fragmentManager?.beginTransaction()?.replace(R.id.activity_main_fl, fragmentA)?.commit()
            parentFragmentManager.beginTransaction().replace(R.id.activity_main_fl, fragmentA).commit()
        }

        buttonFragmentBB.setOnClickListener {
            val fragmentBB = FragmentBB()
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_b_fl, fragmentBB)
                .commit()
        }
        buttonFragmentBC.setOnClickListener {
            val fragmentBC = FragmentBC()
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_b_fl, fragmentBC)
                .commit()

        }
    }
}