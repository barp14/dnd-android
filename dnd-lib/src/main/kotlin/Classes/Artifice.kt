package Classes

import UtilPack.Dados

class Artifice : ClassePadrao {
  override val nomeClasse: String = "Artifíce"
  override var vidaClasse: Int = Dados.d10()
}