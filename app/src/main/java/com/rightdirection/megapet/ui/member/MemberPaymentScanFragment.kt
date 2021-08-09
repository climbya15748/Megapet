package com.rightdirection.megapet.ui.member

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.rightdirection.megapet.R

import com.rightdirection.megapet.databinding.MemberPaymentScanFragmentBinding

class MemberPaymentScanFragment : Fragment(),View.OnClickListener {


    private lateinit var mMemberPaymentScanViewModel: MemberPaymentScanViewModel
    private var _binding: MemberPaymentScanFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mHandler = Handler(Looper.getMainLooper())
    private var mHandlerRunning:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMemberPaymentScanViewModel = ViewModelProvider(this).get(MemberPaymentScanViewModel::class.java)
        _binding = MemberPaymentScanFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val countDownHint: TextView = binding.countDownHint
        val qrImg = binding.paymentImgQR
        Log.d("tag",qrImg.id.toString())
        mMemberPaymentScanViewModel.getCountDownHint.observe(viewLifecycleOwner, Observer {
            countDownHint.text = it
        })

        timeoutQRCode()
        qrImg.setOnClickListener(this)



        return root
    }


    override fun onDestroyView() {

        /* */
        mHandler.removeCallbacksAndMessages(null)
        mHandlerRunning = false
        Log.d("mHandlerRunning","false")
        /* */

        super.onDestroyView()
        _binding = null
    }


    private fun timeoutQRCode(){

        val delay:Long = 1000  //1000 milliseconds = 1 sec
        mHandlerRunning = true
        Log.d("mHandlerRunning","true")
        var time=5
        mHandler.post(object : Runnable {
            override fun run() {
                if (time>=0){
                    val remainingTime = time.toString()
                    binding.countDownHint.text = "此QR code將於$remainingTime 秒後失效"
                    time -= 1
                    //Log.d("Count Down:",t.toString())
                    mHandler.postDelayed(this, delay)
                }
                if (time<0){
                    binding.countDownHint.text = "此QR code已失效. 請擊點QR code刷新."
                    mHandlerRunning = false
                    time -= 1
                    Log.d("mHandlerRunning","false")
                }

            }

        })


    }

    override fun onClick(v: View?) {
        when (v!!.id){
            binding.paymentImgQR.id->{
                if(!mHandlerRunning){
                    timeoutQRCode()
                }
            }
        }
    }




}