import java.io.FileInputStream
import java.util.*

private val props by lazy {
  val propertyFile = rootProject.file("poi.properties.kts")
  val properties = Properties()
  if (propertyFile.canRead()) {
    properties.load(FileInputStream(propertyFile))
  } else {
    error("GradleException: Unable to read file poi.properties.kts")
  }
  properties
}

extra.apply {
  set("clientId", props["clientId"])
  set("clientSecret", props["clientSecret"])
}

