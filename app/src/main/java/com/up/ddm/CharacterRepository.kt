package com.up.ddm

object CharacterRepository {
    private val characterList = mutableListOf<Character>()

    fun addCharacter(character: Character) {
        characterList.add(character)
    }

    fun getAllCharacters(): List<Character> {
        return characterList
    }
}
