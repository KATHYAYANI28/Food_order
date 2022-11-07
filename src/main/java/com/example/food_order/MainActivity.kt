package com.example.food_order

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var itemsRV:RecyclerView
    lateinit var addFAB:FloatingActionButton
    lateinit var list: List<FoodItems>
    lateinit var foodRVAdapter:FoodRVAdapter
    lateinit var foodViewModel: FoodViewModel
    lateinit var foodItems:FoodItems
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV=findViewById(R.id.idRVItems)
        addFAB=findViewById(R.id.idFABAdd)
        list=ArrayList<FoodItems>()
        foodRVAdapter= FoodRVAdapter(list,this)

        itemsRV.layoutManager=LinearLayoutManager(this)
        itemsRV.adapter=foodRVAdapter
        val foodRepository=FoodRepository(FoodDatabase(this))
        val factory=FoodViewModelFactory(foodRepository)
        foodViewModel=ViewModelProvider(this,factory).get(FoodViewModel::class.java)
        foodViewModel.getAllFoodItems().observe(this,{
            foodRVAdapter.list=it
            foodRVAdapter.notifyDataSetChanged()
        })
addFAB.setOnClickListener{
    openDialog()
}

    }
    fun openDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.food_add_dialog)
        val cancelBtn=dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn=dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt=dialog.findViewById<EditText>(R.id.idEdtItemName)
        val itemPriceEdt=dialog.findViewById<EditText>(R.id.idEdtItemPrice)
        val itemQuantityEdt=dialog.findViewById<EditText>(R.id.idEdtItemQuantity)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener{
            val itemName:String=itemEdt.text.toString()
            val itemPrice:String=itemPriceEdt.text.toString()
            val itemQuantity:String=itemQuantityEdt.text.toString()
            val qty:Int=itemQuantity.toInt()
            val pr:Int=itemPrice.toInt()
            if (itemName.isNotEmpty()&& itemPrice.isNotEmpty()&& itemQuantity.isNotEmpty()){
                val items=FoodItems(itemName,qty,pr)
                foodViewModel.insert(items)
                Toast.makeText(applicationContext,"Item inserted",Toast.LENGTH_SHORT).show()
                foodRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else{
                Toast.makeText(applicationContext,"Please enter all the data",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
     fun onItemClick(foodItems: FoodItems){

        foodViewModel.delete(foodItems)
        foodRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_SHORT).show()

    }

}