package UtilPack

import kotlin.math.floor

class Util {

  companion object {

    fun modificadorHabilidade(charHabilidade: Int): Float {
      val modificador = (floor(charHabilidade.toFloat() - 10) / 2)
      return modificador + charHabilidade
    }

    fun modificadorVida(charHabilidade: Int): Float {
      val modificador = (floor(charHabilidade.toFloat() - 10) / 2)
      return modificador
    }

    fun rolagemAtributos(): Int {
      val rolagens = List(4) { Dados.d6() }
      val somaDosMaiores = rolagens.sorted().drop(1).sum() // Remove o menor valor e soma os restantes
        return somaDosMaiores
    }
  }
}