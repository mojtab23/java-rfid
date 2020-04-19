package ir.ramankav.rfid.view

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.ToolBar
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.time.Duration

object IPConfigView : HBox(), VisibleAware {

    private val timerService = createTimerService(Duration.ofMinutes(1))

    private val ip = IPField("192", "168", "1", "10")
    private val gateway = IPField("192", "168", "1", "1")
    private val mask = TextField()

    init {
        val leftItems = VBox()
        leftItems.spacing = 10.0
        leftItems.padding = Insets(10.0)
        leftItems.nodeOrientation = NodeOrientation.LEFT_TO_RIGHT

        val viewName = Label("Configure IP")
        leftItems.children += viewName

        leftItems.children += ip

        mask.promptText = "24"
        leftItems.children += mask

        leftItems.children += gateway

        val saveButton = Button("Save")
        saveButton.onAction = EventHandler { println("save IP") }
        val cancelButton = Button("Cancel")
        cancelButton.onAction = EventHandler { SceneController.showHomeView() }
        leftItems.children += ToolBar(saveButton, cancelButton)


        val numpad = Numpad()

        this.children.addAll(leftItems, numpad)
    }

    override fun becomesVisible() {
        println("IPConfigView is visible")
        timerService.restart()
    }

    override fun becomesInvisible() {
        println("IPConfigView is invisible")
        timerService.cancel()
        clearInputs()
    }

    private fun clearInputs() {
        ip.clear()
        gateway.clear()
        mask.clear()
    }
}

