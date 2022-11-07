package com.example.food_order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodRVAdapter(
    var list:List<FoodItems>,
    val foodItemsClickInterface: MainActivity
        ):RecyclerView.Adapter<FoodRVAdapter.FoodViewHolder>() {
    inner class FoodViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val nameTV=itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV=itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV=itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV=itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV=itemView.findViewById<ImageView>(R.id.idTVDelete)


    }
    interface FoodItemsClickInterface {
        fun onItemClick(foodItems: FoodItems)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
       val view=
           LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameTV.text=list.get(position).itemName
        holder.quantityTV.text=list.get(position).itemQuantity.toString()
        holder.rateTV.text="Rs."+list.get(position).itemPrice.toString()
        val itemTotal:Int=list.get(position).itemPrice*list.get(position).itemQuantity
        holder.amountTV.text="Rs."+itemTotal.toString()
        holder.deleteTV.setOnClickListener{
            foodItemsClickInterface.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return list.size
    }
}








