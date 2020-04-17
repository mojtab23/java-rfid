package ir.ramankav.rfid.view

import javafx.geometry.NodeOrientation
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox

class IPField(
        t1: String? = null,
        t2: String? = null,
        t3: String? = null,
        t4: String? = null
) : HBox() {

    private val field1 = TextField()
    private val field2 = TextField()
    private val field3 = TextField()
    private val field4 = TextField()

    init {
        this.nodeOrientation = NodeOrientation.LEFT_TO_RIGHT
        field1.promptText = t1
        field2.promptText = t2
        field3.promptText = t3
        field4.promptText = t4
        this.children += field1
        this.children += Label(".")
        this.children += field2
        this.children += Label(".")
        this.children += field3
        this.children += Label(".")
        this.children += field4
    }

    val ip: String?
        get() {
            if (validateText(field1) && validateText(field2) && validateText(field3) && validateText(field4)) {
                return "${field1.text}.${field2.text}.${field3.text}.${field4.text}"
            }
            return null
        }

    private fun validateText(field: TextField): Boolean {
        if (!field.text.isNullOrBlank()) {
            val intOrNull = field.text.toIntOrNull()
            if (intOrNull != null && intOrNull >= 0 && intOrNull <= 255) {
                return true
            }
        }
        return false
    }

}
