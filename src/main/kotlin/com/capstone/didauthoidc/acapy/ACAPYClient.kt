package com.capstone.didauthoidc.acapy

import com.capstone.didauthoidc.acapy.models.WalletDidPublicResponse
import com.capstone.didauthoidc.acapy.models.WalletPublicDid
import com.capstone.didauthoidc.utils.OurJacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

interface IACAPYClient {

    fun WalletDidPublic(): WalletPublicDid?

    fun GetAdminUrl(): String?

    fun GetAgentUrl(): String?
}

class ACAPYClient : IACAPYClient {

    private var log: Logger = LoggerFactory.getLogger(Controller::class.java)

    // configuration 구현 전 하드코딩
    private val _adminUrl: String = "http://localhost:5678"

    private val _adminUrlApiKey: String = ""

    private val _agentUrl: String = "http://localhost:5679"

    override fun GetAdminUrl(): String? {
        return _adminUrl
    }

    override fun GetAgentUrl(): String? {
        return _agentUrl
    }

    override fun WalletDidPublic(): WalletPublicDid {
      
        val url = URL("http://localhost:5678/wallet/did/public")

        val con = url.openConnection() as HttpURLConnection

        con.requestMethod = "GET"

        val status = con.responseCode

        val `in` = BufferedReader(
            InputStreamReader(con.inputStream)
        )

        var inputLine: String?

        val content = StringBuffer()

        while (`in`.readLine().also { inputLine = it } != null) {
            content.append(inputLine)
        }

        val json: String = content.toString()
        val mapper = OurJacksonObjectMapper.getMapper()
        var PublicDid: WalletDidPublicResponse = mapper.readValue<WalletDidPublicResponse>(json)

        `in`.close()

        return PublicDid.Result
    }
}
