package Classes

import UtilPack.Dados

class Arqueiro : ClassePadrao {
  override val nomeClasse: String = "Arqueiro"
  override var vidaClasse: Int = Dados.d10()
}