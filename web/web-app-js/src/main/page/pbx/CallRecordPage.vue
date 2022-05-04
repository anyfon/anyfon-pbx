<template>
    <div class="full-height q-pa-sm row justify-center">
        <div class="full-height content-inner q-pa-sm col-10">
            <panel-splitter class="full-height" >
                <template v-slot:sidebar>b</template>
                <template v-slot:main>

                </template>
            </panel-splitter>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent( {
    name: 'CallRecordPage'
} )
</script>

<style lang="sass">

</style>
