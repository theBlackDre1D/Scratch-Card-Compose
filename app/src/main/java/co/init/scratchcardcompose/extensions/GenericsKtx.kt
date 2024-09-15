package co.init.scratchcardcompose.extensions

fun <T> T?.orDefault(defaultValue: T): T = this ?: defaultValue