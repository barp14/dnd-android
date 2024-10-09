package Classes

import UtilPack.Dados

class Feiticeiro : ClassePadrao  {
  override val nomeClasse: String = "Feiticeiro"
  override var vidaClasse: Int = Dados.d6()
}