<template>
    <div class="panel-splitter row"
         :class="splitterClasses"
         @mousemove="onMouseMove"
         @mouseleave="onEndResize"
         @mouseup="onEndResize">
        <div class="panel-splitter__sidebar col-auto"
             v-show="!hideSidebar"
             :style="{width: sidebarWidth + 'px'}">
            <slot name="sidebar"></slot>
            <div v-if="showSeparator"
                 class="panel-splitter__sidebar-separator row justify-center"
                 @mousedown="onStartResize">
                <q-separator v-if="!$slots.separator" vertical></q-separator>
                <slot name="separator"></slot>
            </div>
        </div>
        <div class="panel-splitter__main col full-height">
            <slot name="main"></slot>
        </div>
    </div>
</template>

<script lang="ts">

import { defineComponent, ref } from 'vue'

const DEFAULT_SIDEBAR_WIDTH = 250

export default defineComponent( {
    name: 'PanelSplitter',
    props: {
        overlaySidebar: Boolean,
        disableResize: Boolean,
        hideSidebar: Boolean,
        reverse: Boolean,
        sidebarWidthPx: [ Number, String ],
        minSidebarSizePx: {
            type: [ Number, String ],
            default: 0
        },
        maxSidebarSizePx: {
            type: [ Number, String ],
            default: Infinity
        }
    },
    setup( props ) {
        const startResizeData = ref( undefined )

        const sidebarWidthPx = props.sidebarWidthPx
            ? Number.parseInt(props.sidebarWidthPx.toString())
            : undefined

        const sidebarWidth =  sidebarWidthPx || (props.minSidebarSizePx != 0 ?
            props.minSidebarSizePx :
            DEFAULT_SIDEBAR_WIDTH)

        return {
            showSeparator: ref( !props.disableResize ),
            sidebarWidth: ref(sidebarWidth),
            startResizeData
        }
    },
    computed: {
        splitterClasses() {
            return {
                reverse: this.reverse,
                '--active-resize': !!this.startResizeData,
                '--overlay-sidebar': this.overlaySidebar
            }
        }
    },
    methods: {
        onStartResize( event: PointerEvent ) {
            this.startResizeData = {
                startWidth: this.sidebarWidth,
                clientX: event.clientX
            }
        },
        onEndResize() {
            this.startResizeData = undefined
            this.$emit( 'resize-sidebar', this.sidebarWidth )
        },
        onMouseMove( event: MouseEvent ) {
            if ( this.startResizeData ) {
                const currentWidth = this.reverse ?
                    this.startResizeData.startWidth - ( event.clientX - this.startResizeData.clientX ) :
                    this.startResizeData.startWidth - ( this.startResizeData.clientX - event.clientX )
                if ( currentWidth > this.minSidebarSizePx && currentWidth < this.maxSidebarSizePx ) {
                    this.sidebarWidth = currentWidth
                }
            }
        }
    }
} )
</script>
<style lang="sass">

.panel-splitter
    height: 100%

    *
        -webkit-user-drag: none
        -webkit-user-select: none

    .panel-splitter__main
        overflow: auto

    &.--active-resize
        cursor: col-resize

    & > .panel-splitter__sidebar
        height: 100%
        position: relative

        & > .panel-splitter__sidebar-separator
            height: 100%
            position: absolute
            top: 0
            right: -5px
            min-width: 10px
            cursor: col-resize

    &.reverse > .panel-splitter__sidebar
        & > .panel-splitter__sidebar-separator
            left: -5
            right: auto

    &.--overlay-sidebar
        position: relative
        & > .panel-splitter__sidebar
            position: absolute
            z-index: 1000
</style>
