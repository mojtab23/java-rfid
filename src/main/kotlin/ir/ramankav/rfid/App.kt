package ir.ramankav.rfid

import ir.ramankav.rfid.view.ResourceResolver
import ir.ramankav.rfid.view.SceneController
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.text.Font
import javafx.stage.Stage
import kotlin.properties.Delegates
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
        AppSetting.stop()
    }

    private fun loadFont() {
        val vazirFont = ResourceResolver.getResourceFor(this.javaClass, "Vazir.ttf")
        val font = Font.loadFont(vazirFont, 18.0)
        println("loaded font: $font")
    }

    override fun init() {
        // by default enable RFID
        AppSetting.enableRFID = parameters.named["enableRFID"]?.toBoolean() ?: true

    }


}

object AppSetting {
    var enableRFID by Delegates.notNull<Boolean>()
    fun stopReadingRFIDIfIsEnable() {
        if (enableRFID) {
            RFIDReaderService.stopReadingCard()
        }
    }

    fun readRFIDIfIsEnable() {
        if (enableRFID) {
            RFIDReaderService.startReadingCard()
        }
    }

    fun stop() {
        if (enableRFID) {
            RFIDReaderService.closeResources()
        }
    }
}
