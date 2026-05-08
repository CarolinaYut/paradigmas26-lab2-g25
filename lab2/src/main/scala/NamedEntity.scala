abstract class NamedEntity(val text: String) {
  def entityType: String
  def describe: String = s"[$entityType] $text"
}

class Person(text: String) extends NamedEntity(text) {
  def entityType = "Person"
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType = "Organization"
}

class University(text: String) extends Organization(text) {
  override def entityType = "University"
}

class Place(text: String) extends NamedEntity(text) {
  def entityType = "Place"
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType = "Technology"
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType = "ProgrammingLanguage"
}


  /* val entities: List[NamedEntity] = List(
    new Person("Alan Turing"),
    new University("MIT"),
    new ProgrammingLanguage("Scala"),
    new Place("San Francisco")
  )

  entities.foreach(e => println(e.describe))
*/