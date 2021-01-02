package com.capstone.didauthoidc.controller

import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

class PresentationRequestController {

    @RequestMapping("/vc/connect/poll")
    fun createAsync(@RequestParam param: MultiValueMap<String, String>): String {
        println(param.values)

        return "temp"
    }
}
