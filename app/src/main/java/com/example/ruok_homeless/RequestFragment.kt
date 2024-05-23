package com.example.ruok_homeless

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ruok_homeless.databinding.FragmentRequestBinding

class RequestFragment : Fragment() {
    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    private val requestList = mutableListOf<String>()
    private var selectedItemIndex: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        val view = binding.root

        // 커스텀 어댑터 설정
        val adapter = RequestAdapter(requireContext(), android.R.layout.simple_list_item_1, requestList)
        binding.listView.adapter = adapter

        // 리스트뷰 항목 클릭 리스너 설정
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            onListItemClicked(position)
        }

        // 버튼 클릭 리스너 설정
        binding.btnSave.setOnClickListener { onSaveClicked() }
        binding.btnEdit.setOnClickListener { onEditClicked() }

        return view
    }

    private fun onSaveClicked() {
        val requestText = binding.editText.text.toString().trim()
        if (requestText.isNotEmpty()) {
            val currentTime = System.currentTimeMillis()
            val formattedTime = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", currentTime)
            val requestWithTime = "$formattedTime - $requestText"

            if (selectedItemIndex != null) {
                // 항목 수정
                requestList[selectedItemIndex!!] = requestWithTime
                selectedItemIndex = null
                binding.btnEdit.visibility = View.GONE // 수정 완료 후 버튼 숨김
            } else {
                // 새 항목 추가
                requestList.add(requestWithTime)
            }

            // 어댑터에 데이터 변경을 알림
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()

            // 입력란 초기화
            binding.editText.text.clear()
        } else {
            Toast.makeText(requireContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onEditClicked() {
        val requestText = binding.editText.text.toString().trim()
        if (selectedItemIndex != null && requestText.isNotEmpty()) {
            val currentTime = System.currentTimeMillis()
            val formattedTime = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", currentTime)
            val requestWithTime = "$formattedTime - $requestText"
            requestList[selectedItemIndex!!] = requestWithTime

            // 어댑터에 데이터 변경을 알림
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()

            // 입력란 초기화 및 상태 초기화
            binding.editText.text.clear()
            selectedItemIndex = null
            binding.btnEdit.visibility = View.GONE
        } else {
            Toast.makeText(requireContext(), "수정할 내용을 입력하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onListItemClicked(position: Int) {
        val itemText = requestList[position]
        val contentStartIndex = itemText.indexOf(" - ") + 3
        val content = itemText.substring(contentStartIndex)
        binding.editText.setText(content)
        selectedItemIndex = position
        binding.btnEdit.visibility = View.VISIBLE // 수정 버튼 보이기

        // 어댑터에 데이터 변경을 알림
        (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class RequestAdapter(context: Context, resource: Int, objects: MutableList<String>) :
        ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent) as TextView
            if (position == selectedItemIndex) {
                view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
            } else {
                view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
            return view
        }
    }
}
