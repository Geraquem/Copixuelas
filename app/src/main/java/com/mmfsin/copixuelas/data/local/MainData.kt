package com.mmfsin.copixuelas.data.local

fun getIntroPhrase(): String {
    val list = mutableListOf<String>().apply {
        add("Que yo no me juro, te lo drogo")
        add("Lee, hay que ampliar ese vodkabulario")
        add("La copa con tres hielos es jugar en modo fácil")
        add("No bebas para mañana lo que puedes beber hoy")
        add("El amor puede esperar\nLa cerveza no, que se calienta")
    }
    val rand = (0 until list.size).random()
    return list[rand]
}