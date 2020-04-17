package ir.ramankav.rfid

import ir.ramankav.rfid.view.Home
import ir.ramankav.rfid.view.ResourceResolver
import ir.ramankav.rfid.view.SceneController
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.text.Font
import javafx.stage.Stage
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    launch(App::class.java, *args)
    exitProcess(0)
}


class App : Application() {

    override fun start(primaryStage: Stage) {
        loadFont()
        val scene = SceneController.buildScene()
        primaryStage.scene = scene
        primaryStage.isFullScreen = true
        primaryStage.show()
    }


    override fun stop() {
        if (Home.enableRFID) {
            RFIDReaderService.closeResources()
        }
    }


    private fun loadFont() {
        val vazirFont = ResourceResolver.getResourceFor(this.javaClass, "Vazir.ttf")
        val font = Font.loadFont(vazirFont, 18.0)
        println("loaded font: $font")
    }

    override fun init() {
        // by default enable RFID
        Home.enableRFID = parameters.named["enableRFID"]?.toBoolean() ?: true

    }
}
