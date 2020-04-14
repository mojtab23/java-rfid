package ir.ramankav.rfid

import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.util.Duration
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DigitalClock : Label() {
    init {
        id = "digitalClock"
//        stylesheets += ResourceResolver.getResourceFor(this.javaClass, "styles.css")
        bindToTime()
    }

    private val jalaliCalendar = JalaliCalendar()

    // the digital clock updates once a second.
    private fun bindToTime() {
        val timeline = Timeline(
                KeyFrame(Duration.seconds(0.0),
                        EventHandler {
                            val dateTime = ZonedDateTime.now()
                            val gregorianCalendar = GregorianCalendar.from(dateTime)
                            jalaliCalendar.fromGregorian(gregorianCalendar)

                            val s = "$jalaliCalendar, ${LocalTime.now().format(DateTimeFormatter.ofPattern(" HH : mm : ss "))}"
                            val chars = s.toCharArray().map { char ->
                                if (char.isDigit()) {
                                    val value = Character.getNumericValue(char)
                                    '۰'.plus(value)
                                } else {
                                    return@map char
                                }
                            }
                            val string = String(chars.toCharArray())
                            text = string
                        }
                ),
                KeyFrame(Duration.seconds(1.0))
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

}


object ResourceResolver {
    /**
     * Retrieves an absolute resource path from a path relative to the location of the specified class.
     * The requested resource must exist otherwise this method will throw an IllegalArgumentException.
     * @param clazz a path relative to the location of this class.
     * @param path a path relative to the location of this class.
     * @return the absolute resource path.
     * @throws IllegalArgumentException if a resource at the specified path does not exist.
     */
    fun getResourceFor(clazz: Class<*>, path: String): String {
        val resourceURL = clazz.classLoader.getResource(path)
                ?: throw IllegalArgumentException("No resource exists at: " + path + " relative to " + clazz.name)
        return resourceURL.toExternalForm()
    }
}

