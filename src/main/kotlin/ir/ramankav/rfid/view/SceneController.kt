package ir.ramankav.rfid.view

import javafx.scene.Parent
import javafx.scene.Scene

object SceneController {

    private val scene = Scene(Home, 600.0, 400.0)
    private var currentRoot: VisibleAware = Home

    fun buildScene(): Scene {
        scene.stylesheets += loadStyles()
        currentRoot.becomesVisible()
        return scene
    }

    fun showUnlockView() {
        switchScene(UnlockView)
    }

    fun showHomeView() {
        switchScene(Home)
    }

    fun showIPConfigView() {
        switchScene(IPConfigView)
    }


    private fun <T> switchScene(node: T) where T : Parent, T : VisibleAware {
        currentRoot.becomesInvisible()
        scene.root = node
        currentRoot = node
        node.becomesVisible()
    }

    private fun loadStyles(): String {
        return ResourceResolver.getResourceFor(this.javaClass, "styles.css")
    }

}


interface VisibleAware {

    fun becomesVisible()
    fun becomesInvisible()

}
