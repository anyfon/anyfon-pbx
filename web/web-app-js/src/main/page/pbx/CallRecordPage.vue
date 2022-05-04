<template>
    <div class="full-height q-pa-sm">
        <div class="full-height content-inner q-pa-sm">
            <panel-splitter class="full-height">
                <template v-slot:sidebar>b</template>
                <template v-slot:main>

                </template>
            </panel-splitter>
        </div>
    </div>
</template>

<script lang="ts">
import { QTable } from 'quasar'
import { defineComponent, reactive, ref } from 'vue'
import PanelSplitter from '@component/PanelSplitter.vue'
import axios from 'axios'

export default defineComponent({
    name: 'CallRecordPage',
    components: { PanelSplitter, QTable },
    setup() {
        const columns = [
            {
                name: "id",
                label: "id",
                field: "id"
            },
            {
                name: "callDateTime",
                label: "Date of call",
                field: "callDateTime"
            },
            {
                name: "direction",
                label: "Call direction",
                field: "direction"
            },
            {
                name: "fromNumber",
                label: "From",
                field: "fromNumber"
            },
            {
                name: "toNumber",
                label: "To",
                field: "toNumber"
            },
            {
                name: "didNumber",
                label: "DID",
                field: "didNumber"
            },
            {
                name: "duration",
                label: "Duration",
                field: "duration"
            },
            {
                name: "status",
                label: "Status",
                field: "status"
            },
            {
                name: "answeredNumbers",
                label: "Answered numbers",
                field: "answeredNumbers"
            }

        ]
        return {
            rows: ref([]),
            columns: ref(columns)
        }
    },
    mounted() {
        // axios.post('http://192.168.1.120:8000/api/pbx/call-record-data/find-all', {
        //     requestId: 'aaaa',
        //     params: {}
        // }).then(res => {
        //     this.rows = res.data.result
        // })
    }

})
</script>

<style lang="sass">
.content-inner
    background: white
    border-radius: 6px
</style>
