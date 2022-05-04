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
                        <q-avatar color="red" text-color="white">AK</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            Artem Kh
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            79961232970
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            ah@anyfon.ru
                        </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                        <q-toggle model-value="true" dense/>
                    </q-item-section>
                </q-item>
                <q-item v-ripple clickable>
                    <q-item-section avatar>
                        <q-avatar color="grey" text-color="white">IH</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            Igor Kh
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            79261119792
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            ih@anyfon.ru
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
    name: 'UserListPage'
} )
</script>

<style lang="sass">

</style>
