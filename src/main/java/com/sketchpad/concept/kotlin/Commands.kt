@file:JvmName("KCommands")
package com.sketchpad.concept.kotlin

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.sketchpad.concept.ah.AuctionHouse
import com.sketchpad.concept.utilities.enchantments.Enchant
import com.sketchpad.concept.utilities.enchantments.EnchantCollection
import com.sketchpad.concept.utilities.items.SkyblockItem
import net.axay.kspigot.commands.*

fun ahCommand() =
    command("ah") {
        runs {
            AuctionHouse.openMain(player)
        }
    }

fun enchantCommand() =
    command("sbenchant") {
        argument("enchantment", StringArgumentType.string()) {
            suggestListSuspending { listOf("ALL", *Enchant.values().map { it.name }.toTypedArray()) }
            argument("level", IntegerArgumentType.integer(0, 50)) {
                runs {
                    val sbStack = SkyblockItem.fromItemStack(player.inventory.itemInMainHand)
                    val enchant = getArgument<String>("enchantment")
                    val lvl = getArgument<Int>("level")
                    if(enchant == "ALL") {
                        val map = hashMapOf<Enchant, Int>()
                        Enchant.values().forEach {
                            map[it] = lvl
                        }
                        sbStack.enchants = EnchantCollection(map)
                    } else sbStack.enchants = EnchantCollection(Enchant.valueOf(enchant) mapTo lvl)

                    player.inventory.setItemInMainHand(sbStack.toItemStack(player))
                }
            }
        }
    }
