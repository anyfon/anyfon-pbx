import { MutationTree } from 'vuex'
import { AppState } from '@main/store/app/types'
import { User } from '@main/api/models'

export enum AppMutation {
    SET_AUTHORIZED_USER = "SET_AUTHORIZED_USER"
}

const mutations: MutationTree<AppState> = {
    [ AppMutation.SET_AUTHORIZED_USER ]( state, user: User ) {
        state.authorizedUser = user
    }
}

export default mutations
