<template>
    <div class="full-height q-pa-sm row justify-center">
        <div class="full-height content-inner q-pa-sm col-md col-xl-10 ">
            <panel-splitter class="full-height" sidebar-width-px="300">
                <template v-slot:sidebar>
                    <div class="q-pa-sm">
                        <div class="text-subtitle1 q-ml-sm q-mt-sm">
                            Дата звонка
                        </div>
                        <div>
                            <q-field dense  class="form-field" outlined>
                                <template v-slot:control>
                                    21.03.2022 09:00 / 22.03.2022 10:00
                                </template>
                                <template v-slot:append>
                                    <q-icon name="unfold_more"></q-icon>
                                </template>
                            </q-field>
                        </div>
                        <div class="text-subtitle1 q-ml-sm q-mt-sm">
                            Направление
                        </div>
                        <div>
                            <q-checkbox label="Входящие"/>
                        </div>
                        <div>
                            <q-checkbox label="Исходящие"/>
                        </div>
                        <div>
                            <q-checkbox label="Внутренние"/>
                        </div>
                        <div class="text-subtitle1 q-ml-sm q-mt-md">
                            Кто звонил
                        </div>
                        <div>

                        </div>
                        <div>
                            <q-select class="form-field" dense outlined v-model="srcFilter"/>
                        </div>

                        <div class="text-subtitle1 q-ml-sm q-mt-md">
                            Участники
                        </div>
                        <div>

                        </div>
                        <div>
                            <q-select class="form-field" dense outlined/>
                        </div>
                        <div class="text-subtitle1 q-ml-sm q-mt-md">
                            Статус
                        </div>
                        <div>
                            <q-checkbox label="Успешно"/>
                        </div>
                        <div>
                            <q-checkbox label="Без ответа"/>
                        </div>
                        <div>
                            <q-checkbox label="Ошибка вызова"/>
                        </div>
                        <div>
                            <q-checkbox label="Занято"/>
                        </div>
                    </div>
                </template>
                <template v-slot:main>

                </template>
            </panel-splitter>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent( {
    name: 'CallRecordPage',
    setup() {
        const srcFilter = ref( [] )
        const membersFilter = ref( [] )

        return {
            srcFilter,
            membersFilter
        }
    }
} )
</script>

<style lang="sass">

</style>
