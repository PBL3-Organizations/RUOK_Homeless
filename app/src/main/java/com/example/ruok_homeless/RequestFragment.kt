package com.example.ruok_homeless

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class RequestFragment : Fragment() {
    private lateinit var btnWrite: Button
    private lateinit var btnSave: Button
    private lateinit var editText: EditText
    private lateinit var listView: ListView

    private val requestList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request, container, false)

        // 뷰 요소 초기화
        btnWrite = view.findViewById(R.id.btn_write)
        btnSave = view.findViewById(R.id.btn_save)
        editText = view.findViewById(R.id.edit_text)
        listView = view.findViewById(R.id.list_view)

        // 버튼 클릭 리스너 설정
        btnWrite.setOnClickListener { onWriteClicked() }
        btnSave.setOnClickListener { onSaveClicked() }

        // 리스트뷰에 어댑터 설정
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, requestList)
        listView.adapter = adapter

        return view
    }

    private fun onWriteClicked() {
        // 작성하기 버튼 클릭 시 처리할 로직 추가
    }

    private fun onSaveClicked() {
        val requestText = editText.text.toString().trim()
        if (requestText.isNotEmpty()) {
            val currentTime = System.currentTimeMillis()
            val formattedTime = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", currentTime)
            val requestWithTime = "$formattedTime - $requestText"
            requestList.add(requestWithTime)

            // 어댑터에 데이터 변경을 알림
            (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()

            // 입력란 초기화
            editText.text.clear()
        } else {
            Toast.makeText(requireContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show()
        }
    }
}
