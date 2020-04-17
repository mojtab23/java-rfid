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

    init {
        val leftItems = VBox()
        leftItems.spacing = 10.0
        leftItems.padding = Insets(10.0)
        leftItems.nodeOrientation = NodeOrientation.LEFT_TO_RIGHT

        val viewName = Label("Configure IP")
        leftItems.children += viewName

//        val ipAddress = TextField()
//        ipAddress.promptText = "192.168.1.10"
//        rightItems.children += ipAddress
        leftItems.children += IPField("192", "168", "1", "10")


        val mask = TextField()
        mask.promptText = "24"
        leftItems.children += mask

//        val gateway = TextField()
//        gateway.promptText = "192.168.1.1"
//        rightItems.children += gateway
        leftItems.children += IPField("192", "168", "1", "1")

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
    }
}

