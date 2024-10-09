package Classes

import UtilPack.Dados

class Guerreiro : ClassePadrao  {
  override val nomeClasse: String = "Guerreiro"
  override var vidaClasse: Int = Dados.d10()
}