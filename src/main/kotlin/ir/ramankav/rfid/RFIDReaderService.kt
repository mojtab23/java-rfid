package ir.ramankav.rfid

import com.diozero.api.DigitalOutputDevice
import com.diozero.api.SPIConstants
import com.diozero.devices.Buzzer
import com.diozero.devices.MFRC522
import com.diozero.util.Hex
import com.diozero.util.SleepUtil

object RFIDReaderService {

    private val buzzer = Buzzer(24)
    private val rfidBoard = MFRC522(SPIConstants.DEFAULT_SPI_CONTROLLER, 0, DigitalOutputDevice(25, true, false))
    private var running = true


    private fun readCard(): String? {

        if (!rfidBoard.isNewCardPresent) return null

        val uid = rfidBoard.readCardSerial()

        if (uid == null) {
            failBeep()
            return null
        }

        val cardId = Hex.encodeHexString(uid.uidBytes)
        println("Card type: ${uid.type.name} UID: $cardId")
        successBeep()
        rfidBoard.haltA()
        rfidBoard.stopCrypto1()
        return cardId
    }


    private fun successBeep() = buzzer.beep(0.1F, 0.05F, 2, false)
    private fun failBeep() = buzzer.beep(0.4F, 0.1F, 1, false)

//    private inline fun <T : Closeable?> Array<T>.use(block: () -> Unit) {
//        var exception: Throwable? = null
//        try {
//            return block()
//        } catch (e: Throwable) {
//            exception = e
//            throw e
//        } finally {
//            when (exception) {
//                null -> forEach { it?.close() }
//                else -> forEach {
//                    try {
//                        it?.close()
//                    } catch (closeException: Throwable) {
//                        exception.addSuppressed(closeException)
//                    }
//                }
//            }
//        }
//    }

    fun readId(callback: (String) -> Unit) {
        var cardId: String? = null
        while (running && cardId == null) {
            cardId = readCard()
            SleepUtil.sleepMillis(500)
        }
        if (running && cardId != null) {
            callback(cardId)
        }
    }

    fun closeResources() {
        running = false
        buzzer.close()
        rfidBoard.close()
    }


}
