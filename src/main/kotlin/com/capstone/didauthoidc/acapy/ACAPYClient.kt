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

    fun walletPublicDid(): WalletPublicDid?

    fun getAdminUrl(): String?

    fun getAgentUrl(): String?
}

class ACAPYClient : IACAPYClient {

    private var log: Logger = LoggerFactory.getLogger(Controller::class.java)

    // configuration 구현 전 하드코딩
    private val _adminUrl: String = "http://localhost:5678"

    private val _adminUrlApiKey: String = ""

    private val _agentUrl: String = "http://192.168.65.3:5679"

    override fun getAdminUrl(): String? {
        return _adminUrl
    }

    override fun getAgentUrl(): String? {
        return _agentUrl
    }

    override fun walletPublicDid(): WalletPublicDid {

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
        var publicDid: WalletDidPublicResponse = mapper.readValue(json)

        `in`.close()

        return publicDid.result
    }
}
