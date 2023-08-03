package com.example.s6final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.s6final.databinding.ArticleItemBinding
import com.example.s6final.modelo.local.entities.PhoneEntity

class PhoneListAdapter : RecyclerView.Adapter<PhoneListAdapter.PhoneVH>() {

    //RECUERDA el adapter esta para conectar el recyclerView con (Article_item.xml)

    private var listPhone = listOf<PhoneEntity>()
    private val SelectedPhone = MutableLiveData<PhoneEntity>()



    fun update(list:List<PhoneEntity>){
        listPhone= list
        notifyDataSetChanged()
    }


    // FUNCION PARA SELECCIONAR

    fun selectedPhone():
            LiveData<PhoneEntity> = SelectedPhone


    inner class  PhoneVH(private val mBinding: ArticleItemBinding): // el binding viene del (article_item.xml) del detalle del recyclerView
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bind(phoneEntity: PhoneEntity){
            Glide.with(mBinding.listImage).load(phoneEntity.image).centerCrop().into(mBinding.listImage)

            mBinding.name.text = phoneEntity.name // NO TE olvides del .TEXT
            mBinding.price.text = "Precio:$" + phoneEntity.price
//           

            itemView.setOnClickListener(this)

        }
        override  fun onClick(v: View){

            SelectedPhone.value= listPhone[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
        return PhoneVH(ArticleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        val phone = listPhone[position]
        holder.bind(phone)
    }


    override fun getItemCount()=
        listPhone.size


}