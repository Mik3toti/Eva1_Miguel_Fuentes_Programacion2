package com.example.eva1_miguel_fuentes_p2.modelo;

class ItemMenu (var nombre:String,var precio: Int){

}

class ItemMesa(var cantidad: Int , var itemMenu:ItemMenu ){
    fun calcularSubtotal(): Int{
        return itemMenu.precio * cantidad
    }
}

class CuentaMesa (var mesa: Int){
    var _items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int){
        val nuevoItem = ItemMesa(cantidad, itemMenu)
        _items.add(nuevoItem)
    }

    fun agregarItem(itemMesa: ItemMesa){
        _items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        return _items.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) (calcularTotalSinPropina() * 0.10).toInt() else 0
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}




