package kr.co.fastcampus.eatgo.application

class EmailExistedException(email: String) :
        RuntimeException("Email is already registered: $email")
