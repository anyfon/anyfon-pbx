<template>
    <page-layout header-title="Телефония">
        <template v-slot:header>
            <q-tabs>
                <q-route-tab to="/pbx/call-record" label="Статистика"></q-route-tab>
                <q-route-tab v-if="userIsRoot" to="/pbx/call-record" label="Аналитика"></q-route-tab>
                <q-route-tab v-if="userIsRoot" to="/pbx/users" label="Абоненты"></q-route-tab>
                <q-route-tab v-if="userIsRoot" to="/pbx/schemes" label="Схемы вызовов"></q-route-tab>
                <q-route-tab v-if="userIsRoot" to="/pbx/numbers" label="Номера"></q-route-tab>
            </q-tabs>
        </template>
        <template v-slot:content>
            <router-view></router-view>
        </template>
    </page-layout>
</template>

<script lang="ts">
import { computed, defineComponent } from 'vue'
import PageLayout from '@main/layout/PageLayout.vue'
import { QRouteTab } from 'quasar'
import { useStore } from 'vuex'
import { AppGetter } from '@main/store/app/getters'

export default defineComponent( {
    name: 'PbxPage',
    components: {
        PageLayout,
        QRouteTab
    },
    setup() {
        const $store = useStore()

        return {
            userIsRoot: computed( () => $store.getters[ AppGetter.USER_IS_ROOT ] )
        }

    }
} )
</script>

<style lang="sass">

</style>
