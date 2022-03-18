package com.soon83.workmanagement.utils

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

class HttpUtil {

    companion object {

        //@JvmStatic
        fun getCurrentUri(id: Long): URI {
            val path = "/$id"
            val requestUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
            //log.debug("# requestUri: $requestUri")
            //log.debug("# requestUri.indexOf: ${requestUri.indexOf("/", 8)}")
            return URI(requestUri.substring(requestUri.indexOf("/", 8)) + path)
            //log.debug("# uri: $uri")
            //return uri
        }
    }
}