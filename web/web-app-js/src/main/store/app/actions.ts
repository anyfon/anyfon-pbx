import { appKeyOf, AppState } from '@main/store/app/types'
import { RootState } from '@main/store/types'
import { ActionTree } from 'vuex'
import axios from 'axios'
import { AppMutation } from '@main/store/app/mutations'


export enum AppAction {
    FETCH_USER_SELF = "app/FETCH_USER_SELF"
}

const actions: ActionTree<AppState, RootState> = {
    [ appKeyOf( AppAction.FETCH_USER_SELF ) ]( { commit } ) {
        axios.get( "/api/auth/user/fetch-self" ).then( resp => {
            commit( AppMutation.SET_AUTHORIZED_USER, resp.data )
        } )
    }
}

export default actions
