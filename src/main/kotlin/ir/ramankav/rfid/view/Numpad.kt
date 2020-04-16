package ir.ramankav.rfid.view

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints

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
            this.add(createButton((i + 1).toString()), i % 3, i / 3)
        }
        this.add(createButton("Enter"), 0, 3)
        this.add(createButton("0"), 1, 3)
        this.add(createButton("."), 2, 3)

    }

    private fun createButton(text: String): Button {
        val button = Button(text)
        button.prefWidth
        button.maxWidth = Double.MAX_VALUE
        button.maxHeight = Double.MAX_VALUE
        return button
    }
}
