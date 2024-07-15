package mos.example

import io.quarkus.logging.Log
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone


@QuarkusMain
class CustomQuarkusStart {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            setApplicationTimezone()
            Quarkus.run(MyApp::class.java, *args)
        }

        var startTimeServer: LocalDateTime? = null

        fun getFormattedStartTimeServer(): String? {
            return startTimeServer?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        }

        private fun setApplicationTimezone() {
            TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
        }

    }




    class MyApp : QuarkusApplication {

        override fun run(vararg args: String): Int {
            Log.info("Starting Quarkus server with custom runner.....")
            startTimeServer = LocalDateTime.now()
            Quarkus.waitForExit()
            return 0
        }
    }
}