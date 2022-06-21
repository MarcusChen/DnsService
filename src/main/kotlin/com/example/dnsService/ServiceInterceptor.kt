package com.example.dnsService

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ServiceInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("Pre Handle method is Calling")

//        request.dispatcherType.name != "ERROR"
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        println("Post Handle method is Calling")
    }
}