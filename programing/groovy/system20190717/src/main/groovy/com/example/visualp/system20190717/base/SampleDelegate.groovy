package com.example.visualp.system20190717.base

import java.util.function.Function
import java.util.function.Supplier

class SampleDelegate {

    static def hello(String value, Function supplier) {
        println value + supplier.apply("bddd")
    }
}
