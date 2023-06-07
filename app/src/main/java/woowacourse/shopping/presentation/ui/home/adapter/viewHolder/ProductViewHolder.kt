package woowacourse.shopping.presentation.ui.home.adapter.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.shopping.databinding.ItemProductBinding
import woowacourse.shopping.presentation.ui.home.HomeSetClickListener
import woowacourse.shopping.presentation.ui.home.adapter.HomeAdapter.ProductsByView.Products
import woowacourse.shopping.presentation.ui.shoppingCart.uiModel.ProductInCartUiState

class ProductViewHolder(
    private val binding: ItemProductBinding,
    clickProduct: HomeSetClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener = clickProduct
    }

    fun bind(data: Products, shoppingCart: List<ProductInCartUiState>) {
        binding.product = data.product

        binding.fabItemProductQuantity.setOnClickListener {
            binding.fabItemProductQuantity.visibility = View.INVISIBLE
            binding.layoutQuantity.root.visibility = View.VISIBLE
        }

        binding.shoppingCart = shoppingCart.find { it.product.id == data.product.id }
            ?: ProductInCartUiState(
                product = data.product,
                quantity = 0,
                isChecked = false,
            )
    }

    companion object {
        fun getView(parent: ViewGroup, layoutInflater: LayoutInflater): ItemProductBinding =
            ItemProductBinding.inflate(layoutInflater, parent, false)
    }
}
