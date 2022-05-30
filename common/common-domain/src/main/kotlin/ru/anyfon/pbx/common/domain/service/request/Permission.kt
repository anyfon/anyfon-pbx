package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value

interface Permission {

    val type: Type

    object Create : Permission {
        override val type: Type = Type.Create
    }

    object ReadList : Permission {
        override val type: Type = Type.ReadList
    }

    abstract class EntityIdAware<ID : EntityID<*>>(
        val entityID: ID,
        final override val type: Permission.Type
    ) : Permission

    class ReadEntity<ID : EntityID<*>>(entityId: ID) : EntityIdAware<ID>(entityId, Type.ReadEntity)

    class Update<ID : EntityID<*>>(entityId: ID) : EntityIdAware<ID>(entityId, Type.Update)

    class Delete<ID : EntityID<*>>(entityId: ID) : EntityIdAware<ID>(entityId, Type.Delete)

    abstract class Type(name: String) : Value.AsString(name, "[A-Z\\_]{3,20}") {

        object Create : Type("CREATE")

        object ReadEntity : Type("READ_ENTITY")

        object ReadList : Type("READ_LIST")

        object Update : Type("UPDATE")

        object Delete : Type("DELETE")
    }

}
