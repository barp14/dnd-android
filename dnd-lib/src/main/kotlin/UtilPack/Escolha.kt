package UtilPack

import Classes.*
import PersonagemPack.Personagem
import Racas.*

open class Escolha {

  companion object {

    fun escolhaNome(char: Personagem, nome: String) {
      char.nome = nome
    }

    fun escolhaRaca(char: Personagem, escolhaRaca: Int) {
      char.raca = when (escolhaRaca) {
        1 -> Anao()
        2 -> AnaoDaColina()
        3 -> AnaoDaMontanha()
        4 -> Draconato()
        5 -> Drow()
        6 -> AltoElfo()
        7 -> Elfo()
        8 -> ElfoDaFloresta()
        9 -> MeioElfo()
        10 -> Gnomo()
        11 -> GnomoDaFloresta()
        12 -> GnomoDasRochas()
        13 -> Halfling()
        14 -> HalflingPesLeves()
        15 -> HalflingRobusto()
        16 -> Humano()
        17 -> MeioOrc()
        18 -> Tiefling()
        else -> throw IllegalArgumentException("Opção de raça inválida.")
      }
    }

    fun escolhaClasse(char: Personagem, escolhaClasse: Int) {
      char.classe = when (escolhaClasse) {
        1 -> Arqueiro()
        2 -> Artifice()
        3 -> Barbaro()
        4 -> Bardo()
        5 -> Clerigo()
        6 -> Druida()
        7 -> Feiticeiro()
        8 -> Guardiao()
        9 -> Guerreiro()
        10 -> Ladino()
        11 -> Mago()
        12 -> Monge()
        else -> throw IllegalArgumentException("Opção de classe inválida.")
      }
    }

    fun escolhaAtributos(char: Personagem, atributos: Map<String, Int>, modo: Int) {
      if (modo == 1) {
        // Modo Inserir: validar e atribuir atributos
        var pontos = 27
        atributos.forEach { (atributo, valor) ->
          if (valor > 8 || valor < 1) {
            throw IllegalArgumentException("O atributo $atributo deve estar entre 1 e 8.")
          }
          if (valor > pontos) {
            throw IllegalArgumentException("Pontos insuficientes para o atributo $atributo.")
          }
          pontos -= valor
          when (atributo.lowercase()) {
            "forca" -> char.forca =
              Util.modificadorHabilidade(8 + valor + char.raca.buffForca).toInt()

            "destreza" -> char.destreza =
              Util.modificadorHabilidade(8 + valor + char.raca.buffDestreza).toInt()

            "constituicao" -> {
              // Calcula o valor final da constituição com base em 8 (mínimo) mais o valor distribuído e o buff da raça
              char.constituicao = 8 + valor + char.raca.buffConstituicao

              // Calcula a vida: vida base da classe + modificador baseado na constituição
              // Util.modificadorHabilidade retorna o modificador, então você aplica isso à vida
              val modificadorConstituicao = Util.modificadorHabilidade(char.constituicao).toInt()

              // Vida da classe somada ao modificador de constituição (que influencia os pontos de vida adicionais)
              char.vida = char.classe.vidaClasse + modificadorConstituicao
            }


            "inteligencia" -> char.inteligencia =
              Util.modificadorHabilidade(8 + valor + char.raca.buffInteligencia).toInt()

            "sabedoria" -> char.sabedoria =
              Util.modificadorHabilidade(8 + valor + char.raca.buffSabedoria).toInt()

            "carisma" -> char.carisma =
              Util.modificadorHabilidade(8 + valor + char.raca.buffCarisma).toInt()

            else -> throw IllegalArgumentException("Atributo inválido: $atributo")
          }
        }
        if (pontos != 0) {
          throw IllegalArgumentException("Todos os 27 pontos devem ser distribuídos.")
        }
      } else if (modo == 2) {
        // Modo Aleatório: utilizar rolagem de dados
        char.forca =
          Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffForca).toInt()
        char.destreza =
          Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffDestreza).toInt()
        char.constituicao = 8 + Util.rolagemAtributos() + char.raca.buffConstituicao
        char.vida = char.classe.vidaClasse + Util.modificadorHabilidade(char.constituicao).toInt()
        char.inteligencia =
          Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffInteligencia).toInt()
        char.sabedoria =
          Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffSabedoria).toInt()
        char.carisma =
          Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffCarisma).toInt()
      }
    }
  }
}