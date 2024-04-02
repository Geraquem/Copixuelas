package com.mmfsin.copixuelas.presentation.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.databinding.ItemCategoryBinding
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import com.mmfsin.copixuelas.presentation.category.interfaces.ICategoryListener

class CategoryAdapter(
    private val categories: List<Category>,
    private val listener: ICategoryListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)
        private val context = binding.root.context
        fun bind(category: Category) {
            binding.apply {
                tvText.text = context.getString(category.title)
                tvText.typeface = getFont(context, R.font.avqp_font)
                image.setImageResource(category.image)
                checkIfHideShadow(category.type)
            }
        }

        private fun checkIfHideShadow(type: CategoryType) {
            val visibility = when (type) {
                MONEDA, QPREFIERES, MALETIN -> View.VISIBLE
                else -> View.GONE
            }
            binding.shadow.visibility = visibility
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
        holder.binding.root.setOnClickListener { listener.onCategoryClick(category.type) }
        holder.binding.root.setOnLongClickListener {
            listener.onCategoryLongClick(category.type)
            true
        }
    }

    override fun getItemCount(): Int = categories.size
}