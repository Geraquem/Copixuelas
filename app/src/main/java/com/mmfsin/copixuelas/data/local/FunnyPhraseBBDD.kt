package com.mmfsin.copixuelas.data.local

fun getIntroPhrase(): String {
    val list = mutableListOf<String>().apply {
        add("Que yo no me juro, te lo drogo")
        add("Lee, hay que ampliar ese vodkabulario")
        add("La copa con tres hielos es jugar en modo fácil")
        add("No bebas para mañana lo que puedes beber hoy")
        add("El amor puede esperar\nLa cerveza no, que se calienta")
        add("El agua para los débiles")
        add("A la resaca se la vence volviendo a empezar")
        add("La resaca es como las agujetas\nNo se valora hasta el día siguiente")
    }.shuffled()
    val rand = (list.indices).random()
    return list[rand]
}