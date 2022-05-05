import { AppState } from '@main/store/app/types'
import { Module } from 'vuex'
import { RootState } from '@main/store/types'

import getters from '@main/store/app/getters'
import actions from '@main/store/app/actions'
import mutations from '@main/store/app/mutations'

const namespaced = true

const state: AppState = {
    authorizedUser: null
}

const module: Module<AppState, RootState> = {
    namespaced,
    state,
    getters,
    actions,
    mutations
}

export default module
