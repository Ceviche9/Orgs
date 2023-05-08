package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProductDAO
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extentions.tryLoadImage
import br.com.alura.orgs.model.Product
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.formularioProdutoImagem.setOnClickListener{
            val bindingFormImage = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormImage.formularioImagemBotaoCarregar.setOnClickListener {
                val url = bindingFormImage.formularioImagemUrl.text.toString()
                bindingFormImage.activityFormularioProdutoImagem.tryLoadImage(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingFormImage.root)
                .setPositiveButton("Confirmar") {_, _ ->
                    imageUrl = bindingFormImage.formularioImagemUrl.text.toString()
                    binding.formularioProdutoImagem.tryLoadImage(imageUrl)
                }
                .setNegativeButton("Cancelar") {_, _ ->

                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProductDAO()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.addProduct(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Product {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Product(
            name = nome,
            description = descricao,
            value = valor,
            image = imageUrl
        )
    }
}