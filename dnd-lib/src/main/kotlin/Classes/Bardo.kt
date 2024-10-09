package Classes

import UtilPack.Dados

class Bardo : ClassePadrao  {
  override val nomeClasse: String = "Bardo"
  override var vidaClasse: Int = Dados.d8()
}