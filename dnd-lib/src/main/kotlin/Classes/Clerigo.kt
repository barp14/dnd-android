package Classes

import UtilPack.Dados

class Clerigo : ClassePadrao  {
  override val nomeClasse: String = "Clérigo"
  override var vidaClasse: Int = Dados.d8()
}