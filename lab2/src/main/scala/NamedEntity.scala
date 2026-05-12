abstract class NamedEntity(val text: String) {
  def entityType: String
  def describe: String = s"[$entityType] $text"
  def parentTypes: List[String] = List.empty
}

class Person(text: String) extends NamedEntity(text) {
  def entityType = "Person"
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType = "Organization"
}

class University(text: String) extends Organization(text) {
  override def entityType = "University"
  override def parentTypes = List("Organization")
}

class Place(text: String) extends NamedEntity(text) {
  def entityType = "Place"
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType = "Technology"
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType = "ProgrammingLanguage"
  override def parentTypes = List("Technology")
}