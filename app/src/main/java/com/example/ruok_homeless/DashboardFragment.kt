package com.example.ruok_homeless

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btnDanger = view.findViewById<Button>(R.id.btnDanger)
        val btnMap = view.findViewById<Button>(R.id.btnMap)
        val btnCheckUp = view.findViewById<Button>(R.id.btnCheckUp)

        // 위험지역 알림
        btnDanger.setOnClickListener {
            navigateToFragment(DanferZoneFragment())
        }

        // 지도 보기
        btnMap.setOnClickListener {
            navigateToFragment(WelfareFacilitiesFragment())
        }

        // 문진표 작성하기
        btnCheckUp.setOnClickListener {
            navigateToFragment(QuestionnaireFragment())
        }

        return view
    }

    fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_dashboard, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}