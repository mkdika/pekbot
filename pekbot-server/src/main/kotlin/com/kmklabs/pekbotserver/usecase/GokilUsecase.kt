package com.kmklabs.pekbotserver.usecase

import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom

@Component
class GokilUsecase {

    companion object {
        val dataGokil = listOf(
           "Seberat apapun masalahmu jangan ditimbang gak balakan laku!",
           "Cari wifi saja! kapan cari wife?",
           "Kalau aku jadi superhero, aku akan jadi \"Yourman\".",
            "Sebenarnya pacar orang adalah pacar kiat juga karena kita adalah orang.",
            "Mendung belum tentu hujan. Pdkt belum tentu jadian.",
            "Jika kamu takut merasakan sahit hati saat diputusin pacar, lebih baik kamu putus duluan sebelum jadian.",
            "Mau gagal, mau sukses tidak penting, yang penting berhasil!",
            "Sehat itu simple, kuncinya cuma satu, jangan sakit."
        )


    }

    fun randomGokil(): String {
        val index = ThreadLocalRandom.current().nextInt(0, dataGokil.size)
        return dataGokil[index]
    }
}

