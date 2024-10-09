import Classes.*
import PersonagemPack.Personagem
import Racas.*
import UtilPack.Escolha
import UtilPack.LimitadorPontos
import UtilPack.Util

fun main() {
  val char = Personagem()

  Escolha.escolhaNome(char)

  Escolha.escolhaRaca(char)
  Escolha.escolhaClasse(char)

  println("${char.nome} é um ${char.raca.nomeRaca} da classe ${char.classe.nomeClasse}!")

  Escolha.escolhaAtributos(char)

  println("\n-+- Seus valores de habilidade -+-\n")

  println("Força: " + char.forca)
  println("Destreza: " + char.destreza)
  println("Constituição: " + char.constituicao)
  println("Inteligencia: " + char.inteligencia)
  println("Sabedoria: " + char.sabedoria)
  println("Carisma: " + char.carisma)
  println("Vida: " + char.vida)
}