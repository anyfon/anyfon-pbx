<template>
    <div class="page-layout column">
        <q-resize-observer @resize="setFullSize"/>
        <div class="page-layout__header col-auto shadow-1">
            <q-resize-observer @resize="setHeaderHeight"/>
            <div class="row justify-center items-center full-height">
                <div class="col-auto __title q-px-md" v-html="headerTitle"/>
                <div class="col __content-inner q-pl-md">
                    <slot name="header"/>
                </div>
            </div>
        </div>
        <div class="page-layout__content col" :style="contentStyle">
            <slot name="content"/>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import contentSize from '@main/mixin/content-size'

export default defineComponent( {
    name: 'PageLayout',
    mixins: [ contentSize ],
    props: {
        headerTitle: String
    }
} )
</script>

<style lang="sass">
@import 'src/style/variables'
.page-layout
    height: 100%
    & > .page-layout__header
        height: $page-header-height
        padding-right: 185px
        background: white

        & .__title
            font-size: 1.15rem
            margin-top: -3px

        & .__content-inner
</style>
