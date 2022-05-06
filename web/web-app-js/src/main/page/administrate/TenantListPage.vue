<template>
    <div class="full-height row justify-center q-pa-md">
        <div class="col-xl-6 col-10 content-inner q-pa-md">
            <div class="row q-pb-md">
                <div class="col"></div>
                <div class="col-auto">
                    <q-btn label="Добавить" outline dense color="primary"></q-btn>
                </div>
            </div>
            <q-list separator>
                <q-separator />
                <q-item v-for="tenant in tenants" v-ripple clickable :key="tenant.id">
                    <q-item-section avatar>
                        <q-avatar color="red" text-color="white">{{ tenant.avatarLabel }}</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            {{ tenant.uniqueName }}
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
import { defineComponent, onMounted, ref } from 'vue'
import axios from 'axios'
import { Tenant, User } from '@main/api/models'

export default defineComponent( {
    name: 'TenantListPage',
    setup() {
        const tenants = ref([])

        onMounted( () => {
            axios.get( "/api/auth/tenant/fetch-list" ).then( resp => {
                if ( resp.data ) {
                    tenants.value = resp.data.map( ( tenant: Tenant) => {
                        return {
                            ...tenant,
                            avatarLabel: tenant.uniqueName[0].toUpperCase()
                        }
                    })
                }
            } )
        } )
        return {
            tenants
        }
    }
} )
</script>

<style lang="sass">

</style>
