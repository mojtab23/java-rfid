package ir.ramankav.rfid.view

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

private const val VIEW_NAME = "ورود کد فراموشی"
private const val FORGET_CODE = "کد فراموشی"
private const val PROCESS_CODE = "بررسی کد"


object ForgetCardView : HBox(), VisibleAware {

    init {
        val rightItems = VBox()
        rightItems.spacing = 10.0
        rightItems.padding = Insets(10.0)
        rightItems.nodeOrientation = NodeOrientation.RIGHT_TO_LEFT

        val viewName = Label(VIEW_NAME)
        rightItems.children += viewName

        val forgetCode = TextField()
        forgetCode.promptText = FORGET_CODE
        rightItems.children += forgetCode

        val button = Button(PROCESS_CODE)
        button.onAction = EventHandler { SceneController.showCardView("test","test") }
        rightItems.children += button

        val numpad = Numpad()

        this.children.addAll(numpad, rightItems)
    }


    override fun becomesVisible() {
        println("Forget card view is visible")

    }

    override fun becomesInvisible() {
        println("Forget card view is invisible")

    }

}
