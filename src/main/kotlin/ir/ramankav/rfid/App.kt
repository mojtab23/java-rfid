package ir.ramankav.rfid

import javafx.application.Application
import javafx.application.Application.launch
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.stage.Stage
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    launch(App::class.java, *args)
}


private const val defaultText = "Please provide a CARD"

class App : Application() {

    private val bigFont = Font(48.0)

    private val textLabel = Label(defaultText).apply {
        this.font = bigFont
    }
    private val button = Button("OK").apply {
        this.font = bigFont
        this.setOnAction { readCard() }
    }
    private val closeButton = Button("CLOSE").apply {
        this.font = bigFont
        this.setOnAction { Platform.exit() }
    }
    private var running = true
    private val service = Executors.newSingleThreadExecutor()


    override fun start(primaryStage: Stage) {
        val root = createRoot()
        val scene = Scene(root, 600.0, 400.0)
        primaryStage.scene = scene
        primaryStage.show()
        readCard()
    }

    private fun createRoot(): Parent {
        val root = VBox()
        root.spacing = 20.0
        root.padding = Insets(20.0)
        button.isDisable = true
        root.children += textLabel
        root.children += button
        root.children += closeButton
        return root
    }


    override fun stop() {
        RFIDReaderService.closeResources()
    }

    private fun readCard() {
        button.isDisable = true
        textLabel.text = defaultText
        val cardReader = object : Task<Unit>() {
            override fun call() {
                RFIDReaderService.readId {
                    Platform.runLater {
                        textLabel.text = "CARD: $it"
                        button.isDisable = false
                    }
                }
            }
        }
        service.submit(cardReader)
    }


}
