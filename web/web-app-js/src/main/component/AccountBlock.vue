<template>
    <div class="account-block q-pr-md q-pl-xs q-py-sm shadow-01"
         :class="{'--active': showBlock}">
        <div class="row q-gutter-x-md items-center">
            <q-icon class="__toggle q-py-sm"
                    name="double_arrow"
                    size="xs"
                    @click="showBlock = !showBlock"/>
            <q-icon name="o_search" size="md"/>
            <q-icon name="o_notifications" size="md"/>
            <q-avatar color="red" size="md" text-color="white">ИХ</q-avatar>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'
import { useQuasar } from 'quasar'

const ACCOUNT_SHOW_BLOCK_KEY = 'app.accountBlock.show'

export default defineComponent( {
    name: 'AccountBlock',
    setup( props ) {
        const $q = useQuasar()

        let showBlockVal = $q.localStorage.getItem( ACCOUNT_SHOW_BLOCK_KEY )

        if ( !showBlockVal === undefined ) {
            $q.localStorage.set( ACCOUNT_SHOW_BLOCK_KEY, true )
            showBlockVal = true
        }

        const showBlock = ref( showBlockVal )

        watch( showBlock, val => $q.localStorage.set( ACCOUNT_SHOW_BLOCK_KEY, val ) )

        return {
            showBlock
        }
    }
} )
</script>

<style lang="sass">
@import 'src/style/variables'
.account-block
    position: absolute
    top: 0
    right: -155px
    transition-duration: .3s
    //background: lighten($body-bg-color, 3%)
    border-radius: 10px 0 0 10px

    &.--active
        right: 0
        transition-duration: .3s

        & > * > .__toggle
            transform: rotate(0deg)
            transition-duration: .3s

    & > * > .__toggle
        opacity: 0.4
        transform: rotate(180deg)
        transition-duration: .3s

        &:hover
            opacity: 0.8
            cursor: pointer
            transition-duration: .3s

</style>
