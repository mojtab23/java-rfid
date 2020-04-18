package ir.ramankav.rfid.view

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyEvent.CHAR_UNDEFINED
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Region
import javafx.scene.layout.RowConstraints
import javafx.scene.shape.SVGPath

class Numpad : GridPane() {


    init {
        this.id = "numpad"
        this.padding = Insets(10.0)
        this.prefWidth = 300.0
        this.maxWidth = 300.0
        this.prefHeight = 360.0
        this.maxHeight = 360.0
        this.hgap = 10.0
        this.vgap = 10.0
        this.rowConstraints += RowConstraints(100.0)
        this.rowConstraints += RowConstraints(100.0)
        this.rowConstraints += RowConstraints(100.0)
        this.rowConstraints += RowConstraints(100.0)
        this.columnConstraints += ColumnConstraints(100.0)
        this.columnConstraints += ColumnConstraints(100.0)
        this.columnConstraints += ColumnConstraints(100.0)

        for (i in 0..8) {
            val keyCode = when (i) {
                0 -> KeyCode.DIGIT1
                1 -> KeyCode.DIGIT2
                2 -> KeyCode.DIGIT3
                3 -> KeyCode.DIGIT4
                4 -> KeyCode.DIGIT5
                5 -> KeyCode.DIGIT6
                6 -> KeyCode.DIGIT7
                7 -> KeyCode.DIGIT8
                8 -> KeyCode.DIGIT9
                else -> KeyCode.ALT
            }
            this.add(createButton((i + 1).toString(), keyCode), i % 3, i / 3)
        }

        val clearIcon = SVGPath()
        clearIcon.content = "M490.667,64H133.077c-7.196,0-13.906,3.627-17.848,9.647L3.485,244.314c-4.647,7.098-4.647,16.274,0,23.372" +
                "l111.744,170.667c3.942,6.02,10.652,9.647,17.848,9.647h357.589c11.782,0,21.333-9.551,21.333-21.333V85.333" +
                "C512,73.551,502.449,64,490.667,64z M469.333,405.333H144.609L46.833,256l97.776-149.333h324.725V405.333z M198.246,356.418c8.331,8.331,21.839,8.331,30.17,0l70.248-70.248l70.248,70.248c8.331,8.331,21.839,8.331,30.17,0" +
                "s8.331-21.839,0-30.17L328.834,256l70.248-70.248c8.331-8.331,8.331-21.839,0-30.17s-21.839-8.331-30.17,0l-70.248,70.248" +
                "l-70.248-70.248c-8.331-8.331-21.839-8.331-30.17,0c-8.331,8.331-8.331,21.839,0,30.17L268.495,256l-70.248,70.248" +
                "C189.915,334.58,189.915,348.087,198.246,356.418z"
        val svgShape = Region()
        svgShape.shape = clearIcon
        svgShape.style = "-fx-background-color: #2d2d2d;"
        svgShape.setPrefSize(30.0, 24.0)
        svgShape.setMaxSize(30.0, 24.0)

        val enterIcon = SVGPath()
        enterIcon.content = "M433.5,114.75 433.5,216.75 96.9,216.75 188.7,124.95 153,89.25 0,242.25 153,395.25 188.7,359.55 96.9,267.75 " +
                "484.5,267.75 484.5,114.75z"
        val svgEnterShape = Region()
        svgEnterShape.shape = enterIcon
        svgEnterShape.style = "-fx-background-color: #2d2d2d;"
        svgEnterShape.setPrefSize(30.0, 18.0)
        svgEnterShape.setMaxSize(30.0, 18.0)

        this.add(createButton("", KeyCode.ENTER, svgEnterShape), 0, 3)
        this.add(createButton("0", KeyCode.DIGIT0), 1, 3)
        this.add(createButton("", KeyCode.BACK_SPACE, svgShape), 2, 3)


    }

    private fun createButton(text: String, keyCode: KeyCode, graphic: Node? = null): Button {
        val button: Button = if (graphic == null) {
            Button(text)
        } else {
            Button(text, graphic)
        }
        button.prefWidth
        button.onAction = EventHandler {
            val press = KeyEvent(button, SceneController.scene.focusOwner, KeyEvent.KEY_PRESSED, CHAR_UNDEFINED, keyCode.name, keyCode, false, false, false, false)
            SceneController.scene.focusOwner.fireEvent(press)
            val typed = KeyEvent(button, SceneController.scene.focusOwner, KeyEvent.KEY_TYPED, keyCode.char, "", KeyCode.UNDEFINED, false, false, false, false)
            SceneController.scene.focusOwner.fireEvent(typed)
            val release = KeyEvent(button, SceneController.scene.focusOwner, KeyEvent.KEY_RELEASED, CHAR_UNDEFINED, keyCode.name, keyCode, false, false, false, false)
            SceneController.scene.focusOwner.fireEvent(release)
        }
        button.isFocusTraversable = false
        button.maxWidth = Double.MAX_VALUE
        button.maxHeight = Double.MAX_VALUE
        return button
    }
}
