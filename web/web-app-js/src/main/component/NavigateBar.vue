<template>
    <div class="navigate-bar shadow-3 q-pt-sm">
        <q-tabs vertical no-caps>
            <q-route-tab to="/pbx">
                <div class="column navigate-bar__item q-py-sm">
                    <q-icon name="dialer_sip" size="36px"/>
                    <span class="text-caption __label">Звонки</span>
                </div>
            </q-route-tab>
            <q-route-tab to="/administrate">
                <div class="column navigate-bar__item q-py-sm">
                    <q-icon name="o_admin_panel_settings" size="36px"/>
                    <span class="text-caption __label">Admin</span>
                </div>
            </q-route-tab>
        </q-tabs>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { QRouteTab } from 'quasar'
export default defineComponent( {
    name: 'NavigateBar',
    components: {
        QRouteTab
    }
} )
</script>

<style lang="sass">
@import 'src/style/variables'
.navigate-bar
    height: 100%
    width: $navigate-bar-size
    background-color: $navigate-bar-bg
    .q-tab, .q-tab__content
        width: 100%
        padding: 0
        .q-tab__indicator
            left: 1px !important
    .navigate-bar__item
        width: 100%
        color: $navigate-bar-item-color
        & > .__label
            display: block
            padding-top: 2px
            width: 100%
            text-overflow: ellipsis
    .q-tab--active .navigate-bar__item
        color: $navigate-bar-item-active-color !important
    .q-tab--active .q-tab__indicator
        color: $navigate-bar-item-indicator-active-color !important

</style>
