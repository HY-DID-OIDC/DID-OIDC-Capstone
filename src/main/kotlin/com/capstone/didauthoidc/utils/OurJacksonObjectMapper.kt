package com.capstone.didauthoidc.utils

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/* jacksonObjectMapper의 설정을 매번 해주는게 귀찮으므로 싱글톤 패턴으로 클래스를 정의하였다.
사용할때는 OurJacksonObjectMapper.getMapper().readValue() 나
OurJacksonObjectMapper.getMapper().writeValueAsString() 처럼 쓰면 된다. */
class OurJacksonObjectMapper private constructor() {
    companion object {
        private var mapper: ObjectMapper? = null

        @JvmStatic
        fun getMapper(): ObjectMapper {
            if (mapper != null)
                return mapper as ObjectMapper
            else {
                mapper = jacksonObjectMapper()
                mapper!!.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                mapper!!.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                mapper!!.setSerializationInclusion(JsonInclude.Include.ALWAYS)
                return mapper as ObjectMapper
            }
        }
    }
}
