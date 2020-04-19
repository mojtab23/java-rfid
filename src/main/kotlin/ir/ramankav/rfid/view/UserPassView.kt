package ir.ramankav.rfid.view

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

private const val VIEW_NAME = "ورود کد‌ملی و رمز دوم"
private const val USER_ID = "کد ملی"
private const val SECONDARY_PASS = "رمز دوم"
private const val PROCESS_CODE = "بررسی اطلاعات"

object UserPassView : HBox(), VisibleAware {

    init {
        val rightItems = VBox()
        rightItems.spacing = 10.0
        rightItems.padding = Insets(10.0)
        rightItems.nodeOrientation = NodeOrientation.RIGHT_TO_LEFT

        val viewName = Label(VIEW_NAME)
        rightItems.children += viewName

        val label = Label(USER_ID)
        rightItems.children += label
        val userId = TextField()
        userId.promptText = USER_ID
        rightItems.children += userId

        val submitInput = EventHandler<ActionEvent> { SceneController.showCardView("test", "test") }

        val label1 = Label(SECONDARY_PASS)
        rightItems.children += label1
        val secondaryPass = PasswordField()
        secondaryPass.promptText = SECONDARY_PASS
        secondaryPass.onAction = submitInput
        rightItems.children += secondaryPass

        val button = Button(PROCESS_CODE)
        button.onAction = submitInput
        rightItems.children += button

        val numpad = Numpad()

        this.children.addAll(numpad, rightItems)
    }

    override fun becomesVisible() {
        println("User Pass view is visible")

    }

    override fun becomesInvisible() {
        println("User Pass view is invisible")
//todo clear fields
    }
}
