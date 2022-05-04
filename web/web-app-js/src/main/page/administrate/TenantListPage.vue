<template>
    <div class="full-height row justify-center q-pa-md">
        <div class="col-6 content-inner q-pa-md">
            <div class="row q-pb-md">
                <div class="col"></div>
                <div class="col-auto">
                    <q-btn label="Добавить" outline dense color="primary"></q-btn>
                </div>
            </div>
            <q-list separator>
                <q-separator />
                <q-item v-ripple clickable>
                    <q-item-section avatar>
                        <q-avatar color="red" text-color="white">A</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            anyfon
                        </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                        <q-toggle model-value="true" dense/>
                    </q-item-section>
                </q-item>
                <q-item v-ripple clickable>
                    <q-item-section avatar>
                        <q-avatar color="red" text-color="white">V</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            vitoline
                        </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                        <q-toggle model-value="true" dense/>
                    </q-item-section>
                </q-item>
                <q-item v-ripple clickable>
                    <q-item-section avatar>
                        <q-avatar color="red" text-color="white">M</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            molvers
                        </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                        <q-toggle model-value="true" dense/>
                    </q-item-section>
                </q-item>
                <q-separator />
            </q-list>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent( {
    name: 'TenantListPage'
} )
</script>

<style lang="sass">

</style>
