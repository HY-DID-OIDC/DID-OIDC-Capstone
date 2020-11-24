package com.capstone.didauthoidc.controller

import com.capstone.didauthoidc.UrlConstant
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PresentationRequestController {

    @RequestMapping("vc/connect/poll")
    fun createAsync(@RequestParam param: MultiValueMap<String, String>): String {
        println(param.values)

        return "temp"
    }

    @RequestMapping("url/{key}")
    fun ResolveUrl(@RequestParam param: MultiValueMap<String, String>): String {

        val url = UrlConstant.longUrl

        println(url)

        return "redirect:$url"
    }
}
