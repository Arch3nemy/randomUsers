package com.alacrity.randomUsers

interface EventHandler<T> {
    fun obtainEvent(event: T)
}