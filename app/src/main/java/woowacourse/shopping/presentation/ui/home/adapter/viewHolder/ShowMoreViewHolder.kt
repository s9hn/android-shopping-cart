package woowacourse.shopping.presentation.ui.home.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.shopping.databinding.ItemShowMoreBinding

class ShowMoreViewHolder(
    private val binding: ItemShowMoreBinding,
    private val clickProduct: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            clickProduct()
        }
    }

    fun bind() {}

    companion object {
        fun getView(parent: ViewGroup): ItemShowMoreBinding {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ItemShowMoreBinding.inflate(layoutInflater, parent, false)
        }
    }
}
