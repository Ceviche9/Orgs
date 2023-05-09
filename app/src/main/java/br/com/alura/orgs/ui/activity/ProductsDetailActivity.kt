package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityProductDetailBinding
import br.com.alura.orgs.extentions.tryLoadImage
import br.com.alura.orgs.model.Product

class ProductsDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getProduct()
    }

    private fun getProduct() {
        intent.getParcelableExtra<Product>(PRODUCT_KEY)?.let {
            productSelected -> loadProductData(productSelected)
        } ?: finish()
    }

    private fun loadProductData(product: Product) {
        with(binding) {
            activityProductDetailsImage.tryLoadImage(product.image)
            activityProductDetailsName.text = product.name
            activityProductDetailsDescription.text = product.description
            activityProductDetailsValue.text = product.value.toString()
        }
    }
}