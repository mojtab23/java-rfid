package ir.ramankav.rfid.view

import javafx.application.Platform
import javafx.concurrent.Service
import javafx.concurrent.Task
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.time.Duration

private const val VIEW_NAME = "ورود به تنظیمات"
private const val UNLOCK_CODE = "کد مدیریت"
private const val UNLOCK = "بازکردن"

object UnlockView : HBox(), VisibleAware {

    private val timerService = createTimerService(Duration.ofSeconds(30))

    private val passwordField = PasswordField()

    init {
        val rightItems = VBox()
        rightItems.spacing = 10.0
        rightItems.padding = Insets(10.0)
        rightItems.nodeOrientation = NodeOrientation.RIGHT_TO_LEFT

        val viewName = Label(VIEW_NAME)
        rightItems.children += viewName

        passwordField.promptText = UNLOCK_CODE
        rightItems.children += passwordField

        val button = Button(UNLOCK)
        button.onAction = EventHandler { SceneController.showIPConfigView() }
        rightItems.children += button

        val numpad = Numpad()

        this.children.addAll(numpad, rightItems)
    }

    private fun startCountDown() {
        timerService.restart()
    }

    private fun cancelCountDownTimer() {
        timerService.cancel()
    }

    override fun becomesVisible() {
        println("Unlock view is visible")
        startCountDown()
    }

    override fun becomesInvisible() {
        println("Unlock view is invisible")
        cancelCountDownTimer()
        clearInputs()
    }

    private fun clearInputs() {
        passwordField.clear()
    }

}

fun createTimerService(duration: Duration): Service<Unit> {
    return object : Service<Unit>() {
        override fun createTask(): Task<Unit> {
            return object : Task<Unit>() {
                override fun call() {
                    Thread.sleep(duration.toMillis())
                    println("Timeout!")
                    Platform.runLater { SceneController.showHomeView() }
                }
            }
        }
    }
}
