package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extentions.tryLoadImage
import br.com.alura.orgs.model.Product

class ListaProdutosAdapter(
    private val context: Context,
    products: List<Product>,
    var onItemClick: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    onItemClick(product)
                }
            }
        }

        fun vincula(product: Product) {
            this.product = product
            val nome = binding.produtoItemNome
            nome.text = product.name
            val descricao = binding.produtoItemDescricao
            descricao.text = product.description
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = product.value
                .formataParaMoedaBrasileira()
            valor.text = valorEmMoeda

            val visibilidade = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tryLoadImage(product.image)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = products[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = products.size

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}