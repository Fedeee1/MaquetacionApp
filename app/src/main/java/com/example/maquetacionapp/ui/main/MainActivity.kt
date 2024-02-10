package com.example.maquetacionapp.ui.main

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.maquetacionapp.R
import com.example.maquetacionapp.R.id.btnAccept
import com.example.maquetacionapp.R.id.editChangeText
import com.example.maquetacionapp.R.id.iconElement
import com.example.maquetacionapp.R.id.linearEditTextElement
import com.example.maquetacionapp.R.id.txtElement
import com.example.maquetacionapp.R.id.txtNumberElement
import com.example.maquetacionapp.commons.MAX_NUM_OF_CHARS_LAST_PARAGRAPHS
import com.example.maquetacionapp.commons.MAX_NUM_OF_CHARS_PARAGRAPHS
import com.example.maquetacionapp.commons.MIN_NUM_OF_CHARS_LAST_PARAGRAPHS
import com.example.maquetacionapp.commons.MIN_NUM_OF_CHARS_PARAGRAPHS
import com.example.maquetacionapp.data.Element
import com.example.maquetacionapp.data.User
import com.example.maquetacionapp.databinding.ActivityMainBinding
import com.example.maquetacionapp.ui.main.adapter.ViewPagerAdapter
import com.example.maquetacionapp.ui.main.adapter.ViewPagerTransformer
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private var listUsers: List<User> = mutableListOf()
    private var listElements: List<Element> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearElements.removeAllViewsInLayout()
        initVars()
        setupViewModel() // Modificar este metodo

        initAdapter(listUsers)
        binding.circleIndicatorViewPager.setViewPager(binding.viewPagerDescription)

        binding.btnAddElement.setOnClickListener {
            addElement(viewModel.addElementViewModel())
        }

        initSwipe()

    }

    private fun initVars() {
        binding.txtParagraph1.text = viewModel.createParagraphRandom(
            MIN_NUM_OF_CHARS_PARAGRAPHS,
            MAX_NUM_OF_CHARS_PARAGRAPHS
        )
        binding.txtParagraph2.text = viewModel.createParagraphRandom(
            MIN_NUM_OF_CHARS_PARAGRAPHS,
            MAX_NUM_OF_CHARS_PARAGRAPHS
        )

        binding.txtParagraph3.text = viewModel.createParagraphRandom(
            MIN_NUM_OF_CHARS_LAST_PARAGRAPHS,
            MAX_NUM_OF_CHARS_LAST_PARAGRAPHS
        )
        binding.txtParagraph4.text = viewModel.createParagraphRandom(
            MIN_NUM_OF_CHARS_LAST_PARAGRAPHS,
            MAX_NUM_OF_CHARS_LAST_PARAGRAPHS
        )
        binding.txtParagraph5.text = viewModel.createParagraphRandom(
            MIN_NUM_OF_CHARS_LAST_PARAGRAPHS,
            MAX_NUM_OF_CHARS_LAST_PARAGRAPHS
        )
    }

    private fun initSwipe() {
        binding.swipeMain.setOnRefreshListener {
            binding.linearElements.removeAllViewsInLayout()
            this.recreate()
            binding.swipeMain.isRefreshing = false
        }
        addElement(listElements)
    }

    private fun setupViewModel() {
        viewModel.viewModelScope.launch {
            viewModel.listUsersFlow.collect {
                listUsers = it
            }

        }
        viewModel.viewModelScope.launch {
            viewModel.listElementsFlow.collect {
                listElements = it
            }
        }
    }

    private fun initAdapter(listUsers: List<User>) {
        val viewPagerAdapter = ViewPagerAdapter(listUsers)
        binding.viewPagerDescription.adapter = viewPagerAdapter
        binding.viewPagerDescription.setPageTransformer(ViewPagerTransformer())
        binding.viewPagerDescription.overScrollMode = View.OVER_SCROLL_ALWAYS
    }

    private fun addElement(listElements: List<Element>) {
        for (i in listElements.indices) {
            val inflate = layoutInflater.inflate(
                R.layout.card_view_emelents_constraint,
                findViewById(R.id.constraintElementInclude),
                false
            )
            with(inflate) {
                findViewById<TextView>(txtElement).text = listElements[i].text
                findViewById<TextView>(txtNumberElement).text = listElements[i].number
                findViewById<ImageView>(iconElement).setImageResource(R.drawable.icon_edit)
                findViewById<ImageView>(iconElement).setOnClickListener {
                    findViewById<LinearLayout>(linearEditTextElement).visibility = View.VISIBLE
                    findViewById<ImageButton>(btnAccept).setOnClickListener {
                        if (!inflate.findViewById<EditText>(editChangeText).text.isNullOrBlank()) {
                            findViewById<TextView>(txtElement).text =
                                inflate.findViewById<EditText>(editChangeText).text
                            findViewById<LinearLayout>(linearEditTextElement).visibility = View.GONE
                        } else {
                            Toast.makeText(
                                this@MainActivity, "Ingrese un texto", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            binding.linearElements.addView(inflate)
        }
    }

    private fun addElement(element: Element) {
        val inflate = layoutInflater.inflate(
            R.layout.card_view_emelents_constraint,
            findViewById(R.id.constraintElementInclude),
            false
        )
        with(inflate) {
            findViewById<TextView>(txtElement).text = element.text
            findViewById<TextView>(txtNumberElement).text = element.number
            findViewById<ImageView>(iconElement).setImageResource(R.drawable.icon_edit)
            findViewById<ImageView>(iconElement).setOnClickListener {
                findViewById<LinearLayout>(linearEditTextElement).visibility = View.VISIBLE
                findViewById<ImageButton>(btnAccept).setOnClickListener {
                    if (!findViewById<EditText>(editChangeText).text.isNullOrBlank()) {
                        findViewById<TextView>(txtElement).text =
                            inflate.findViewById<EditText>(editChangeText).text
                        findViewById<LinearLayout>(linearEditTextElement).visibility = View.GONE
                    } else {
                        Toast.makeText(this@MainActivity, "Ingrese un texto", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.linearElements.addView(inflate)
    }
}