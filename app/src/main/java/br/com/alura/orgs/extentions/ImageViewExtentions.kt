package br.com.alura.orgs.extentions

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.load

fun ImageView.tryLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.erro)
    }
}