package ru.juraogurcov.multitool.ui.vpn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.databinding.FragmentVPNBinding

class VPN : Fragment() {

    private var _binding: FragmentVPNBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val VPNViewModel =
            ViewModelProvider(this).get(VPNViewModel::class.java)

        _binding = FragmentVPNBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.VPNText
        VPNViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}