// Racas/RacaPadrao.kt
package Racas

import java.io.Serializable

interface RacaPadrao : Serializable {
  val nomeRaca: String

  val buffForca: Int
  val buffDestreza: Int
  val buffConstituicao: Int
  val buffInteligencia: Int
  val buffSabedoria: Int
  val buffCarisma: Int
}
