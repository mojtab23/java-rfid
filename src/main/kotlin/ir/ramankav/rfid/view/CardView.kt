package ir.ramankav.rfid.view

import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import java.time.Duration

object CardView : VBox(12.0), VisibleAware {

    private val nameLabel = Label("")
    private val cardIdLabel = Label("")


    var name: String
        get() = nameLabel.text
        set(value) {
            nameLabel.text = value
        }

    var cardId: String
        get() = cardIdLabel.text
        set(value) {
            cardIdLabel.text = value
        }


    private val timerService = createTimerService(Duration.ofSeconds(5))


    init {
        val welcome = Label("خوش آمدید")
        children += welcome

        children += nameLabel
        children += cardIdLabel

        val close = Button("بستن")
        close.onAction = EventHandler { SceneController.showHomeView() }
        children += close

    }


    override fun becomesVisible() {
        timerService.restart()
    }

    override fun becomesInvisible() {
        timerService.cancel()
    }




}
