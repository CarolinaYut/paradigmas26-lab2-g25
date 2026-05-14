// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   *
   * TODO (Ejercicio 3): Implementar este método.
   *
   *   Para cada entidad en el diccionario, verificar si su texto aparece en el
   *   texto del post. Retornar únicamente las entidades que aparecen.
   *
   *   Ejemplo:
   *     text       = "Scala fue creado en EPFL por Martin Odersky"
   *     dictionary = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky"),
   *                    Person("Ada Lovelace")   ← no aparece en el texto
   *                  )
   *     resultado  = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky")
   *                  )
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    val textl = text.toLowerCase() //Transformo las palabras a minusculas
    // Filtro todas las entidades que aparezcan en el texto y tomo la palabra completa
    dictionary.filter(entidad =>
    text.toLowerCase.matches(s".*\\b${entidad.text.toLowerCase}\\b.*") 
    )
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   *
   * TODO (Ejercicio 5): Implementar este método.
   *
   *   Ejemplo:
   *     entities = List(
   *                  Person("Alan Turing"),
   *                  ProgrammingLanguage("Scala"),
   *                  Person("Ada Lovelace"),
   *                  University("MIT")
   *                )
   *     resultado = Map(
   *                   "Person"              -> 2,
   *                   "ProgrammingLanguage" -> 1,
   *                   "University"          -> 1
   *                 )
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    val entitiesTypeList = entities.map {n => n.entityType}
    val result  = entitiesTypeList
      .map { entidad =>  
        (entidad , entitiesTypeList.count(t => t == entidad))
        //es lo mismo q entidad -> entitiesTypeList.count(t => t == entidad)
      }
    result.toMap
  }
  /* otra forma de resolverlo era esta --->
  val entitiesTypeList = entities.map {n => n.entityType}
  val result = entitiesTypeList.groupBy(identity).mapValues(_.size).toMap
  result
  */

  def countByTypeHierarchical(entities: List[NamedEntity]): Map[String, Int] = {
    entities
      .flatMap(e => e.entityType :: e.parentTypes)
      .groupBy(tipo => tipo)
      .map { case (tipo, lista) => tipo -> lista.size }
  }
}