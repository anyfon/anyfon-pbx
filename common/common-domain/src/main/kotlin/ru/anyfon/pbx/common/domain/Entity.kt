package ru.anyfon.pbx.common.domain

interface Entity {

    interface Mapped<Res> : Entity {
        fun map() : Res
    }

    interface Mapper<Src : Entity, Res> {
        fun map(src: Src): Res
    }
}
