package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extentions.tryLoadImage
import br.com.alura.orgs.model.Product
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Product>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Product) {
            val nome = binding.produtoItemNome
            nome.text = produto.name
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.description
            val valor = binding.produtoItemValor
            val formatador: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "BR"))
            val valorFormatado: String = formatador.format(produto.value)
            valor.text = valorFormatado

           val viewVisibility = if(produto.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = viewVisibility

            binding.imageView.tryLoadImage(produto.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Product>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
