package Classes

import UtilPack.Dados

class Ladino : ClassePadrao  {
  override val nomeClasse: String = "Ladino"
  override var vidaClasse: Int = Dados.d10()
}