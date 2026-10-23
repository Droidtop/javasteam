package `in`.dragonbra.javasteam.steam.cdn

import java.io.File

/**
 * Downloads that the host application makes for JavaSteam: a depot chunk, a web file or a Workshop file. Set one with
 * [in.dragonbra.javasteam.steam.steamclient.configuration.ISteamConfigurationBuilder.withFetcher] when the host has a
 * download engine of its own; without one, JavaSteam fetches them with the configuration's HTTP client.
 */
fun interface Fetcher {
    /**
     * Downloads [url] whole, sending [headers], and returns the file it is in, which the caller then owns: it reads or
     * moves it and deletes what is left. [name] is stable for the same download, so a host may carry a partial file on
     * from where it stopped. A server's refusal is thrown as a
     * [in.dragonbra.javasteam.util.SteamKitWebRequestException] whose `statusCode` is the HTTP status (a 403 makes the
     * depot downloader ask for a CDN token and try again).
     */
    suspend fun fetch(url: String, headers: Map<String, String>, name: String): File
}
