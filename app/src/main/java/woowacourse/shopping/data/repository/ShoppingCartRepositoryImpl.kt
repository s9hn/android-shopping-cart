package woowacourse.shopping.data.repository

import woowacourse.shopping.data.dataSource.local.product.ProductDataSource
import woowacourse.shopping.data.dataSource.local.shoppingCart.ShoppingCartDataSource
import woowacourse.shopping.data.entity.ProductInCartEntity
import woowacourse.shopping.data.mapper.ProductMapper.toDomainModel
import woowacourse.shopping.domain.model.ProductInCart
import woowacourse.shopping.domain.repository.ShoppingCartRepository

class ShoppingCartRepositoryImpl(
    private val shoppingCartDataSource: ShoppingCartDataSource,
    private val productDataSource: ProductDataSource,
) : ShoppingCartRepository {

    override fun getShoppingCart(): List<ProductInCart> {
        return shoppingCartDataSource.getShoppingCart().mapNotNull { entity ->
            entity.toProductInCart()
        }
    }

    override fun getShoppingCartByPage(unit: Int, pageNumber: Int): List<ProductInCart> {
        return shoppingCartDataSource.getShoppingCartByPage(unit, pageNumber)
            .mapNotNull { entity -> entity.toProductInCart() }
    }

    private fun ProductInCartEntity.toProductInCart(): ProductInCart? {
        val product = productDataSource.getProductEntity(productId)?.toDomainModel()
        return product?.let { ProductInCart(it, quantity, true) }
    }

    override fun addProductInCart(productInCart: ProductInCart): Long {
        val productId = productInCart.product.id
        val productQuantity = productInCart.quantity

        return shoppingCartDataSource.addProductInShoppingCart(productId, productQuantity)
    }

    override fun deleteProductInCart(id: Long): Boolean {
        return shoppingCartDataSource.deleteProductInShoppingCart(id)
    }

    override fun getShoppingCartSize(): Int {
        return shoppingCartDataSource.getShoppingCartSize()
    }
}