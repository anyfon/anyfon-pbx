<template>
    <page-layout header-title="Телефония">
        <template v-slot:header>
            <q-tabs>
                <q-route-tab to="/pbx/call-record" label="Статистика"></q-route-tab>
                <q-route-tab to="/pbx/call-record" label="Аналитика"></q-route-tab>
                <q-route-tab to="/pbx/users" label="Абоненты"></q-route-tab>
                <q-route-tab to="/pbx/schemes" label="Схемы вызовов"></q-route-tab>
                <q-route-tab to="/pbx/numbers" label="Номера"></q-route-tab>
            </q-tabs>
        </template>
        <template v-slot:content>
            <router-view></router-view>
        </template>
    </page-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import PageLayout from '@layout/PageLayout.vue'
import { QRouteTab } from 'quasar'

export default defineComponent( {
    name: 'PbxPage',
    components: {
        PageLayout,
        QRouteTab
    }
} )
</script>

<style lang="sass">

</style>
