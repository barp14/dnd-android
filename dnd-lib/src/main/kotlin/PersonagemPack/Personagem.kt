package PersonagemPack

import Classes.ClassePadrao
import Racas.RacaPadrao
import kotlin.math.floor

open class Personagem() {

  lateinit var nome: String
  lateinit var classe: ClassePadrao
  lateinit var raca: RacaPadrao

  var vida: Int = 0
  var forca: Int = 0
  var destreza: Int = 0
  var constituicao: Int = 0
  var inteligencia: Int = 0
  var sabedoria: Int = 0
  var carisma: Int = 0

}

