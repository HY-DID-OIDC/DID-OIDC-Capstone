package com.capstone.didauthoidc.controller

import com.capstone.didauthoidc.Authsession_constant
import com.capstone.didauthoidc.UrlConstant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PresentationRequestController {

    @RequestMapping("/vc/connect/poll")
    fun createAsync(@RequestParam param: MultiValueMap<String, String>): ResponseEntity<Any> {
        println("poll request arrived")

        /* VC가 도착했을 때 OK를 리턴해야 resolution URL로 리다이렉트 됩니다. */
        if (Authsession_constant.vc_arrived == true) {
            return ResponseEntity(HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @RequestMapping("/url/{key}")
    fun ResolveUrl(@RequestParam param: MultiValueMap<String, String>): String {

        val url = UrlConstant.longUrl

        return "redirect:$url"
    }
}
