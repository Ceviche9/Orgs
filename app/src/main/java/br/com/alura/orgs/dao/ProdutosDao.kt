package br.com.alura.orgs.dao

import br.com.alura.orgs.R
import br.com.alura.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                "Salada de Frutas",
                "Com frutas frescas",
                BigDecimal(20),
                "https://images.pexels.com/photos/1132047/pexels-photo-1132047.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            )
        )
    }
}