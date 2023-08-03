package com.example.s6final

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s6final.databinding.FragmentFirstBinding
import com.example.s6final.viewModel.PhoneViewModel

class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding
    private val mViewModel : PhoneViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBEMOS INTANCIAR ADAPTER

        val adapter = PhoneListAdapter()
        mBinding.listaRecyclerView.adapter=adapter //tomamos el id del recyclerView
        mBinding.listaRecyclerView.layoutManager= LinearLayoutManager(context)

//        val recyclerView: RecyclerView = view.findViewById(R.id.listaRecyclerView)
//        recyclerView.layoutManager = GridLayoutManager(this.requireContext(),2) //PARA LAS COLUMNAS


        mViewModel.getPhoneList().observe(viewLifecycleOwner, Observer { //del viewModel obtenemos el listado de elementos (getPhoneList())
            it?.let {
                adapter.update(it)
            }
        })

        // MÉTODO PARA SELECCIONAR

        adapter.selectedPhone().observe(viewLifecycleOwner,Observer{ //selectedPhone viene del adapter
            it?.let {
                //validar si capta la seleccion
                Log.d("seleccion", it.id.toString())
            }
            val bundle = Bundle().apply {
                putString("phoneId",it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mBinding = null
    }
}