import { GetterTree } from 'vuex'
import { appKeyOf, AppState } from '@main/store/app/types'
import { RootState } from '@main/store/types'
import { UserRole } from '@main/api/models'

export enum AppGetter {
    AUTHORIZED_USER = 'app/AUTHORIZED_USER',
    USER_IS_ROOT = 'app/USER_IS_ROOT'
}

const getters: GetterTree<AppState, RootState> = {

    [ appKeyOf( AppGetter.AUTHORIZED_USER ) ]: state => state.authorizedUser,

    [ appKeyOf(AppGetter.USER_IS_ROOT)]: state => state.authorizedUser
        ? state.authorizedUser.role == UserRole.ROOT
        : false
}

export default getters
