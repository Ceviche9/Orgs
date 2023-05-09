package br.com.alura.orgs.model

import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.parcelize.Parcelize
@Parcelize
data class Product(
        val name: String,
        val description: String,
        val value: BigDecimal,
        val image: String? = null
) : Parcelable
