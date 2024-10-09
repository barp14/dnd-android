package UtilPack

import Classes.*
import PersonagemPack.Personagem
import Racas.*

open class Escolha {

  companion object {

    fun escolhaNome(char: Personagem){
      print("Nome do personagem: ")
      char.nome = readln()
    }

    fun escolhaRaca(char: Personagem) {

      var escolhaValidaRaca = false

      while (!escolhaValidaRaca) {
        println(
          "\nEscolha uma raça:" +
                  "\n1. Anão" +
                  "\n2. Anão da Colina" +
                  "\n3. Anão da montanha" +
                  "\n4. Draconato" +
                  "\n5. Drow" +
                  "\n6. Alto-elfo" +
                  "\n7. Elfo" +
                  "\n8. Elfo da floresta" +
                  "\n9. Meio-elfo" +
                  "\n10. Gnomo" +
                  "\n11. Gnomo da floresta" +
                  "\n12. Gnomo das rochas" +
                  "\n13. Halfling" +
                  "\n14. Halfling pés-leves" +
                  "\n15. Halfling robusto" +
                  "\n16. Humano" +
                  "\n17. Meio-orc" +
                  "\n18. Tiefling"
        )
        print("\nRaça escolhida: ")
        var escolhaRaca: Int = readln().toInt()

        char.raca = when (escolhaRaca) {
          1 -> { escolhaValidaRaca = true
            Anao() }
          2 -> { escolhaValidaRaca = true
            AnaoDaColina() }
          3 -> { escolhaValidaRaca = true
            AnaoDaMontanha() }
          4 -> { escolhaValidaRaca = true
            Draconato() }
          5 -> { escolhaValidaRaca = true
            Drow() }
          6 -> { escolhaValidaRaca = true
            AltoElfo() }
          7 -> { escolhaValidaRaca = true
            Elfo() }
          8 -> { escolhaValidaRaca = true
            ElfoDaFloresta() }
          9 -> { escolhaValidaRaca = true
            MeioElfo() }
          10 -> { escolhaValidaRaca = true
            Gnomo() }
          11 -> { escolhaValidaRaca = true
            GnomoDaFloresta() }
          12 -> { escolhaValidaRaca = true
            GnomoDasRochas() }
          13 -> { escolhaValidaRaca = true
            Halfling() }
          14 -> { escolhaValidaRaca = true
            HalflingPesLeves() }
          15 -> { escolhaValidaRaca = true
            HalflingRobusto() }
          16 -> { escolhaValidaRaca = true
            Humano() }
          17 -> { escolhaValidaRaca = true
            MeioOrc() }
          18 -> { escolhaValidaRaca = true
            Tiefling() }
          else -> {
            println("Opção inválida, por favor escolha novamente.")
            continue
          }
        }
      }
    }

    fun escolhaClasse(char: Personagem) {

      var escolhaValidaClasse = false

      while (!escolhaValidaClasse) {

        println(
          "\nEscolha uma classe:" +
                  "\n1. Arqueiro" +
                  "\n2. Artífice" +
                  "\n3. Bárbaro" +
                  "\n4. Bardo" +
                  "\n5. Clérigo" +
                  "\n6. Druída" +
                  "\n7. Feiticeiro" +
                  "\n8. Guardião" +
                  "\n9. Guerreiro" +
                  "\n10. Ladino" +
                  "\n11. Mago" +
                  "\n12. Monge"
        )
        print("\nClasse escolhida: ")
        val escolhaClasse = readln().toInt()

        char.classe = when (escolhaClasse) {
          1 -> { escolhaValidaClasse = true
            Arqueiro() }
          2 -> { escolhaValidaClasse = true
            Artifice() }
          3 -> { escolhaValidaClasse = true
            Barbaro() }
          4 -> { escolhaValidaClasse = true
            Bardo() }
          5 -> { escolhaValidaClasse = true
            Clerigo() }
          6 -> { escolhaValidaClasse = true
            Druida() }
          7 -> { escolhaValidaClasse = true
            Feiticeiro() }
          8 -> { escolhaValidaClasse = true
            Guardiao() }
          9 -> { escolhaValidaClasse = true
            Guerreiro() }
          10 -> { escolhaValidaClasse = true
            Ladino() }
          11 -> { escolhaValidaClasse = true
            Mago() }
          12 -> { escolhaValidaClasse = true
            Monge() }
          else -> {
            println("Opção inválida, por favor escolha novamente.")
            continue
          }
        }
      }
    }
    
    fun escolhaAtributos(char: Personagem){

      print("\nDeseja inserir seus valores da habilidade ou decidir na aleatoriedade." +
              "\n1 - Inserir." +
              "\n2 - Aleatório." +
              "\nSua esolha: ")
      val escolhaHabilidade = readln().toInt()


      if (escolhaHabilidade == 1) {

        do {
          var pontos: Int = 27
          do {
            print("\nForça: ")
            val digitadoForca = readln().toInt()

            if (digitadoForca <= pontos && digitadoForca >= 0 && digitadoForca <= 7) {
              char.forca = Util.modificadorHabilidade((8 + digitadoForca) + char.raca.buffForca).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoForca)
              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)

          do {
            print("Destreza: ")
            val digitadoDestreza: Int = readln().toInt()

            if (digitadoDestreza <= pontos && digitadoDestreza in 0..7) {
              char.destreza = Util.modificadorHabilidade((8 + digitadoDestreza) + char.raca.buffDestreza).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoDestreza)
              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)

          do {
            print("Constituição: ")
            val digitadoConstituicao: Int = readln().toInt()

            if (digitadoConstituicao <= pontos && digitadoConstituicao in 0..7) {
              char.constituicao = Util.modificadorHabilidade((8 + digitadoConstituicao) + char.raca.buffConstituicao).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoConstituicao)

              // definindo a vida junto com a constiuição por conta do modificador
              char.vida = char.classe.vidaClasse + Util.modificadorVida((char.constituicao)).toInt()

              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)

          do {
            print("Inteligencia: ")
            val digitadoInteligencia: Int = readln().toInt()

            if (digitadoInteligencia <= pontos && digitadoInteligencia in 0..7) {
              char.inteligencia = Util.modificadorHabilidade((8 + digitadoInteligencia) + char.raca.buffInteligencia).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoInteligencia)
              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)

          do {
            print("Sabedoria: ")
            val digitadoSabedoria: Int = readln().toInt()

            if (digitadoSabedoria <= pontos && digitadoSabedoria in 0..7) {
              char.sabedoria = Util.modificadorHabilidade((8 + digitadoSabedoria) + char.raca.buffSabedoria).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoSabedoria)
              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)

          do {
            print("Carisma: ")
            val digitadoCarisma: Int = readln().toInt()

            if (digitadoCarisma <= pontos && digitadoCarisma in 0..7) {
              char.carisma = Util.modificadorHabilidade((8 + digitadoCarisma) + char.raca.buffCarisma).toInt()
              pontos = LimitadorPontos.diminuirPontos(pontos, digitadoCarisma)
              println("Pontos restantes: " + pontos)
              break
            } else {
              println("Valor inserido é maior que os pontos disponíveis, menor que 0 ou maior que 7. Tente novamente.")
            }
          } while (true)
        } while (pontos != 0)

      }
      else if (escolhaHabilidade == 2){

        println("\nRoll the dices!")
        char.forca = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffForca).toInt()
        char.destreza = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffDestreza).toInt()
        char.constituicao = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffConstituicao).toInt()
        char.vida = char.classe.vidaClasse + Util.modificadorVida((char.constituicao)).toInt()
        char.inteligencia = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffInteligencia).toInt()
        char.sabedoria = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffSabedoria).toInt()
        char.carisma = Util.modificadorHabilidade(Util.rolagemAtributos() + char.raca.buffCarisma).toInt()
      }
    }
  }
}