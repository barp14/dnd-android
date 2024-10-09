package Classes

import UtilPack.Dados

class Guardiao : ClassePadrao  {
  override val nomeClasse: String = "Guardião"
  override var vidaClasse: Int = Dados.d8()
}