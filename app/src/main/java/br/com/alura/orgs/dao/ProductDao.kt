package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Product
import java.math.BigDecimal

class ProductDAO {

    fun addProduct(product: Product){
        products.add(product)
    }

    fun getAll() : List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                "Salada de Frutas",
                "Com frutas frescas",
                BigDecimal(20),
                "https://images.pexels.com/photos/1132047/pexels-photo-1132047.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            )
        )
    }
}