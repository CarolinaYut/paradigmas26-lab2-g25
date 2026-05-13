// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================
import Dictionary.loadAll
import Formatters.formatNERResult
import Analyzer.detectEntities
object Main {
  def main(args: Array[String]): Unit = {

     // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------
    // TODO (Ejercicio 2)
    val dictionary: List[NamedEntity] = loadAll()

    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    // ------------------------------------------------------------------
    // Paso 2: Descargar posts
    // ------------------------------------------------------------------
    val subscriptions = FileIO.readSubscriptions()
    // Devuelve una lista de tuplas de (url, lista[titulos])
    val allPosts: List[(String, List[String])] = subscriptions.map { url =>
      println(s"Descargando posts de: $url")
      val json   = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)
      (url, titles)
    }

    // ------------------------------------------------------------------
    // Paso 3: Detectar entidades y mostrar resultados por post
    // ------------------------------------------------------------------
    // TODO (Ejercicios 3, 4 y 6):
    //   Para cada post:
    //     1. Detectar entidades
    //     2. Formatear y mostrar el resultado
    allPosts.foreach { case (url, titles) =>
      titles.foreach { title => 
        val entities = detectEntities(title, dictionary)
        val formatted = formatNERResult(title, entities)
        println(formatted) 
      }
    }
    
    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------
    // TODO (Ejercicios 5 y 6):
    //   1. Recolectar TODAS las entidades detectadas en todos los posts
    //   2. Contar por tipo
    //   3. Mostrar el resumen
    /* se guarda en globaletities todas las entidades, como se logra esto:
      se recorre la lista de titulos de todos los posts y en cada titulo
      se se detecta las entidades, luego en vez de quedar una list[list[entidades]] 
      con flatMap lo onvertimos en List[entidades asi lo toma countByType]*/
    val globalentities = allPosts.flatMap { case (url, titles) =>
      titles.flatMap{ title =>
        detectEntities(title, dictionary)
      }  
    }
    val countbyT = Analyzer.countByType(globalentities)
    println(countbyT)
  /* 
  //IMPLEMENTACION DE PUNTO ESTRELLA
  val countsHierarchical = Analyzer.countByTypeHierarchical(todasLasEntidades)
  println(Formatters.formatEntityStatsHierarchical(countsHierarchical))

   */
  }
}