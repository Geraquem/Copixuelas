package com.mmfsin.copixuelas.presentation.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.databinding.ItemCategoryBinding
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.presentation.category.interfaces.ICategoryListener

class CategoryAdapter(
    private val categories: List<Category>,
    private val listener: ICategoryListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)
        private val c = binding.root.context

        fun bind(category: Category) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener { listener.onCategoryClick(category.type) }
        holder.itemView.setOnLongClickListener {
            listener.onCategoryLongClick(category.type)
            true
        }
    }

    override fun getItemCount(): Int = categories.size
}