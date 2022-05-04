<template>
    <div class="navigate-bar shadow-3 q-pt-sm">
        <q-tabs vertical no-caps>
            <q-route-tab to="/pbx">
                <div class="column navigate-bar__item q-py-sm">
                    <q-icon name="dialer_sip" size="36"/>
                    <span class="text-caption __label">Звонки</span>
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
        padding: 0
        .q-tab__indicator
            left: 1px !important
    .navigate-bar__item
        color: $navigate-bar-item-color
        & > .__label
            padding-top: 2px
    .q-tab--active .navigate-bar__item
        color: $navigate-bar-item-active-color !important
    .q-tab--active .q-tab__indicator
        color: $navigate-bar-item-indicator-active-color !important

</style>
