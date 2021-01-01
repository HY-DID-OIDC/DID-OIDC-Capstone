package com.capstone.didauthoidc

import net.minidev.json.JSONObject

class Authsession_constant {
    companion object {
        var vc_arrived: Boolean = false

        var session_Id: String = "null"

        var state: String = "null"

        var presentationRecordId: String = "null"

        var email: String = "null"

        var issued_at: String = "null"

        var nonce: String = "null"

        var redirect_url: String = "null"

        val keys: JSONObject = JSONObject()
    }
}
