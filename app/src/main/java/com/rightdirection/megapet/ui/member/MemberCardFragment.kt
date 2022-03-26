package com.rightdirection.megapet.ui.member

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.MemberCardFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MemberCardFragment : Fragment() {



    val viewModel : MemberCardViewModel by viewModels()
    private val myAccountViewModel: MyAccountViewModel by viewModels()
    private var _binding: MemberCardFragmentBinding? = null

    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mHandler = Handler(Looper.getMainLooper())
    private var isHandlerRunning:Boolean = false

    private var qrString = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MemberCardFragmentBinding.inflate(inflater,container,false)

        binding.progressBar.visibility = View.VISIBLE

        viewModel.isLogin.observe(viewLifecycleOwner, { haveToken->
            if (!haveToken){
                navController.navigate(R.id.action_navigation_memberCardFragment_to_navigation_memberDashboard)
            }else{
                myAccountViewModel.memberResponse.observe(viewLifecycleOwner, { memberResponse->
                    if (memberResponse.isSuccessful){
                        binding.accountInfo = memberResponse.body()
                        if (binding.accountInfo!!.is_phone_valid=="0"){
                            navController.navigate(R.id.navigation_editAccountInfoFragment)
                        }
                    }else{
                        Log.e("Error:",memberResponse.code().toString())
                        Log.e("Error:",memberResponse.body().toString())
                    }
                    //binding.progressBar.visibility = View.INVISIBLE
                })
            }

        })


        myAccountViewModel.qrStringResponse.observe(viewLifecycleOwner,{
            if(it.isSuccessful){
                qrString = it.body()!!.qrString
                refreshQRCode()
            }else{
                Log.e("Error:",it.code().toString())
                Log.e("Error:",it.body().toString())
            }
            binding.progressBar.visibility = View.INVISIBLE
        })

        myAccountViewModel.getQRString()

        binding.memberCardQr.setOnClickListener {
            if(!isHandlerRunning){
                binding.progressBar.visibility = View.VISIBLE
                myAccountViewModel.getQRString()
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }


    override fun onDestroyView() {

        /* */
        mHandler.removeCallbacksAndMessages(null)
        isHandlerRunning = false
//        Log.d("mHandlerRunning","false")
        /* */

        super.onDestroyView()
        _binding = null
    }


    private fun refreshQRCode(){

        //sent https request to server

        val delay:Long = 1000  //1000 milliseconds = 1 sec
        //binding.memberCardQr.setImageResource(R.drawable.qr_megapet_website)
        if(qrString.isEmpty()){
            return
        }
        binding.testQrcode.text = qrString
        binding.memberCardQr.setImageBitmap(getQrCodeBitmap(qrString))
        isHandlerRunning = true
//        Log.d("mHandlerRunning","true")
        var time=1800
        mHandler.post(object : Runnable {
            override fun run() {
                if (time>=0){
                    val remainingTime = time.toString()
                    binding.countDownHint.text = String.format(resources.getString(R.string.member_card_count_down_hint),remainingTime)
                    time -= 1
                    //Log.d("Count Down:",t.toString())
                    mHandler.postDelayed(this, delay)
                }
                if (time<0){
                    binding.countDownHint.text = resources.getString(R.string.member_card_count_down_expire_hint)
                    binding.memberCardQr.setImageResource(R.drawable.refresh)
                    isHandlerRunning = false
                    time -= 1
                    Log.d("mHandlerRunning","false")
                }

            }

        })

    }

    private fun getQrCodeBitmap(QRString:String): Bitmap {
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val size = 384 //pixels
        val bits = QRCodeWriter().encode(QRString, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

}