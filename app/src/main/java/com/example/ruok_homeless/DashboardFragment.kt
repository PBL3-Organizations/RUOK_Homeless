package com.example.ruok_homeless

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ruok_homeless.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val btnRequest = view?.findViewById<Button>(R.id.btnRequest)
        val btnMap = view?.findViewById<Button>(R.id.btnMap)

        //btnRequest 클릭시 DashboardFragment에서 ListFragment로 이동
        binding.btnRequest.setOnClickListener{
            val parentActivity = activity as DashboardActivity
            parentActivity.setFragment(ListFragment())
        }

        //btnMap 클릭시 DashboardFragment에서 AccessFragment로 이동
        binding.btnMap.setOnClickListener{
            val parentActivity = activity as DashboardActivity
            parentActivity.setFragment(AccessFragment())
        }

        return binding!!.root
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}