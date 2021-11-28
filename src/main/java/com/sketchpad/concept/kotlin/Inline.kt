@file:JvmName("KInline")

package com.sketchpad.concept.kotlin

import com.sketchpad.concept.Concept
import org.bukkit.NamespacedKey

val plugin: Concept get() = Concept.instance
fun nKey(name: String) = NamespacedKey(plugin, name)
fun String.namespace() = nKey(this)

infix fun <K, V> K.mapTo(other: V) = hashMapOf(this to other)