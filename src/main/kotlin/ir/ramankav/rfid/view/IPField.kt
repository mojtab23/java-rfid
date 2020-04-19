package ir.ramankav.rfid.view

import javafx.event.ActionEvent
import javafx.event.EventHandler
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

    fun onActionProperty() = field4.onActionProperty()
    var onAction: EventHandler<ActionEvent>?
        get() = onActionProperty().get()
        set(value) = onActionProperty().set(value)

    init {
        this.nodeOrientation = NodeOrientation.LEFT_TO_RIGHT
        field1.promptText = t1
        field1.onAction = EventHandler { field2.requestFocus() }
        field2.promptText = t2
        field2.onAction = EventHandler { field3.requestFocus() }
        field3.promptText = t3
        field3.onAction = EventHandler { field4.requestFocus() }
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

    fun clear() {
        field1.clear()
        field2.clear()
        field3.clear()
        field4.clear()
    }

    override fun requestFocus() {
        field1.requestFocus()
    }

}
