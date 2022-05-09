package ru.anyfon.pbx.manager.domain.callrecord

sealed interface GroupingCall : Iterable<GroupingCall.Group>{

    class Base(groups: Iterable<Group>) : GroupingCall {
        private val uniqueGroups = groups.groupBy { it }.map { it.key }
        override fun iterator(): Iterator<Group> = uniqueGroups.iterator()
    }

    class Builder {
        private val groups: MutableList<Group> = mutableListOf()

        fun byMonth() : Builder = checkBeforeAdd(Group.MONTH).let {
            groups.add(Group.MONTH)
            this
        }

        fun byDay() : Builder = checkBeforeAdd(Group.DAY).let {
            groups.add(Group.DAY)
            this
        }

        fun byGroupMember() : Builder = checkBeforeAdd(Group.GROUP_MEMBER).let {
            groups.add(Group.GROUP_MEMBER)
            this
        }

        fun byMember() : Builder = checkBeforeAdd(Group.MEMBER).let {
            groups.add(Group.MEMBER)
            this
        }

        private fun checkBeforeAdd(group: Group) {
            if (groups.contains(group)) {
                throw IllegalStateException("Grouping by [ $group ] already added")
            }
        }

        fun build() : GroupingCall = Base(groups)
    }

    enum class Group(val order: Int) {
        MONTH(1), DAY(2), GROUP_MEMBER(3), MEMBER(4);
    }
}
