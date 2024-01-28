package com.example.maquetacionapp

/*

Una pantalla dentro de un scrollview, para garantizar que vaya a poder verse todoo,
sobre todoo en android mucho cuidado con esto, hay pantallas que tú las pruebas en tu super móvil o emulador,
y siempre hay que pensar que existen millones de móviles con pantallas más pequeñas...

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}