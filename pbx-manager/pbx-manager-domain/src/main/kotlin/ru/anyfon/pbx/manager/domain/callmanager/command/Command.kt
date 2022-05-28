package ru.anyfon.pbx.manager.domain.callmanager.command

import ru.anyfon.pbx.manager.domain.callmanager.CommandName

interface Command {

    val name: CommandName

    fun execute()

    fun execute(callback: ReplyCallback)

}
