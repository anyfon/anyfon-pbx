<template>
    <page-layout header-title="Администрирование">
        <template v-slot:header>
            <q-tabs>
                <q-route-tab to="/administrate/user-list" label="Пользователи"></q-route-tab>
                <q-route-tab to="/administrate/tenant-list" label="Эккаунты"></q-route-tab>
                <q-route-tab to="/administrate/sip-subscriber-list" label="SIP пользователи"></q-route-tab>
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

export default defineComponent( {
    name: 'AdministratePage',
    components: {
        PageLayout
    }
} )
</script>

<style lang="sass">

</style>
