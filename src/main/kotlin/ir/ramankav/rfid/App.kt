package ir.ramankav.rfid

import ir.ramankav.rfid.view.Home
import ir.ramankav.rfid.view.ResourceResolver
import ir.ramankav.rfid.view.SceneController
import ir.ramankav.rfid.view.UnlockView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.text.Font
import javafx.stage.Stage
import java.util.concurrent.Executors
import kotlin.properties.Delegates
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    launch(App::class.java, *args)
    exitProcess(0)
}


private const val defaultText = "Please provide a CARD"

class App : Application() {

    private var enableRFID by Delegates.notNull<Boolean>()

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
    private val service = Executors.newSingleThreadExecutor()


    override fun start(primaryStage: Stage) {
        loadFont()
        val scene = SceneController.buildScene()
        primaryStage.scene = scene
        primaryStage.isFullScreen = true
        primaryStage.show()
        enableRFIDService()
    }



    override fun stop() {
        if (enableRFID) {
            RFIDReaderService.closeResources()
        }
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


    private fun loadFont() {
        val vazirFont = ResourceResolver.getResourceFor(this.javaClass, "Vazir.ttf")
        val font = Font.loadFont(vazirFont, 18.0)
        println("loaded font: $font")
    }



    private fun enableRFIDService() {
        if (enableRFID) {
            readCard()
        }
    }

    override fun init() {
        // by default enable RFID
        enableRFID = parameters.named["enableRFID"]?.toBoolean() ?: true

    }
}
