package com.kmklabs.pekbotserver.model

import java.util.*

/*

// Payload example

{
  "type": "MESSAGE",
  "eventTime": "2017-03-02T19:02:59.910959Z",
  "space": {
    "name": "spaces/AAAAAAAAAAA",
    "displayName": "Best Dogs Discussion Room",
    "type": "ROOM"
  },
  "message": {
    "name": "spaces/AAAAAAAAAAA/messages/CCCCCCCCCCC",
    "sender": {
      "name": "users/12345678901234567890",
      "displayName": "Chris Corgi",
      "avatarUrl": "https://lh3.googleusercontent.com/.../photo.jpg",
      "email": "chriscorgi@example.com"
    },
    "createTime": "2017-03-02T19:02:59.910959Z",
    "text": "I mean is there any good reason their legs should be longer?",
    "thread": {
      "name": "spaces/AAAAAAAAAAA/threads/BBBBBBBBBBB"
    }
  }
}
*/


data class HttpRequest(
    val type: String,
    val eventTime: Date,
    val space : HttpRequestSpace,
    val message: HttpRequestMessage
)

data class HttpRequestSpace (
    val name : String,
    val displayName : String,
    val type : String
)

data class HttpRequestMessage(
    val name : String,
    val sender : HttpRequestSender,
    val createTime :  Date,
    val text : String,
    val thread : HttpRequestThread
)

data class HttpRequestSender (
    val name:String,
    val displayName:String,
    val avatarUrl:String,
    val email:String
)

data class HttpRequestThread (
    val name: String
)