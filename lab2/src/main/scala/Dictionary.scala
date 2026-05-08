// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */
  /* def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    
    Using(Source.fromFile(filePath)) { source =>

      source.getLines().toList.map { line =>
        val name = line.trim

        entityType match {
          case "Person" =>
            Person(name)

          case "University" =>
            University(name)

          case "ProgrammingLanguage" =>
            ProgrammingLanguage(name)

          case "Place" =>
            Place(name)

          case "Organization" =>
              Organization(name)
          
          case _ =>
            throw new IllegalArgumentException(s"Unknown entity type: $entityType")
        }
      }

    }.getOrElse(Nil)
  } */
def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
  val archivo = FileIO.readLines(filePath)
  entityType match {
    case "Person"              => archivo.map(elem => new Person(elem))
    case "University"          => archivo.map(elem => new University(elem))
    case "Organization"        => archivo.map(elem => new Organization(elem))
    case "Place"               => archivo.map(elem => new Place(elem))
    case "ProgrammingLanguage" => archivo.map(elem => new ProgrammingLanguage(elem))
    case _                     => List.empty[NamedEntity]  // o simplemente Nil
  }
}
   
  def loadAll(): List[NamedEntity] = {
    
    loadFromFile("data/people.txt", "Person") :::
    loadFromFile("data/universities.txt", "University") :::
    loadFromFile("data/languages.txt", "ProgrammingLanguage") :::
    loadFromFile("data/place.txt", "Place") :::
    loadFromFile("data/organizations.txt", "Organization") 
  }
}
