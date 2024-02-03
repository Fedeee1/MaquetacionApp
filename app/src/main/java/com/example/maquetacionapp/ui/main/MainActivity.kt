package com.example.maquetacionapp.ui.main

/*

Una pantalla dentro de un scrollview, para garantizar que vaya a poder verse todoo

La pantalla de arriba a abajo:
    Un título.
    Dos párrafos, con texto aleatorio, tienen que ocupar cada uno el 50% del ancho de la pantalla.
Pensad un algoritmo que devuelva letras random de 50 a 400 caracteres y que de vez en cuando meta espacios,
no hace falta que sean palabras reales. Cuando veamos redes ya usaremos apis que devuelven textos random online.
    Una imagen a todoo ancho de pantalla, poned la que más os guste del basto internet, pero tiene que estar si o si,
siempre debajo de los dos párrafos.
    Un Caja, que por programación se le van a meter un número random de elementos, de 3 a 10.
    Estos elementos tienen un iconito, un texto de longitud variable, reusar el método del párrafo,
pero ahora de 10 a 120 caracteres y al final una cantidad que vosotros defináis con al menos 4 decimales,
pero por pantalla solo hay que mostrarla con 2 decimales y símbolo de moneda.
    Importante el texto solo puede tener una línea y en caso de no caber todoo,
se tiene que cortar el texto, no la cantidad.
    Un viewpager/collection view con información de usuarios, una imagen, edad, sexo, hobbies,
y un pequeño texto descriptivo debajo. Aquí no lo hagáis random, un modelo de datos y os inventáis 4-5 personas.
    Por último 3 párrafos con anchos diferentes 50 – 120 – 70 que se distribuyan uniformemente en el ancho de la pantalla,
dejando la misma separación entre ellos, y entre los bordes.
    Extra points, poner botones cerca de cada componente random, para modificar los textos de los párrafos,
y el número de elementos que aparecen en la caja.

*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.maquetacionapp.R
import com.example.maquetacionapp.R.id.iconElement
import com.example.maquetacionapp.R.id.txtElement
import com.example.maquetacionapp.R.id.txtNumberElement
import com.example.maquetacionapp.data.Element
import com.example.maquetacionapp.data.User
import com.example.maquetacionapp.databinding.ActivityMainBinding
import com.example.maquetacionapp.ui.main.adapter.ViewPagerAdapter
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

        binding.txtParagraph1.text = viewModel.createParagraphRandom(50, 400)
        binding.txtParagraph2.text = viewModel.createParagraphRandom(50, 400)

        binding.txtParagraph3.text = viewModel.createParagraphRandom(30, 100)
        binding.txtParagraph4.text = viewModel.createParagraphRandom(30, 100)
        binding.txtParagraph5.text = viewModel.createParagraphRandom(30, 100)


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
        initAdapter(listUsers)
        binding.circleIndicatorViewPager.setViewPager(binding.viewPagerDescription)

        addElement(listElements)
        binding.btnAddElement.setOnClickListener {
            addElement(viewModel.addElementViewModel())
        }
    }
    private fun initAdapter(listUsers: List<User>){
        var viewPagerAdapter = ViewPagerAdapter(listUsers)
        binding.viewPagerDescription.adapter = viewPagerAdapter
        binding.viewPagerDescription.overScrollMode = View.OVER_SCROLL_ALWAYS
    }
    private fun addElement(listElements: List<Element>){
        for (i in listElements.indices){
            val inflate = layoutInflater.inflate(R.layout.linear_emelents_include, findViewById(R.id.linearElementInclude), false)
            inflate.findViewById<TextView>(txtElement).text = listElements[i].text
            inflate.findViewById<TextView>(txtNumberElement).text = listElements[i].number
            inflate.findViewById<ImageView>(iconElement).setImageResource(R.drawable.icoon_feed)
            binding.linearElements.addView(inflate)
        }
    }
    private fun addElement(element: Element){
            val inflate = layoutInflater.inflate(R.layout.linear_emelents_include, findViewById(R.id.linearElementInclude), false)
            inflate.findViewById<TextView>(txtElement).text = element.text
            inflate.findViewById<TextView>(txtNumberElement).text = element.number
            inflate.findViewById<ImageView>(iconElement).setImageResource(R.drawable.icoon_feed)
            binding.linearElements.addView(inflate)
    }
}