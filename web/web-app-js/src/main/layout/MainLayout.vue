<template>
    <div class="main-layout row">
        <navigate-bar></navigate-bar>
        <div class="main-layout__inner col">
            <div class="main-layout__content">
                <account-block />
                <router-view />
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

import NavigateBar from '@component/NavigateBar.vue'
import AccountBlock from '@component/AccountBlock.vue'

export default defineComponent( {
    name: 'MainLayout',
    components: { AccountBlock, NavigateBar }
} )
</script>

<style lang="sass">
@import 'src/style/variables'
.main-layout
    height: 100%

    .main-layout__content
        height: 100%
        position: relative
        & > .--account-block
            position: absolute
            top: 0
            right: 0
</style>
