import Classes.*
import PersonagemPack.Personagem
import Racas.*
import UtilPack.Escolha
import UtilPack.LimitadorPontos
import UtilPack.Util

fun main() {
  val char = Personagem()

  // Definir o nome do personagem
  val nome = "Arthur"
  Escolha.escolhaNome(char, nome)

  // Escolher a raça (exemplo: 1 para "Anão")
  val escolhaRaca = 1
  Escolha.escolhaRaca(char, escolhaRaca)

  // Escolher a classe (exemplo: 1 para "Arqueiro")
  val escolhaClasse = 1
  Escolha.escolhaClasse(char, escolhaClasse)

  println("${char.nome} é um ${char.raca.nomeRaca} da classe ${char.classe.nomeClasse}!")

  // Definir os atributos manualmente (modo Inserir = 1)
  val atributos = mapOf(
    "forca" to 10,
    "destreza" to 12,
    "constituicao" to 14,
    "inteligencia" to 8,
    "sabedoria" to 13,
    "carisma" to 10
  )
  val modo = 1 // 1 para Inserir, 2 para Aleatório
  Escolha.escolhaAtributos(char, atributos, modo)

  println("\n-+- Seus valores de habilidade -+-\n")

  println("Força: " + char.forca)
  println("Destreza: " + char.destreza)
  println("Constituição: " + char.constituicao)
  println("Inteligencia: " + char.inteligencia)
  println("Sabedoria: " + char.sabedoria)
  println("Carisma: " + char.carisma)
  println("Vida: " + char.vida)
}
