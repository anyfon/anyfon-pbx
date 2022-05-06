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
                <q-separator/>
                <q-item v-for="user in users" v-ripple clickable :key="user.id">
                    <q-item-section avatar>
                        <q-avatar color="red" text-color="white">{{ user.avatarLabel }}</q-avatar>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            {{ user.firstName }} {{ user.lastName }}
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            {{ user.phoneNumber }}
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label>
                            {{ user.email }}
                        </q-item-label>
                    </q-item-section>
                    <q-item-section>
                        <q-item-label style="width: 100px">
                            {{ user.role }}
                        </q-item-label>
                    </q-item-section>
                    <q-item-section side>
                        <q-toggle model-value="true" dense/>
                    </q-item-section>
                </q-item>
                <q-separator/>
            </q-list>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import axios from 'axios'
import { User } from '@main/api/models'

export default defineComponent( {
    name: 'UserListPage',
    setup() {
        const users = ref([])

        onMounted( () => {
            axios.get( "/api/auth/user/fetch-list" ).then( resp => {
                if ( resp.data ) {
                     users.value = resp.data.map( ( user: User) => {
                        return {
                            ...user,
                            avatarLabel: user.firstName[ 0 ].toUpperCase() + user.lastName[ 0 ].toUpperCase()
                        }
                    })
                }
            } )
        } )
        return {
            users
        }
    }
} )
</script>

<style lang="sass">

</style>
