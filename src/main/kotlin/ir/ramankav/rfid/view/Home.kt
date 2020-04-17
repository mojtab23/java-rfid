package ir.ramankav.rfid.view

import ir.ramankav.rfid.RFIDReaderService
import javafx.application.Platform
import javafx.concurrent.Service
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.SVGPath
import kotlin.properties.Delegates

private const val PROVIDE_CARD = "لطفا کارت خود را ارایه دهید!"

private const val OR_TEXT = "یا"

private const val FORGOT_CARD = "فراموشی کارت"

private const val RESERVE_FOOD = "رزرو غذا"

private const val LOCK_ICON_SVG_PATH = "M 16 0 C 13.789063 0 11.878906 0.917969 10.6875 2.40625 C 9.496094 3.894531 9 5.824219 9 7.90625 L 9 9 L 12 9 L 12 7.90625 C 12 6.328125 12.390625 5.085938 13.03125 4.28125 C 13.671875 3.476563 14.542969 3 16 3 C 17.460938 3 18.328125 3.449219 18.96875 4.25 C 19.609375 5.050781 20 6.308594 20 7.90625 L 20 9 L 23 9 L 23 7.90625 C 23 5.8125 22.472656 3.863281 21.28125 2.375 C 20.089844 0.886719 18.207031 0 16 0 Z M 9 10 C 7.34375 10 6 11.34375 6 13 L 6 23 C 6 24.65625 7.34375 26 9 26 L 23 26 C 24.65625 26 26 24.65625 26 23 L 26 13 C 26 11.34375 24.65625 10 23 10 Z M 16 15 C 17.105469 15 18 15.894531 18 17 C 18 17.738281 17.597656 18.371094 17 18.71875 L 17 21 C 17 21.550781 16.550781 22 16 22 C 15.449219 22 15 21.550781 15 21 L 15 18.71875 C 14.402344 18.371094 14 17.738281 14 17 C 14 15.894531 14.894531 15 16 15 Z"

object Home : VBox(12.0), VisibleAware {

    private val service = object : Service<Unit>() {
        override fun createTask(): Task<Unit> {
            return object : Task<Unit>() {
                override fun call() {
                    RFIDReaderService.readId {
                        Platform.runLater {
                            SceneController.showCardView("مجتبی زارع‌زاده", it)
                        }
                    }
                }
            }
        }
    }


    var enableRFID by Delegates.notNull<Boolean>()


    init {
        padding = Insets(10.0)
        alignment = Pos.CENTER

        val label = Label(PROVIDE_CARD)
        children += label

        val orLabel = Label(OR_TEXT)
        orLabel.id = "orLabel"
        children += orLabel

        val forgotBtn = Button(FORGOT_CARD)
        children += forgotBtn

        val reserveBtn = Button(RESERVE_FOOD)
        children += reserveBtn

        val footer = HBox(10.0)
        footer.styleClass += "footer"
        footer.alignment = Pos.BOTTOM_LEFT
        footer.prefHeight = 600.0
        footer.prefWidth = 1000.0
        val lockBtn = Button()
        lockBtn.onAction = EventHandler { SceneController.showUnlockView() }
        lockBtn.id = "lockButton"
        val lockIcon = SVGPath()
        lockIcon.content = LOCK_ICON_SVG_PATH
        lockBtn.graphic = lockIcon
        footer.children += lockBtn
        footer.children += DigitalClock()
        children += footer


    }

    override fun becomesVisible() {
        println("Home view is visible")
        if (enableRFID) {
            service.restart()
        }
    }

    override fun becomesInvisible() {
        println("Home view is invisible")
        if (enableRFID) {
            service.cancel()
        }
    }


}
