package com.example.app1.ui.slideshow

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app1.R
import com.example.app1.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    var muallif1 = true
    var muallif2 = true
    var muallif3 = true
    var muallif4 = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mu1()
        mu2()
        mu3()
        mu4()
    }

    private fun mu4() {
        binding.muallif4.setOnClickListener {
            if(muallif4){
                muallif4=false
                binding.muallifXaqida4.visibility=View.VISIBLE
            }else{
                muallif4=true
                binding.muallifXaqida4.visibility=View.GONE
            }
        }
    }

    private fun mu3() {
        binding.muallif3.setOnClickListener {
            if(muallif3){
                muallif3=false
                binding.muallifXaqida3.visibility=View.VISIBLE
            }else{
                muallif3=true
                binding.muallifXaqida3.visibility=View.GONE
            }
        }
    }

    private fun mu2() {
        binding.muallif2.setOnClickListener {
            if (muallif2) {
                muallif2 = false
                binding.muallifXaqida2.visibility = View.VISIBLE
            } else {
                muallif2 = true
                binding.muallifXaqida2.visibility = View.GONE
            }
        }
    }

    private fun mu1() {
        binding.muallif1.setOnClickListener {
            if (muallif1) {
                muallif1 = false
                binding.muallifXaqida1.visibility = View.VISIBLE
            } else {
                muallif1 = true
                binding.muallifXaqida1.visibility = View.GONE
            }
        }
    }


    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}