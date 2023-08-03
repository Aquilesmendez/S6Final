package com.example.s6final

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.s6final.databinding.FragmentSecondBinding
import com.example.s6final.viewModel.PhoneViewModel


class SecondFragment : Fragment() {

    private lateinit var mBinding: FragmentSecondBinding
    private val mViewModel : PhoneViewModel by activityViewModels()
    //OJO va a almacenar el id del Phone
    private var phoneId :String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //validar que está llegando la variable
        arguments?.let { bundle ->

            phoneId = bundle.getString("phoneId")
            Log.d("seleccion2", phoneId.toString())
        }

        phoneId?.let { id ->
            mViewModel.getPhoneDetailByIDFromInternet(id) // esta es la 3ERA FUN del PhoneViewModel
        }

        mViewModel.getPhoneDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3",phoneId.toString())
            var id=it.id
            var name=it.name

            Glide.with(mBinding.ivLogo).load(it.image).into(mBinding.ivLogo)   //conecta el id de la imagen del 2DO FRAGMENTO con la imagen del Entity Details

            mBinding.textName.text = it.name
            mBinding.textPrice.text = "Precio:$" + it.price
            mBinding.textDescription.text = "Description: " + it.description
            mBinding.textLastPrice.text = "Ultimo Precio:$" + it.lastPrice
            mBinding.textCredit.text = "Credito: " + it.credit.toString()

            //correo electronico

            mBinding.btMail.setOnClickListener{
                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@novaera.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por producto :"+name+ "_id"+id
                )

                mintent.putExtra(
                    Intent.EXTRA_TEXT,"Hola\n" +
                            "Vi el producto"+name+"de código,\n"+id+
                            "y me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)
                }catch (e: Exception){
                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }



        })







    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mBinding = null
    }
}