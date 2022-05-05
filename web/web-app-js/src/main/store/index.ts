import { StoreOptions, createStore } from 'vuex'
import { RootState } from '@main/store/types'

import app from '@main/store/app'

const store: StoreOptions<RootState> = {
    state: {
        version: '1.0.0'
    },
    modules: {
        app
    }
}

export default createStore<RootState>(store)
