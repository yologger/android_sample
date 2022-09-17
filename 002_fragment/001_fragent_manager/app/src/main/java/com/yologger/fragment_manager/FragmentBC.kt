package com.yologger.fragment_manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentBC : Fragment() {

    lateinit var buttonA: Button
    lateinit var buttonBB: Button
    lateinit var buttonBCC: Button
    lateinit var buttonBCD: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rootView = requireView()
        buttonA = rootView.findViewById(R.id.fragment_b_c_btn_a)
        buttonBB = rootView.findViewById(R.id.fragment_b_c_btn_bb)
        buttonBCC = rootView.findViewById(R.id.fragment_b_c_bcc)
        buttonBCD = rootView.findViewById(R.id.fragment_b_c_bcd)

        buttonA.setOnClickListener {
            // val fragmentA = FragmentA()
            // fragmentManager?.beginTransaction()?.replace(R.id.activity_main_fl, fragmentA)?.commit()
//            val fragmentBCC = FragmentBCC()
//            fragmentManager?.beginTransaction()?.replace(R.id.fragment_b_fl, )
//            childFragmentManager.beginTransaction()
        }

        buttonBB.setOnClickListener {
            val fragmentBB = FragmentBB()
            parentFragmentManager.beginTransaction().replace(R.id.fragment_b_fl, fragmentBB).commit()
        }

        buttonBCC.setOnClickListener {
            val fragmentBCC = FragmentBCC()
            childFragmentManager.beginTransaction().replace(R.id.fragment_b_c_fl, fragmentBCC).commit()
        }

        buttonBCD.setOnClickListener {
            val fragmentBCD = FragmentBCD()
            childFragmentManager.beginTransaction().replace(R.id.fragment_b_c_fl, fragmentBCD).commit()
        }
    }
}