package com.capstone.didauthoidc.services

import java.util.UUID

class UrlShortenerService {
    fun createShortUrl(url: String): String {

        val final_url = url.split("/vc")

/*        TODO : 일반url과 shorturl을 짝지어서 MappedUrl 클래스로 저장하여 DB에 저장해야함.
        그래야 나중에 shorturl을 가지고 일반url을 get할수있음. */
        val uuid: String = UUID.randomUUID().toString()
        return "${final_url[0]}/url/$uuid"
    }
}
