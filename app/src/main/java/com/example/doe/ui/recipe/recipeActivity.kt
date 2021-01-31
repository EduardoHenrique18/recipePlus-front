package com.example.doe.ui.recipe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.doe.R


class recipeActivity : AppCompatActivity(), recipeContract.CreateRecipeView {
    private lateinit var presenter: recipeContract.CreateRecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        presenter = recipePresenter(
        )

        val intent = intent

        val description = intent.getStringExtra("description");
        val prepareMode = intent.getStringExtra("prepareMode");
        val recipeYield = intent.getStringExtra("recipeYield");
        val product = intent.getStringExtra("product");

        findViewById<TextView>(R.id.show_description)?.text = description
        findViewById<TextView>(R.id.show_prepareMode)?.text = prepareMode
        findViewById<TextView>(R.id.show_yield)?.text = recipeYield
        findViewById<TextView>(R.id.show_product)?.text = product
    }

}