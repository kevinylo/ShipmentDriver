package com.example.drivers

data class Optional<T> constructor(private val value: T?) {

    val isPresent = value != null

    fun get() = value ?: throw NotPresentException()

    fun or(alternative: T): T = value ?: alternative

    fun orNull(): T? = value

    class NotPresentException : RuntimeException()

    override fun equals(other: Any?): Boolean {
        return if (other is Optional<*>) {
            this.orNull() == other.orNull()
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {

        fun <T> fromNullable(t: T?): Optional<T> = Optional(t)

        fun <T> of(t: T): Optional<T> = Optional(t)

        fun <T> absent(): Optional<T> = Optional(null)
    }
}

