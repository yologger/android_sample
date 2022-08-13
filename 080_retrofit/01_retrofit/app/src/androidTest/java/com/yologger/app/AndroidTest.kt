package com.yologger.app

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AndroidTest {

    @Test
    fun test() {
        val number: Int = 35
        assertThat(number).isEqualTo(35)
        assertThat(number).isGreaterThan(30)
        assertThat(number).isLessThan(40)
    }
}