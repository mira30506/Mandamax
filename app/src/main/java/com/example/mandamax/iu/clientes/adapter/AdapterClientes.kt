package com.example.mandamax.iu.clientes.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mandamax.R
import com.example.mandamax.data.models.AbonoActual
import com.example.mandamax.databinding.AbonoItemBinding

class AdapterClientes(private val dataset: List<AbonoActual>) : RecyclerView.Adapter<AdapterClientes.ViewHolder>() {
    var abonoItemBinding:AbonoItemBinding?=null;

    class ViewHolder(abonoItemBinding: AbonoItemBinding): RecyclerView.ViewHolder(abonoItemBinding.root){
        var  binding:AbonoItemBinding;
        init {
            binding=abonoItemBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        abonoItemBinding = AbonoItemBinding.bind(LayoutInflater.from(parent.context)
                .inflate(R.layout.abono_item, parent, false))
        return ViewHolder(abonoItemBinding!!)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.modelo=dataset[position]
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    }