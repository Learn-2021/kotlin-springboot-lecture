package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    /**
     *
     * {
     *      "result": {
     *          "result_code" : "OK",
     *          "result_message : "성공"
     *      },
     *      "description" : "blah blah~",
     *      "user" : [
     *          {
     *              "name" : "hoon",
     *              "age" : 10,
     *              "email : "a@a.com",
     *              "phoneNumber" : "010AAAAAAAA"
     *          },
     *          {
     *              "name" : "soo",
     *              "age" : 20,
     *              "email : "b@b.com",
     *              "phoneNumber" : "010BBBBBBBB"
     *          }
     *      ]
     * }
     * */
    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
            //--
            this.description = "!!!"

            val users = mutableListOf<UserRequest>()

            users.add(userRequest)

            users.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@a.com"
                this.address = "a address"
                this.phoneNumber = "01012341234"
            })

            users.add(UserRequest().apply {
                this.name = "b"
                this.age = 10
                this.email = "b@b.com"
                this.address = "b address"
                this.phoneNumber = "01012341234"
            })

            this.userRequest = users
        }
//            .apply {
//            this.description = "blah blah~~"
//        }.apply {
//            val users = mutableListOf<UserRequest>()
//
//            users.add(userRequest)
//
//            users.add(UserRequest().apply {
//                this.name = "a"
//                this.age = 10
//                this.email = "a@a.com"
//                this.address = "a address"
//                this.phoneNumber = "01012341234"
//            })
//
//            users.add(UserRequest().apply {
//                this.name = "b"
//                this.age = 10
//                this.email = "b@b.com"
//                this.address = "b address"
//                this.phoneNumber = "01012341234"
//            })
//
//            this.userRequest = users
//        }
    }
}